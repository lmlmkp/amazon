package com.st.cart.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bar {
	private String content;  //表示的是分页按钮上展示的文字
	private boolean active;  //表示按钮是否可点
}
