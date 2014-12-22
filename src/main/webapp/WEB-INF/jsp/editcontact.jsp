
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Spring 3 MVC Series - Edit Contact </title>
	<style type="text/css">
		body {
			font-family: sans-serif;
		}
		.data, .data td {
			border-collapse: collapse;
			width: 100%;
			border: 1px solid #aaa;
			margin: 2px;
			padding: 2px;
		}
		.data th {
			font-weight: bold;
			background-color: #5C82FF;
			color: white;
		}
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<h2>Contact Manager</h2>
<c:if  test="${contact!=null}">
<form:form method="post" action="/edit.html" commandName="contact">
<form:hidden path="id"   />
	<table>
	<tr>
		<td><form:label path="firstname"><spring:message code="label.firstname"/></form:label></td>
		<td><form:input path="firstname" value="${firstname}" /></td> 
	</tr>
	<tr>
		<td><form:label path="lastname"><spring:message code="label.lastname"/></form:label></td>
		<td><form:input path="lastname" value="${lastname}"/></td>
	</tr>
	<tr>
		<td><form:label path="email"><spring:message code="label.email"/></form:label></td>
		<td><form:input path="email" value="${email}" /></td>
	</tr>
	<tr>
		<td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
		<td><form:input path="telephone" value="${telephone}" /></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].houseNo"><spring:message code="label.houseNo"/></form:label></td>
		<td><form:input path="addressList[0].houseNo"  value="${addressList[0].houseNo}"/></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].locality"><spring:message code="label.locality"/></form:label></td>
		<td><form:input path="addressList[0].locality" value="${addressList[0].locality}" /></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].city"><spring:message code="label.city"/></form:label></td>
		<td><form:input path="addressList[0].city" value="${addressList[0].city}" /></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].zipCode"><spring:message code="label.zipCode"/></form:label></td>
		<td><form:input path="addressList[0].zipCode" value="${addressList[0].zipCode}" /></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].state"><spring:message code="label.state"/></form:label></td>
		<td><form:input path="addressList[0].state" value="${addressList[0].state}" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="label.editcontact"/>"/>
		</td>
	</tr>
</table>	
</form:form>

</c:if>
</body>
</html>