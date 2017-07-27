package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ClassAndStudentDAO;
import pojo.Student;
import pojo.Class;

public class ClassAndStudentDaoTest {
	private static SessionFactory factory;
	private static ClassAndStudentDAO classAndStudentDAO ;
	

	@BeforeClass
	public static void setUp(){
		factory = new Configuration()
				.addPackage("pojo")
				.addAnnotatedClass(Class.class).addAnnotatedClass(Student.class)
				.configure().buildSessionFactory();
		classAndStudentDAO = new ClassAndStudentDAO(factory);
	}
	
	
	@After
	public void tearDown(){
		classAndStudentDAO.deleteClassAll();
	}
	
	
	@Test
	public void testAddClassWithStudent(){
		System.out.println("\ntest Add Class with student...");
		Class theClass = new Class("Class One - Grade Two");
		Student studentA = new Student("Noah");
		Student studentB = new Student("Allie");
		//student has to set theClass, or it will break the foreign key constraint
		//null value in column "class_id" violates not-null constraint
		//Failing row contains (66, Noah, null).
		studentA.setTheClass(theClass);
		studentB.setTheClass(theClass);
		theClass.joinStudents(studentA);
		theClass.joinStudents(studentB);
		classAndStudentDAO.addClass(theClass);
		classAndStudentDAO.listClassAndStudents();
	}
	
	@Test
	public void testAddClassWithoutStudent(){
		System.out.println("\ntest Add Class without student...");
		Class theClass = new Class("Class One - Grade Two");
		classAndStudentDAO.addClass(theClass);
		classAndStudentDAO.listClassAndStudents();
	}
	
	@Test
	public void testUpdateClass(){
		System.out.println("\ntest Update Class...");
		Class thClass = new Class("Class 1 Grade 3");
		Student studentA = new Student("Peggy");
		Student studentB = new Student("George");
		studentA.setTheClass(thClass);
		studentB.setTheClass(thClass);
		thClass.joinStudents(studentA);
		thClass.joinStudents(studentB);
		Integer classId = classAndStudentDAO.addClass(thClass);
		classAndStudentDAO.listClassAndStudents();
		Class classToUpdate = new Class("Class XXX Grade YYY");
		classToUpdate.setId(classId);
		classAndStudentDAO.updateClass(classToUpdate);
		classAndStudentDAO.listClassAndStudents();
	}

}
