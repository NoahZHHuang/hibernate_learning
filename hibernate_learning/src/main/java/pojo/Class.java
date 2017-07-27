package pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;


@Entity
@Table(name="class")
public class Class {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="theClass", fetch=FetchType.EAGER) 
	//fetch=FetchType.EAGER means once the Class instance is loaded, the corresponding students will be fetched at the same time
	//fetch=FetchType.Lazy means only when the .getStudents() is called, then the students will be fetched.
	private Set<Student> students;
	
	public Class() {
	}


	public Class(String name) {
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<Student> getStudents() {
		return students;
	}


	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public void joinStudents(Student student){
		if(getStudents()==null){
			setStudents(new HashSet<Student>());
		}
		getStudents().add(student);
	}

	
	
	
}
