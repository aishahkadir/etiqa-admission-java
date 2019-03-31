<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>University Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
	 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="jumbotron text-center" style="center">
  <h1>UNIVERSITI TEKNOLOGI PERAK</h1>
  
</div>

<c:url var="getAction" value="/person/${person.courseid}" ></c:url>
<form:form action="${getAction}" commandName="person" method="POST">
	<table>
	<tr>
			<form:hidden path="id" /></tr>
	<tr>
	<td><h6 class="text-primary">
	Course ID:
    </h6>
    </td>
    <td>
    <td>
		<input type="text" name="courseid">
	</td>
	
	<td colspan="2">
	            <c:if test="${!empty person.courseid}">
				<input type="submit"
					value="<spring:message text="Search"/>" />
				</c:if>
	</td>
	</tr>
	</table>

<br>
<h4>Student By Course ID</h4>

	<table class="table table-bordered">
	<tr>
	    <th width="80">Student ID</th>
		<th width="80">Course ID</th>
		<th width="120">Student Name</th>
	</tr>
	<c:forEach items="${listPersons}" var="person">
	
		<tr>
		    <td>${person.id}</td>
			<td>${person.courseid}</td>
			<td>${person.name}</td>
		</tr>
	
	</c:forEach>
	
	</table>
</form:form>
<br>

<h5 class="text-primary">
	Add new student
</h5>

<c:url var="addAction" value="/person/add" ></c:url>

<form:form action="${addAction}" commandName="person">
<table>
	<c:if test="${!empty person.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Student Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="country">
				<spring:message text="Student Country"/>
			</form:label>
		</td>
		<td>
			<form:input path="country" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty person.name}">
				<input type="submit"
					value="<spring:message text="Edit Student"/>" />
			</c:if>
			<c:if test="${empty person.name}">
				<input type="submit"
					value="<spring:message text="Add Student"/>" />
			</c:if>
		</td>
	</tr>
	
</table>	
</form:form>

<h4>Final Student List</h4>
<c:if test="${!empty listPersons}">
	<table class="table table-bordered">
	<tr>
		<th width="80">Student ID</th>
		<th width="120">Student Name</th>
		<th width="120">Student Country</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listPersons}" var="person">
		<tr>
			<td>${person.id}</td>
			<td>${person.name}</td>
			<td>${person.country}</td>
			<td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<br>

</body>
</html>
