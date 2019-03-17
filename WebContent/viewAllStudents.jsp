<%@page import="com.kulchuri.bill.student.StudentDto"%>
<%@page import="com.kulchuri.bill.student.StudentDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="main_header.jsp"%>
	<%@ include file="accountant_menu.jsp"%>
	<div id="tables">
		<h1>All Branches</h1>
		<table style="width: 100%">
			<tr>
				<th>S.No.</th>
				<th>Name</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Gender</th>
				<th>DOJ</th>
				<th>Address</th>
				<th>Paid</th>
				<th>Balance</th>
				<th>Photo</th>
				<th>Operation</th>
			</tr>
			<%
				ArrayList<StudentDto> al = new StudentDao().getAllStudents();
				if (al != null) {
					int s = 0;
					for (StudentDto dto : al) {
						++s;
			%>
			<tr>
				<td><%=s%></td>
				<td><%=dto.getName()%></td>
				<td><%=dto.getEmail()%></td>
				<td><%=dto.getMno()%></td>
				<td><%=dto.getGender()%></td>
				<td><%=dto.getDoj()%></td>
				<td><%=dto.getAddress()%></td>
				<td><%=dto.getPaid()%></td>
				<td><%=dto.getBalance()%></td>
				<td><img alt=""
					src="data:image/jpeg;base64,<%=dto.getPhoto()%>"></td>
				<td><a href="updateStudent.jsp?sid=<%=dto.getSid()%>">Edit</a>
					<a href="deleteStudent.jsp?sid=<%=dto.getSid() %>">Delete</a></td>

			</tr>
			<%
				}
				}
			%>
		</table>
	</div>

</body>
</html>