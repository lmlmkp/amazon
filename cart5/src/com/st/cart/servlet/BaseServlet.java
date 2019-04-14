package com.st.cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class BaseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	protected void outJson(HttpServletResponse resp, Object object) {
		resp.setContentType("application/json;charset=utf-8");
		String json = JSONObject.toJSONString(object);

		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
