package com.soudeep.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soudeep.dto.Student;

public interface IStudentRepo {
	public int insertRecord(Student student) throws SQLException;
	public Student readRecord(int id) throws SQLException;
	public int deleteRecord(int id) throws SQLException;
	public int updateRecord(Student student) throws SQLException;
}
