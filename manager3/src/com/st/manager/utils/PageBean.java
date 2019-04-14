package com.st.manager.utils;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
	
	private int currentPage = 1;  //当前页
	private int pageSize = 10; //每页多少条数据
	private int rowCount; //总数据量
	private int totalPage; //总页数
	private List<T> datas;
	private int roundNum = 2; //
	private List<Bar> pageBar;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize <= 0) {
			this.pageSize = 10;
		}else {
			this.pageSize = pageSize;
		}
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	/**
	 * 获取总页数 
	 * 29
	 * 30
	 * 31
	 */
	public int getTotalPage() {
		return (this.rowCount + this.pageSize - 1) / this.pageSize;
	}
	public int getRoundNum() {
		return roundNum;
	}
	public void setRoundNum(int roundNum) {
		this.roundNum = roundNum;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	public List<Bar> getPageBar() {
		List<Bar> list = new ArrayList<>();
		int total = getTotalPage(); //获取到总页数
		//  1 ... 3 4 5 6 7 ... 10
		//  1 2 3 4 5 ... 10
		//  1 2 3 4 5 6 ... 10
		//  1 ... 5 6 7 8 9 10
		//  1 ... 4 5 6 7 8 ... 10
		//  1 ... 6 7 8 9 10
		
		/**
		 * 1.首先将计算当前页的左边有哪些东西
		 * 2.设置当前页
		 * 3.设置当前页的后面有哪些东西
		 */
		/**
		 * 如果当前的前面是2, 那么从第一页开始，所有的页都可以点击
		 */
		if(currentPage - roundNum <= 2) {
			for(int i = 1; i <= currentPage - 1;i++) {
				Bar bar = new Bar(i + "", true);
				list.add(bar);
			}
		}else {
			Bar bar1 = new Bar(1 + "", true); //表示第一页
			list.add(bar1);
			Bar other = new Bar("...", false);
			list.add(other);
			for(int i = currentPage - roundNum; i < currentPage; i++) {
				Bar bar = new Bar(i + "", true);
				list.add(bar);
			}
		}
		
		Bar currentBar = new Bar(currentPage + "", false);
		list.add(currentBar);
		
		/***
		 * 三个点出现  
		 */
		if(total - (currentPage + roundNum) >= 2) {
			for(int i = currentPage + 1; i <= currentPage + roundNum; i++) {
				Bar bar = new Bar(i + "", true);
				list.add(bar);
			}
			Bar other = new Bar("...", false);
			list.add(other);
			
			Bar lastBar = new Bar(total + "", true);
			list.add(lastBar);
		}else { //当前页的下一页到尾页都出现
			for(int i = currentPage + 1; i <= total; i++) {
				Bar bar = new Bar(i + "", true);
				list.add(bar);
			}
		}
		
		return list;
	}
}
