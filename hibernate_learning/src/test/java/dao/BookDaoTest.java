package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.BookDAO;
import pojo.Book;

public class BookDaoTest {
	private static SessionFactory factory;
	private static BookDAO bookDAO ;
	

	@BeforeClass
	public static void setUp(){
		factory = new Configuration().configure().buildSessionFactory();
		//if no <mapping class = "pojo.Book"/> in hibernate.cfg.xml, then need to do like :
		//factory = new Configuration().addPackage("pojo").addAnnotatedClass(Book.class).configure().buildSessionFactory();
		bookDAO = new BookDAO(factory);
	}
	
	
	@Test
	public void testAddBook(){
		System.out.println("\nTest Add Book...");
		Book bookToAdd = new Book("Java Code Design", "Someone", false, 12.5);
		Integer id = bookDAO.addBook(bookToAdd);
		bookDAO.listBooks();
		bookDAO.deleteBookByID(id);
		bookDAO.listBooks();
	}
}
