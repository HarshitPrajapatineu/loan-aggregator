package com.quikloan.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.quikloan.server.dto.*;
import com.quikloan.server.entity.User;
import com.quikloan.server.repository.DatabaseConnector;

public class LoginDAO {

	private Connection connection;

	public LoginDAO() {

	}

	public UserSession validateLogin(String username, String password) {

		PreparedStatement pst = null;
		UserSession userSession = null;

		try {
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("SELECT * FROM users WHERE username=? and password=MD5(?)");
			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String usernameFromDB = rs.getString("username");
				String emailFromDB = rs.getString("email");
				String roleFromDB = rs.getString("role");

				userSession = new UserSession(usernameFromDB, emailFromDB, roleFromDB);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userSession;

	}

	public boolean registerUser(UserDTO user) 
	{
		boolean flag=false;PreparedStatement pst = null;
		try 
		{
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("INSERT INTO users(username,firstname,lastname,address,gender,email,phone,password,role) value(?,?,?,?,?,?,?,MD5(?),?)");
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getFirstname());
			pst.setString(3, user.getLastname());
			pst.setString(4, user.getAddress());
			pst.setString(5, user.getGender());
			pst.setString(6, user.getEmail());
			pst.setString(7, user.getPhone());
			pst.setString(8, user.getPassword());
			pst.setString(9, user.getRole());
			int row = pst.executeUpdate();
			if(row > 0) 
			{
				flag=true;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
			try 
			{
				pst.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return flag;
	}

	public UserDTO getUser(String userName) 
	{
		PreparedStatement pst = null;
		UserDTO userRegistration = new UserDTO();
		try 
		{
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("SELECT * FROM users WHERE username = (?)");
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String emailId = rs.getString("email");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String role = rs.getString("role");

				userRegistration = new UserDTO(username, firstname, lastname, emailId, phone, role);
				userRegistration.setAddress(address);
			}
			return userRegistration;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
			try 
			{
				pst.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return userRegistration;
	}

	public boolean editUser(UserDTO user) 
	{
		boolean flag=false;PreparedStatement pst = null;
		try 
		{
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("UPDATE users "
					+ "SET firstname = (?), lastname = (?), email = (?), phone =(?), address=(?), gender=(?)"
					+ "WHERE username = (?);");
			pst.setString(1, user.getFirstname());
			pst.setString(2, user.getLastname());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getPhone());
			pst.setString(5, user.getAddress());
			pst.setString(6, user.getGender());
			pst.setString(7, user.getUsername());
			int row = pst.executeUpdate();
			if(row > 0) 
			{
				flag=true;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
			try 
			{
				pst.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean updatePassword(UserDTO user) 
	{
		boolean flag=false;PreparedStatement pst = null;
		try 
		{
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("UPDATE users "
					+ "SET password = MD5(?)"
					+ "WHERE username = (?);");
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getUsername());
			int row = pst.executeUpdate();
			if(row > 0) 
			{
				flag=true;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
			try 
			{
				pst.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean deleteUser(String username) 
	{
		boolean flag=false;PreparedStatement pst = null;
		try 
		{
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("DELETE from users "
					+ "WHERE username = (?);");
			pst.setString(1, username);
			int result = pst.executeUpdate();
			if( result > 0) 
			{
				flag=true;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
			try 
			{
				pst.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return flag;
	}

}
