
<%
	String uname = (String) session.getAttribute("uname");
	if (uname == null) {
		response.sendRedirect("login.jsp");
	}
%>

<ul>
	<li><a href="admin_home.jsp">Home</a></li>
	<li><a href="addBranch.jsp">Add Branch</a></li>
	<li><a href="viewAllBranches.jsp">View Branches</a></li>
	<li><a href="addCourse.jsp">Add Course</a></li>
	<li><a href="viewAllCourses.jsp">View Courses</a></li>
	<li><a href="addAccountant.jsp">Add Accountant</a></li>
	<li><a href="viewAllAccountants.jsp">View Accountants</a></li>
	<li><a href="addStudent.jsp">Add Student</a></li>
	<li><a href="viewAllStudents.jsp">View Students</a></li>
	<li><a href="logout.jsp">SignOut</a></li>






</ul>