package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.EmployeeDAO;
import pojo.Employee;

public class EmployeeDaoTest {

	private static SessionFactory factory;
	private static EmployeeDAO employeeDAO;
	
	@BeforeClass
	public static void setUp(){
		factory = new Configuration().configure().buildSessionFactory();
		employeeDAO = new EmployeeDAO(factory);
	}
	
	@After
	public void tearDown(){
		employeeDAO.deleteEmployeeAll();
	}
	
	@Test
	public void testAddEmployee(){
		System.out.println("\nTest Add Employee...");
		Employee employee = new Employee("Noah", "Huang", 10000);
		employeeDAO.addEmployee(employee);
		employeeDAO.listEmployees();
	}
	
	@Test
	public void testUpdateEmployee(){
		System.out.println("\nTest Update Employee...");
		Employee employee = new Employee("Allice", "Zheung", 20000);
		Integer id = employeeDAO.addEmployee(employee);
		employeeDAO.listEmployees();
		Employee employeeToUpdate = new Employee("Allie", "Zhang", 30000);
		employeeToUpdate.setId(id);
		employeeDAO.updateEmployee(employeeToUpdate);
		employeeDAO.listEmployees();
		employeeToUpdate.setFirstName("Noah");
		employeeToUpdate.setLastName("Huang");
		employeeToUpdate.setSalary(40000);
		employeeDAO.updateEmployeeByHql(employeeToUpdate);
		employeeDAO.listEmployees();
		/*
		 compare updateEmployee and updateEmployeeByHql
		 updateEmployee: call session.get() to get the persistent object first, then modify this object, finally call session.save() to save. It will generate 2 sql statements, one select & one update.
		 updateEmployeeByHql : only generate one update sql 
		 * */
		
	}
	
	@Test
	public void testDeleteEmployeeByID(){
		System.out.println("\nTest Delete Employee By ID...");
		Employee employee = new Employee("Peggy", "Huang", 20000);
		Integer id = employeeDAO.addEmployee(employee);
		employeeDAO.listEmployees();
		employeeDAO.deleteEmployeeByID(id);
		employeeDAO.listEmployees();
	}
	
	
}
