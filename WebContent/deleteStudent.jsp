<%@page import="com.kulchuri.bill.student.StudentDao"%>
<%
String sid = request.getParameter("sid");
if(sid != null) {
	int id = Integer.parseInt(sid);
	new StudentDao().deleteStudent(id);
	response.sendRedirect("viewAllStudents.jsp");
}else {
response.sendRedirect("viewAllStudents.jsp");
}
%>