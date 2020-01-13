<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/script.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="style/style.css">
<title>Admin Menu List</title>
</head>
<body>
	<div class="topnav">
		<div class="home">truYum</div>
		<img src="images/truyum-logo-light.png"> <a
			href="ShowMenuItemListAdmin">Menu</a>
	</div>
	<div>
		<h1 id="s1">Menu Items</h1>
		<table id="rh">
			<tr style="width: 60%">
				<th id="g1">Name</th>
				<th>Price</th>
				<th>Active</th>
				<th>Date of Launch</th>
				<th>Category</th>
				<th>Free Delivery</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${menuItemList}" var="menuItem">
				<tr style="text-align: center">
					<td id="g1">${menuItem.name}</td>
					<fmt:setLocale value="en_IN" scope="request" />
					<td><fmt:formatNumber type="currency"
							value="${menuItem.price}" /></td>
					<td><c:if test="${menuItem.active}">Yes</c:if> <c:if
							test="${!menuItem.active}">No</c:if></td>
					<td><fmt:formatDate type="date" pattern="dd/MM/yyyy"
							value="${menuItem.dateOfLaunch}" /></td>
					<td>${menuItem.category}</td>
					<td><c:if test="${menuItem.freeDelivery}">Yes</c:if> <c:if
							test="${!menuItem.freeDelivery}">No</c:if></td>
					<td><a href="ShowEditMenuItem?id=${menuItem.id}">Edit</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="footer">
		<h3>Copyright � 2019</h3>
	</div>
</body>
</html>