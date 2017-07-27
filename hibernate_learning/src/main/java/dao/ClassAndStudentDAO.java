package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pojo.Class;
import pojo.Student;

public class ClassAndStudentDAO {

	
	private SessionFactory factory;
	
	public ClassAndStudentDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	public Integer addClass(Class classToAdd){
		Integer classID = null;
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			classID = (Integer)session.save(classToAdd);
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
		return classID;
	}
	
	
	public void listClassAndStudents(){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			List<Class> classes = session.createQuery("FROM Class").list();
			classes.forEach(theClass->{
				System.out.print(theClass.getId()+"\t");
				System.out.print(theClass.getName()+"\t");
				theClass.getStudents().forEach(student->{
					System.out.print("'"+student.getId()+" - "+student.getName()+"' ");
				});
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
	
	
	public void deleteClassAll(){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			List<Class> classes = session.createQuery("FROM Class").list();
			//if the db set up a foreign key constraint then
			//even the entity has the annotation CascadeType = Remove/All, it would Not work
			//must manually delete the "Many Side" first.
			classes.forEach(theClass->{
				Set<Student> students = theClass.getStudents();
				students.forEach(student->{
					session.delete(student);
				});
				session.delete(theClass);
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
	
	public void updateClass(Class classToUpdate){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			session.update(classToUpdate);
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
