<%@page import="com.kulchuri.bill.branch.BranchDao"%>
<%@page import="com.kulchuri.bill.branch.BranchDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kulchuri.bill.course.CourseDto"%>
<%@page import="com.kulchuri.bill.course.CourseDao"%>
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
	<%@ include file="admin_menu.jsp"%>

	<%
		if (request.getMethod().equalsIgnoreCase("post")) {
	%>
	<jsp:useBean id="dto" class="com.kulchuri.bill.course.CourseDto"></jsp:useBean>
	<jsp:setProperty property="*" name="dto" />
	<%
		if (new CourseDao().updateCourse(dto)) {
				response.sendRedirect("viewAllCourses.jsp");
			} else {
				out.print("failed");
			}
		}
		String cid = request.getParameter("cid");
		if (cid != null) {
			int id = Integer.parseInt(cid);
			CourseDto dto = new CourseDao().getCourse(id);
			if (dto != null) {
	%>
	<div style="width: 50%; margin-left: 25%">
		<form action="updateCourse.jsp" method="post">
			<input type="hidden" name="cid" value="<%=cid%>"> <input
				class="field" type="text" name="name" placeholder="Enter name" value="<%=dto.getName() %>"
				required="" > <input class="field" type="text" value="<%=dto.getFees() %>"
				name="fees" placeholder="Enter fees" required=""> <select
				class="field" name="bid">
				<option value="<%=dto.getBid() %>"><%=dto.getBname() %></option>
				<%
					ArrayList<BranchDto> al = new BranchDao().getAllBranches();
							for (BranchDto dto1 : al) {
				%>
				<option value="<%=dto1.getBid()%>"><%=dto1.getName()%></option>
				<%
					}
				%>
			</select> <input type="submit" value="Update Course">

		</form>
	</div>
	<%
		} else {
				response.sendRedirect("viewAllCourses.jsp");
			}
		}
	%>
</body>
</html>