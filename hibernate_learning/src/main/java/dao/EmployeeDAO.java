package dao;



import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.hql.internal.ast.tree.Statement;

import pojo.Employee;

public class EmployeeDAO {
	
	private SessionFactory factory;
		
	public EmployeeDAO(SessionFactory factory) {
		this.factory = factory;
	}


	public Integer addEmployee(Employee employeeToAdd){
		Integer employeeID = null;
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			employeeID = (Integer)session.save(employeeToAdd);
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
		return employeeID;
	}
	
	
	public void listEmployees(){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			List<Employee> employees = session.createQuery("FROM Employee").list();
			employees.forEach(employee->{
				System.out.print(employee.getId()+"\t");
				System.out.print(employee.getFirstName()+"\t");
				System.out.print(employee.getLastName()+"\t");
				System.out.print(employee.getSalary()+"\t");
				System.out.println("");
			});
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
	
	public void updateEmployee(Employee employeeToUpdate){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			Employee employee = session.get(Employee.class, employeeToUpdate.getId());
			employee.setFirstName(employeeToUpdate.getFirstName());
			employee.setLastName(employeeToUpdate.getLastName());
			employee.setSalary(employeeToUpdate.getSalary());
			session.save(employee);
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
	
	public void updateEmployeeByHql(Employee employeeToUpdate){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			String hql = " Update Employee set firstName = :firstName, lastName = :lastName, salary = :salary where id = :id ";
			Query query = session.createQuery(hql);
			query.setParameter("id", employeeToUpdate.getId());
			query.setParameter("salary", employeeToUpdate.getSalary());
			query.setParameter("lastName", employeeToUpdate.getLastName());
			query.setParameter("firstName", employeeToUpdate.getFirstName());
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
	
	public void deleteEmployeeByID(Integer id){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			Employee employee = session.get(Employee.class, id);
			session.delete(employee);
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
	
	
	public void deleteEmployeeAll(){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Employee");
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
