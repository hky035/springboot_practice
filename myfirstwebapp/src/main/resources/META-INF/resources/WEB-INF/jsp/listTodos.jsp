<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<<<<<<< HEAD
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf"%>
=======
<html>
	<head>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
		<title>List Todos Page</title>		
	</head>
	<body>
>>>>>>> ee5f0a06e3a6c7c8fd2e66c219a191f96abdf4dc
		<div class="container">
			<h1>Your Todos</h1>
			<table class="table">
				<thead>
					<tr>
<<<<<<< HEAD
=======
						<th>id</th>
>>>>>>> ee5f0a06e3a6c7c8fd2e66c219a191f96abdf4dc
						<th>Description</th>
						<th>Target Date</th>
						<th>Is Done?</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>		
					<c:forEach items="${todos}" var="todo">
						<tr>
<<<<<<< HEAD
=======
							<td>${todo.id}</td>
>>>>>>> ee5f0a06e3a6c7c8fd2e66c219a191f96abdf4dc
							<td>${todo.description}/${todo.username} </td>
							<td>${todo.targetDate}</td>
							<td>${todo.done}</td>
							<td><a class="btn btn-warning" href="delete-todo?id=${todo.id}">Delete</a></td>
							<td><a class="btn btn-success" href="update-todo?id=${todo.id}">Update</a></td>		
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
		<a href="add-todo" class="btn btn-success">Add To Do</a>
		
<<<<<<< HEAD
<%@ include file="common/footer.jspf"%>
=======
		</div>
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
		
	</body>
</html>
>>>>>>> ee5f0a06e3a6c7c8fd2e66c219a191f96abdf4dc
