package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pojo.Employee;
import pojo.Role;
import pojo.User;

public class UserAndRoleDAO {


	private SessionFactory factory;
	
	public UserAndRoleDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	public Integer addUser(User userToAdd){
		Integer userID = null;
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			Set<Role> roles = userToAdd.getRoles();
		    if(roles!=null){
		    	roles.forEach(role->addRole(role));
		    } 
			userID = (Integer)session.save(userToAdd);
			tx.commit();
		}catch(HibernateError e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null){
				session.close();
			}
		}
		return userID;
	}
	
	public Integer addRole(Role roleToAdd){
		Integer roleID = null;
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			// here have to save the users in role first, or it will throw exception 
			// object references an unsaved transient instance, save the transient instance beforeQuery flushing
			Set<User> users = roleToAdd.getUsers();
			if(users!=null){
				users.forEach(user->addUser(user));
			}
			roleID = (Integer)session.save(roleToAdd);
			tx.commit();
		}catch(HibernateError e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null){
				session.close();
			}
		}
		return roleID;
	}
	
	public void listRoles(){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			List<Role> roles = session.createQuery("FROM Role").list();
			if(roles!=null){
				roles.forEach(role->{
					System.out.print(((Role)role).getId()+"\t");
					System.out.print(((Role)role).getName()+"\t");
					Set<User> users = role.getUsers();
					if(users != null){
						users.forEach(user->{
							System.out.print("'"+user.getId()+" - "+user.getName()+"'");
						});
					}
					System.out.println("");
				});
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	
	public void listUsers(){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			List<User> users = session.createQuery("FROM User").list();
			if(users!=null){
				users.forEach(user->{
					System.out.print(((User)user).getId()+"\t");
					System.out.print(((User)user).getName()+"\t");
					Set<Role> roles = user.getRoles();
					if(roles != null){
						roles.forEach(role->{
							System.out.print("'"+role.getId()+" - "+role.getName()+"'");
						});
					}
					System.out.println("");
				});
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void deleteAllUser(){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE FROM User");
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		} 
	}
	
	public void deleteAllRole(){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Role");
			query.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		} 
	}
	
}
