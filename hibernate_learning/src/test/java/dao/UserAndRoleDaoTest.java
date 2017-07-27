package dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.UserAndRoleDAO;
import pojo.Role;
import pojo.User;


public class UserAndRoleDaoTest {
	private static SessionFactory factory;
	private static UserAndRoleDAO userAndRoleDAO ;
	

	@BeforeClass
	public static void setUp(){
		factory = new Configuration()
				.addPackage("pojo")
				.addAnnotatedClass(User.class).addAnnotatedClass(Role.class)
				.configure().buildSessionFactory();
		userAndRoleDAO = new UserAndRoleDAO(factory);
	}
	
	@After
	public void tearDown(){
		userAndRoleDAO.deleteAllUser();
		userAndRoleDAO.deleteAllRole();
	}

	@Test
	public void testAddRoleWithUsers(){
		System.out.println("\ntest Add a role with 2 users...");
		User userA = new User();userA.setName("Allie");
		User userB = new User();userB.setName("Peggy");
		Set<User> users = new HashSet<>();
		users.add(userA);
		users.add(userB);
		Role roleA = new Role(); 
		roleA.setName("HR"); 
		roleA.setUsers(users);
		userAndRoleDAO.addRole(roleA);
		userAndRoleDAO.listRoles();
	}
	
	@Test
	public void testAddRoleWithoutUsers(){
		System.out.println("\ntest Add a role without users...");
		Role roleA = new Role(); 
		roleA.setName("CEO"); 
		userAndRoleDAO.addRole(roleA);
		userAndRoleDAO.listRoles();
	}
	
	
	@Test
	public void testAddUsersWithRoles(){
		System.out.println("\ntest Add a user with 2 roles...");
		Role roleA = new Role(); roleA.setName("CTO"); 
		Role roleB = new Role(); roleB.setName("Developer");
		Set<Role> roles = new HashSet<>();
		roles.add(roleA);
		roles.add(roleB);		
		User userA = new User();
		userA.setName("Noah");
		userA.setRoles(roles);
		userAndRoleDAO.addUser(userA);
		userAndRoleDAO.listUsers();
	}

}
