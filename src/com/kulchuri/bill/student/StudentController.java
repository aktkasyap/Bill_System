package com.kulchuri.bill.student;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 987777777)

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentDto dto = new StudentDto();

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String mno = req.getParameter("mno");
		String address = req.getParameter("address");
		String paid = req.getParameter("paid");
		String cid = req.getParameter("cid");
		// int bid=(Integer)req.getSession().getAttribute("bid");
		int bid = 0;
		dto.setName(name);
		dto.setMno(mno);
		dto.setAddress(address);
		dto.setCid(Integer.parseInt(cid));
		dto.setPaid(Float.parseFloat(paid));
		dto.setGender(gender);
		dto.setEmail(email);
		InputStream photo = req.getPart("photo").getInputStream();
		if (new StudentDao().addStudent(dto, photo)) {
			resp.sendRedirect("viewAllStudents.jsp");
		} else {
			resp.sendRedirect("addStudent.jsp?msg=Registration Failed");
		}
	}
}
