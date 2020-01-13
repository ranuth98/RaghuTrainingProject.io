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
<title>Edit Menu</title>
</head>
<body>
	<div class="topnav">
		<div class="home">truYum</div>
		<img src="images/truyum-logo-light.png"> <a
			href="ShowMenuItemListAdmin">Menu</a>
	</div>
	<h3>Edit Menu Item</h3>
	<form action="EditMenuItem" onsubmit="return validateMenuItemForm()"
		name="menuItemForm" method="post">
		<div class="body-content-color">
			<div class="form-field-spacing">
				<label for="name">Name</label> <input type="text"
					class="text-box text-box-title" name="name" id="name"
					value='${menuItem.name}'>
			</div>
		</div>
		<div>
			<div class="form-field-spacing2">
				<label for="price">Price(Rs.)</label> <input type="text"
					class="text-box" name="price" id="price" value='${menuItem.price}'>
			</div>
			<div class="form-field-spacing2">
				<label for="inStock">Active</label>
				<div>
					Yes <input type="radio" name="active" value="yes"
						<c:if test="${menuItem.active eq 'true'}">Checked</c:if>>
					No<input type="radio" name="active" value="no"
						<c:if test="${menuItem.active eq 'false'}">Checked</c:if>>
				</div>
			</div>
			<div class="form-field-spacing2">
				<label for="dateOfLaunch">Date of launch</label> <input type="text"
					class="text-name" name="dateOfLaunch"
					value=<fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${menuItem.dateOfLaunch}"/>>
			</div>
			<div class="form-field-spacing2">
				<label for="category">Category</label> <select name="category"
					class="dropdown">
					<option value="${menuItem.category}">${menuItem.category}</option>
					<option value="starters">Starters</option>
					<option value="main course">Main Course</option>
					<option value="dessert">Dessert</option>
					<option value="drinks">Drinks</option>
				</select>
			</div>
		</div>
		<div class="form-field-spacing2">
			<label for="freeDelivery">Free Delivery</label>
			<div>
				<input type="checkbox" name="freeDelivery"
					<c:if test="${menuItem.freeDelivery eq 'true'}">Checked</c:if>
					<c:if test="${menuItem.freeDelivery eq 'false'}"></c:if>>
			</div>
		</div>
		<div id="l1">
			<input type="submit" class="button success-button" value="Save">
		</div>
		<div>
			<input type="hidden" name="id" value="${menuItem.id}">
		</div>
	</form>
	<div class="footer">
		<h3>Copyright © 2019</h3>
	</div>
</body>
</html>