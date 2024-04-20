package com.soudeep.service;

import java.sql.SQLException;

import com.soudeep.dto.Student;
import com.soudeep.factory.StudentFactory;

public class StudentServiceImpl implements IStudentService {

	@Override
	public int insertRecord(Student student) throws SQLException {
		return StudentFactory.getStudentRepo().insertRecord(student);
	}

	@Override
	public Student readRecord(int id) throws SQLException {
		return StudentFactory.getStudentRepo().readRecord(id);
	}

	@Override
	public int deleteRecord(int id) throws SQLException {
		return StudentFactory.getStudentRepo().deleteRecord(id);
	}

	@Override
	public int updateRecord(Student student) throws SQLException {
		return StudentFactory.getStudentRepo().updateRecord(student);
	}

	

}
