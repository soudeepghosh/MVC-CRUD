package com.soudeep.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soudeep.dto.Student;
import com.soudeep.factory.StudentFactory;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("*.do")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doOperation(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doOperation(request, response);
	}
	
	private void doOperation(HttpServletRequest request, HttpServletResponse response) {
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		out.println("<body align='center' bgcolor='#BED7DC'>");
		// CSS
		out.println(	
				"<style>\r\n"
				+ "        table, tr, td,th{"
				+ "            border: 1px solid black;"
				+ "            border-collapse: collapse;"
				+ "        }"
				+ "        table{"
				+ "            width: 50%;"
				+ "            height: 150px;"
				+ "            text-align: center;"
				+ "        }"
				+ "        tr:hover{"
				+ "            background-color: lightblue;"
				+ "        }"
				+ "        th{"
				+ "            background-color: orange;"
				+ "        }"
				+ "    </style>"
				);
		
		String info = request.getRequestURI();
		
		if(info.endsWith("insert.do")) {
			
			doInsert(request, response, out);
			
		} else if(info.endsWith("read.do")) {
			
			doRead(request, response, out);
			
		} else if(info.endsWith("delete.do")) {
			
			doDelete(request, response, out);
			
		} else if(info.endsWith("forwardupdate.do")) {
			
			doUpdate(request, response, out);
			
		} else if(info.endsWith("update.do")) {
			
			doPerformUpdate(request, response, out);
			
		}
		
		out.close();
	}

	private void doInsert(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		Student student = new Student();
		
		Integer sid = null;
		try {
			sid = Integer.parseInt(request.getParameter("sid"));
		} catch (NumberFormatException e) {
			out.println("<h1 style='color:red; text-align:center;'>Invalid ID...</h1>");
			e.printStackTrace();
		}
		String sname = request.getParameter("sname");
		Integer sage = null;
		try {
			sage = Integer.parseInt(request.getParameter("sage"));
		} catch (NumberFormatException e) {
			out.println("<h1 style='color:red; text-align:center;'>Invalid Age...</h1>");
			e.printStackTrace();
		}
		String saddress = request.getParameter("saddress");
		
		student.setSid(sid);
		student.setSname(sname);
		student.setSage(sage);
		student.setSaddress(saddress);
		
		if (sname == null || sname == null || sage == null || saddress == null) {
			out.println("<h1 style='color:red; text-align:center;'>Please enter all the details carefully to insert...</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("./insert.html");
			try {
				rd.include(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			int rowAffected = StudentFactory.getStudentService().insertRecord(student);
			if(rowAffected!=0) {
				out.println("<h1 style='color:orange; text-align:center;'>Insertion Successful</h1>");
			}else {
				out.println("<h1 style='color:red; text-align:center;'>Insertion failure</h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1 style='color:red; text-align:center;'>OOPs! Something went wrong.. Looks like ID already exists.. Enter the details carefully..</h1>");
		}
		
	}

	private void doRead(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		Integer sid = null;
		try {
			sid = Integer.parseInt(request.getParameter("sid"));
		} catch (NumberFormatException e) {
			out.println("<h1 style='color:red; text-align:center;'>Please enter a valid ID...</h1>");
			e.printStackTrace();
		}
		Student student = null;
		try {
			student = StudentFactory.getStudentService().readRecord(sid);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (sid != null) {
			if (student != null) {
				out.println("<h1 style='color:green; text-align:center;'>STUDENT RECORDS</h1> <br>");
				out.println("<table border='1' align='center'>");
				out.println("<tr>");
					out.println("<th>ID</th>");
					out.println("<th>NAME</th>");
					out.println("<th>AGE</th>");
					out.println("<th>ADDRESS</th>");
				out.println("</tr>");
				out.println("<tr>");
					out.println("<td>"+ student.getSid() +"</td>");
					out.println("<td>"+ student.getSname() +"</td>");
					out.println("<td>"+ student.getSage() +"</td>");
					out.println("<td>"+ student.getSaddress() +"</td>");
				out.println("</tr>");
				out.println("</table>");
			} else {
				out.println("<h1 style='color:orange; text-align:center;'>No Data available for ID : "+sid+"</h1>");
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("./read.html");
			try {
				rd.include(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void doDelete(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		Integer sid = null;
		try {
			sid = Integer.parseInt(request.getParameter("sid"));
		} catch (NumberFormatException e) {
			out.println("<h1 style='color:red; text-align:center;'>Please enter a valid ID...</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("./delete.html");
			try {
				rd.include(request, response);
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			e.printStackTrace();
		}
		
		if (sid != null) {
			try {
				int rowAffected = StudentFactory.getStudentService().deleteRecord(sid);
				if(rowAffected !=0) {
					out.println("<h1 style='color:violet; text-align:center;'>Student Record(with id: "+sid+") is successfully deleted...</h1>");
				} else {
					out.println("<h1 style='color:red; text-align:center;'>ID not available to delete the data...</h1>");
				}
			} catch (SQLException e) {
				out.println("<h1 style='color:red; text-align:center;'>OOPs! Something went wrong! Please try again later.</h1>");
				e.printStackTrace();
			}
		}	
	}
	
	private void doUpdate(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		Integer sid = Integer.parseInt(request.getParameter("sid"));
		Student student = null;
		try {
			student = StudentFactory.getStudentRepo().readRecord(sid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (student == null) {
			out.println("<h1 style='color:orange; text-align:center;'>No Data available for ID : "+sid+"</h1>");
			return;
		}
		String sname = student.getSname();
		Integer sage = student.getSage();
		String saddress = student.getSaddress();
		out.println("<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "    <link rel=\"stylesheet\" href=\"style/style.css\">"
				+ "</head>");
		out.println("<div class=\"main\">"
				+ "        <div class=\"register\">"
				+ "            <h2>Update Student Info</h2>"
				+ "            <form method=\"POST\" id=\"register\" action=\"./update.do\">"
				+ "                <label>Student ID </label> <br>"
				+ "                <input type=\"number\" name=\"sid\" id=\"name\" value="+sid+" readonly>"
				+ "                <br><br>"
				+ "                <label>Name </label> <br>"
				+ "                <input type=\"text\" name=\"sname\" id=\"name\" value="+sname+" required>"
				+ "                <br><br>"
				+ "                <label>Age </label> <br>"
				+ "                <input type=\"number\" name=\"sage\" id=\"name\" value="+sage+" required>"
				+ "                <br><br>"
				+ "                <label>Address </label> <br>"
				+ "                <input type=\"text\" name=\"saddress\" id=\"name\" value="+saddress+" required>"
				+ "                <br><br>"
				+ "                <input type=\"submit\" id=\"submit\" value=\"Update\">"
				+ "            </form>"
				+ "        </div><!--end register-->"
				+ "    </div><!--end main-->");
		
	}
	
	private void doPerformUpdate(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		Student student = new Student();
		
		Integer sid = Integer.parseInt(request.getParameter("sid"));
		String sname = request.getParameter("sname");
		Integer sage = Integer.parseInt(request.getParameter("sage"));
		String saddress = request.getParameter("saddress");
		
		student.setSid(sid);
		student.setSname(sname);
		student.setSage(sage);
		student.setSaddress(saddress);
		
		try {
			int rowAffected = StudentFactory.getStudentService().updateRecord(student);
			if(rowAffected!=0) {
				out.println("<h1 style='color:orange; text-align:center;'>Updation Successful</h1>");
			}else {
				out.println("<h1 style='color:red; text-align:center;'>OOPS! Updation failure! Please try again later.</h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1 style='color:red; text-align:center;'>OOPs! Something went wrong.. Looks like ID already exists.. Enter the details carefully..</h1>");
		}
		
	}
}
