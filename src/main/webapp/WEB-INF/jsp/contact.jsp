<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Spring 3 MVC Series - Contact Manager</title>
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
</head>
<body>

<h2>Contact Manager</h2>

<form:form method="post" action="add.html" commandName="contact">

	<table>
	<tr>
		<td><form:label path="firstname"><spring:message code="label.firstname"/></form:label></td>
		<td><form:input path="firstname" /></td> 
	</tr>
	<tr>
		<td><form:label path="lastname"><spring:message code="label.lastname"/></form:label></td>
		<td><form:input path="lastname" /></td>
	</tr>
	<tr>
		<td><form:label path="email"><spring:message code="label.email"/></form:label></td>
		<td><form:input path="email" /></td>
	</tr>
	<tr>
		<td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
		<td><form:input path="telephone" /></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].houseNo"><spring:message code="label.houseNo"/></form:label></td>
		<td><form:input path="addressList[0].houseNo" /></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].locality"><spring:message code="label.locality"/></form:label></td>
		<td><form:input path="addressList[0].locality" /></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].city"><spring:message code="label.city"/></form:label></td>
		<td><form:input path="addressList[0].city" /></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].zipCode"><spring:message code="label.zipCode"/></form:label></td>
		<td><form:input path="addressList[0].zipCode" /></td>
	</tr>
	<tr>
		<td><form:label path="addressList[0].state"><spring:message code="label.state"/></form:label></td>
		<td><form:input path="addressList[0].state" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="label.addcontact"/>"/>
		</td>
	</tr>
</table>	
</form:form>

	
<h3>Contacts</h3>
<c:if  test="${!empty contactList}">
<table class="data">
<tr>
	<th>Name</th>
	<th>Email</th>
	<th>Telephone</th>
	 <th>Address</th>
	<th>City</th>
	<th>Pin Code</th>
	<th>State</th>
	<th>&nbsp;</th>
	<th>&nbsp;</th>
</tr>
<c:forEach items="${contactList}" var="contact">
	<tr>
		<td>${contact.lastname}, ${contact.firstname} </td>
		<td>${contact.email}</td>
		<td>${contact.telephone}</td>
		<c:forEach items="${contact.addressList}" var="addr">
		 <td>${addr.houseNo}, ${addr.locality} </td>
		<td>${addr.city}</td>
		<td>${addr.zipCode}</td>
		<td>${addr.state}</td> 
		</c:forEach>
		<td><a href="delete/${contact.id}">delete</a></td>
		<td><a href="edit/${contact.id}">edit</a></td>
	</tr>
</c:forEach>
</table>
</c:if>


</body>
</html>
