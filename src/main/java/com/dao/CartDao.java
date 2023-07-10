package com.dao;

import java.util.List;

import com.entity.Cart;

public interface CartDao {

	public boolean addCart(Cart cart);
	
	public List<Cart> getBookByUser(int userid);
	
	public boolean deleteBook(int bid,int uid,int cid);
}
