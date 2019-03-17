<%@page import="com.kulchuri.bill.branch.BranchDao"%>
<%@page import="com.kulchuri.bill.branch.BranchDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kulchuri.bill.accountant.AccountantDto"%>
<%@page import="com.kulchuri.bill.accountant.AccountantDao"%>
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
	<jsp:useBean id="dto"
		class="com.kulchuri.bill.accountant.AccountantDto"></jsp:useBean>
	<jsp:setProperty property="*" name="dto" />
	<%
		if (new AccountantDao().updateAccountant(dto)) {
				response.sendRedirect("viewAllAccountants.jsp");
			} else {
				out.print("failed");
			}
		}
		String aid = request.getParameter("aid");
		if (aid != null) {
			int id = Integer.parseInt(aid);
			AccountantDto dto = new AccountantDao().getAccountant(id);
			if (dto != null) {
	%>
	<div style="width: 50%; margin-left: 25%">
		<form action="updateAccountant.jsp" method="post">
		<input type="hidden" name="aid" value="<%=aid%>">
		<input class="field" type="text" name="name" placeholder="Enter name" value="<%=dto.getName() %>"
				autofocus="" required=""> <input class="field" type="text"
				name="uname" placeholder="Enter user name" required="" value="<%=dto.getUname()%>"> <input
				class="field" type="password" name="pwd" value="<%=dto.getPwd() %>"
				placeholder="Enter password" required=""> <input
				class="field" type="text" name="mno" value="<%=dto.getMno() %>"
				placeholder="Enter mobile number" required=""> <input
				class="field" type="text" name="email" placeholder="Enter email" value="<%=dto.getEmail() %>"
				required=""> <input class="field" type="text" name="address"
				placeholder="Enter address" required="" value="<%=dto.getAddress()%>"> <input
				class="field" type="text" name="salary" placeholder="Enter salary" value="<%=dto.getSalary() %>"
				required=""> <input type="radio" name="gender" value="Male">Male
			<input type="radio" name="gender" value="Female">Female <select
				class="field" name="bid" required="">
				<option value="<%=dto.getBid() %>"><%=dto.getBname() %></option>
				<%
					ArrayList<BranchDto> al = new BranchDao().getAllBranches();
					if (al != null) {
						for (BranchDto bd : al) {
				%>
				<option value="<%=bd.getBid()%>"><%=bd.getName()%></option>
				<%
					}
					} else {
						response.sendRedirect("addBranch.jsp");
					}
				%>
			</select> <input type="submit" value="Update Accountant">
		
		</form>
	</div>
	<%
		} else {
				response.sendRedirect("viewAllAccountants.jsp");
			}
		}
	%>
</body>
</html>