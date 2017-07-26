package dao;

import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pojo.Book;
import pojo.Employee;

public class BookDAO {
	private SessionFactory factory;
	
	public BookDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public Integer addBook(Book bookToAdd){
		Integer bookID = null;
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			bookID = (Integer)session.save(bookToAdd);
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
		return bookID;
	}
	
	public void listBooks(){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			List<Book> books = session.createQuery("FROM Book").list();
			books.forEach(book->{
				System.out.print(book.getId()+"\t");
				System.out.print(book.getName()+"\t");
				System.out.print(book.getAuthor()+"\t");
				System.out.print(book.getSellOut()+"\t");
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
	
	public void deleteBookByID(Integer id){
		Transaction tx = null;
		Session session = factory.openSession();
		try{
			tx = session.beginTransaction();
			Book book = session.get(Book.class, id);
			session.delete(book);
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
