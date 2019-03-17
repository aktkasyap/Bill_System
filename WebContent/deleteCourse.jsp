<%@page import="com.kulchuri.bill.course.CourseDao"%>
<%
String cid = request.getParameter("cid");
if(cid != null) {
	int id = Integer.parseInt(cid);
	new CourseDao().deleteCourse(id);
	response.sendRedirect("viewAllCourses.jsp");
}else {
response.sendRedirect("viewAllCourses.jsp");
}
%>