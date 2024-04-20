package com.soudeep.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.soudeep.dto.Student;
import com.soudeep.utility.DBUtil;

public class StudentRepoImpl implements IStudentRepo {
	
	private static Connection connection = null;

	static {
		try {
			connection = DBUtil.getDBConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insertRecord(Student student) throws SQLException {
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO student(`sid`,`sname`,`sage`,`saddress`) values(?,?,?,?)";
		if (connection != null) {
			pstmt = connection.prepareStatement(insertQuery);
		}
		if (pstmt != null) {
			pstmt.setInt(1, student.getSid());
			pstmt.setString(2, student.getSname());
			pstmt.setInt(3, student.getSage());
			pstmt.setString(4, student.getSaddress());
		}
		
		return pstmt.executeUpdate();
	}

	@Override
	public Student readRecord(int id) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Student student = null;
		String selectQuery = "SELECT sid, sname, sage, saddress FROM STUDENT where sid = ?";
		if (connection != null) {
			pstmt = connection.prepareStatement(selectQuery );
		}
		if(pstmt != null) {
			pstmt.setInt(1, id);
			result = pstmt.executeQuery();
		}
		if (result != null) {
			if(result.next()) {
				student = new Student();
				student.setSid(result.getInt(1));
				student.setSname(result.getString(2));
				student.setSage(result.getInt(3));
				student.setSaddress(result.getString(4));
			}
		}
		
		return student;
	}

	@Override
	public int deleteRecord(int id) throws SQLException {
		PreparedStatement pstmt = null;
		String sqlDelete = "DELETE FROM student where sid = ?";
		if (connection != null) {
			pstmt = connection.prepareStatement(sqlDelete);
			pstmt.setInt(1, id);
		}
		return pstmt.executeUpdate();
	}

	@Override
	public int updateRecord(Student student) throws SQLException {
		PreparedStatement pstmt = null;
		String sqlUpdate = "UPDATE student SET sname = ?, sage = ?, saddress = ? WHERE sid = ?";
		if (connection != null) {
			pstmt = connection.prepareStatement(sqlUpdate);
			pstmt.setString(1, student.getSname());
			pstmt.setInt(2, student.getSage());
			pstmt.setString(3, student.getSaddress());
			pstmt.setInt(4, student.getSid());
		}
		return pstmt.executeUpdate();
	}

	

}
