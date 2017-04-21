package com.vasudev.book.dao;

import java.util.List;

import com.vasudev.book.model.Book;

public interface BookDAO {
	
	public List<Book> getAll();

	public Book findByBookId(int bookid);
	
	public void insert(Book book);
	
}
