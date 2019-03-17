<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>

	<%@ include file="main_header.jsp"%>
	<%@ include file="main_menu.jsp"%>
	<div style="width: 50%; margin-left: 25%; margin-top: 5%">
		<%
			if (request.getMethod().equalsIgnoreCase("post")) {
				String uname = request.getParameter("uname");
				String pwd = request.getParameter("pwd");
				if (uname.equals("admin") && pwd.equals("admin")) {
					session.setAttribute("uname", uname);
					response.sendRedirect("admin_home.jsp");
				} else {
					out.print("<script>alert('Please Enter valid Login Details')</script>");
				}
			}
		%>

		<form action="login.jsp" method="post">
			<input class="field" type="text" name="uname"
				placeholder="Enter username" required="" autofocus="autofocus"> <input
				class="field" type="password" name="pwd"
				placeholder="Enter password" required=""> <input
				type="submit" value="Signin">
		</form>
	</div>
</body>
</html>