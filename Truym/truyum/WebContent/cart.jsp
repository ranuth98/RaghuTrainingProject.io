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
<title>Cart</title>
</head>
<body>
	<div>Menu Item</div>
	<div class="topnav">
		<div class="home">truYum</div>
		<img src="images/truyum-logo-light.png"> <a href="ShowCart">Cart</a>
		<a href="ShowMenuItemListCustomer">Menu</a>
	</div>
	<div>
		<h1 id="s1">Menu Items</h1>
		<table id="rh">
			<c:if test="${deleteCartStatus}">
				<div class="noti1">Item Removed Successfully</div>
			</c:if>
			<tr style="width: 60%">
				<th id="g1">Name</th>
				<th>Free Delivery</th>
				<th>Price</th>
				<th>Category</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${cart.menuItemList}" var="menuItem">
				<tr style="text-align: center">
					<td id="g1">${menuItem.name}</td>
					<td><c:if test="${menuItem.freeDelivery}">Yes</c:if> <c:if
							test="${!menuItem.freeDelivery}">No</c:if></td>
					<fmt:setLocale value="en_IN" scope="request" />
					<td><fmt:formatNumber type="currency"
							value="${menuItem.price}" /></td>
					<td>${menuItem.category}</td>
					<td><a href="RemoveCart?id=${menuItem.id}">Delete</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td class="total">Total</td>
				<td class="total"><fmt:setLocale value="en_IN" scope="request" />
					<fmt:formatNumber type="currency" value="${cart.total}" /></td>
				<td></td>
		</table>
	</div>
	<div class="footer">
		<h3>Copyright © 2019</h3>
	</div>
</body>
</html>