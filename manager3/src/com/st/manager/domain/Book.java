package com.st.manager.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	 private int id;
	 private String name;
	 private String cover;
	 private BigDecimal price;
	 private String category;
	 private int categoryId;
	 private Timestamp createTime;
	 private Timestamp updateTime;
	 
	 public Book(int id, String name, BigDecimal price, String category, Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.createTime = createTime;
		this.updateTime = updateTime;
	 }

	 public Book(int id, String name, BigDecimal price, int categoryId, String cover) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
		this.cover = cover;
	 }
}
