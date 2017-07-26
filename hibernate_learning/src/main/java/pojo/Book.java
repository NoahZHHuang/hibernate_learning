package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="book")

public class Book{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="author")
	private String author;
	
	@Column(name="sell_out")
	@Type(type="yes_no")
	//@Type(type="true_false")
	private boolean sellOut;
	
	@Column(name="price")
	private Double price;

	
	
	public Book() {

	}



	public Book(String name, String author, boolean sellOut, Double price) {
		this.name = name;
		this.author = author;
		this.sellOut = sellOut;
		this.price = price;
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



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public boolean getSellOut() {
		return sellOut;
	}



	public void setSellOut(boolean sellOut) {
		this.sellOut = sellOut;
	}
	
	
	
	
	
	
}