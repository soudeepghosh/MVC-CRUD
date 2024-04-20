package com.soudeep.factory;

import com.soudeep.repository.IStudentRepo;
import com.soudeep.repository.StudentRepoImpl;
import com.soudeep.service.IStudentService;
import com.soudeep.service.StudentServiceImpl;

public class StudentFactory {
	
	private static StudentRepoImpl studentRepo = null;
	private static StudentServiceImpl studentService = null;
	
	private StudentFactory() {}
	
	public static IStudentRepo getStudentRepo() {
		if(studentRepo==null) {
			studentRepo = new StudentRepoImpl();
		}
		return studentRepo;
	}
	
	public static IStudentService getStudentService() {
		if(studentRepo==null) {
			studentService = new StudentServiceImpl();
		}
		return studentService;
	}
}
