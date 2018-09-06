package com.yjq.ssh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;




@Entity
@Table(name="product",catalog="ssh")
public class Product {
	@Id
	@GeneratedValue(generator="id")
	@GenericGenerator(name="id",strategy="native")
	private int id;
	
	@Column(length=100)
	
	private String name;//商品名称
	private double price;//商品价格
	public Product() {
		super();
	}
	
	public Product( String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
