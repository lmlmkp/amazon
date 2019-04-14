package com.st.cart.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	 private int id;
	 private String name;
	 private BigDecimal price;
	 private String cover;
}
