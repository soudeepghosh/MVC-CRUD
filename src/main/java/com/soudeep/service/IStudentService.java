package com.soudeep.service;

import java.sql.SQLException;

import com.soudeep.dto.Student;

public interface IStudentService {
	public int insertRecord(Student student) throws SQLException;
	public Student readRecord(int id) throws SQLException;
	public int deleteRecord(int id) throws SQLException;
	public int updateRecord(Student student) throws SQLException;
}
