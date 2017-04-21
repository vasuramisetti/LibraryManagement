package com.vasudev.book.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.vasudev.book.dao.BookDAO;
import com.vasudev.book.model.Book;

public class JdbcBookDAO implements BookDAO
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Book book){
		
		String sql = "INSERT INTO BOOKS " +
				"(Id, Title, Author,Status) VALUES (?, ?, ?,?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, book.getBookid());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getStatus());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public Book findByBookId(int bookid){
		
		String sql = "SELECT * FROM BOOKS WHERE Id = ?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bookid);
			Book book = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book(
						rs.getInt("Id"),
						rs.getString("Title"), 
						rs.getString("Author"), 
						rs.getString("Status")
				);
			}
			rs.close();
			ps.close();
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	
	
	
	public List<Book> getAll() {
		String query = "select Id, Title,Author,Status  from books";
		List<Book> bkList = new ArrayList<Book>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Book bk = new Book(0, query, query, query);
				bk.setBookid(rs.getInt("Id"));
				bk.setTitle(rs.getString("Title"));
				bk.setAuthor(rs.getString("Author"));
				bk.setStatus(rs.getString("Status"));
				bkList.add(bk);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bkList;
	}
	
	
	
	
	
	
}


