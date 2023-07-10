package com.dao;

import java.util.List;

import com.entity.BookOrder;

public interface BookOrderDao {

//	public int getOrderNo();
	
	public boolean saveOrder(List<BookOrder> list);
	
	public List<BookOrder> getBook(String email);
	
	public List<BookOrder> getAllOrder();
}
