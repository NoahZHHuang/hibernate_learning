package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;

	@ManyToOne
	@JoinColumn(name="class_id")
	private Class theClass;

	
	
	public Student() {
	}



	public Student(String name) {
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



	public Class getTheClass() {
		return theClass;
	}



	public void setTheClass(Class theClass) {
		this.theClass = theClass;
	}


	
	
	
	
	

}
