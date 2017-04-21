package com.vasudev.common;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vasudev.book.dao.BookDAO;
import com.vasudev.book.model.Book;


public class App {

	
	    public static void main( String[] args )
	    {
	    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	    	 
	    	   BookDAO bookDAO = (BookDAO) context.getBean("bookDAO");
	           Book book = new Book(11, "C programming"," Brain", "Not-Available");
	           bookDAO.insert(book);
	       	
	           Book book1 = bookDAO.findByBookId(5);
	           System.out.println(book1);
	        
	        List<Book> bkList = bookDAO.getAll();
			System.out.println(bkList);
	        
	    }
	}



