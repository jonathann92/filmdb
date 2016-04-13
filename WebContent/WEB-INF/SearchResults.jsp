<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<%@include file="/WEB-INF/includes/header.jsp" %>
<body>
<div class="container" >
<%@include file="/WEB-INF/includes/navbar.jsp" %>
	<div>
		<div class="text-center">
			<h1>Search Results</h1>
			<p>${movieList.size() }
		</div>
</div>
		<div class="results" align="center">
			<table>
			<thead>
				<tr>
					<th>id</th>
					<th>photo</th>
					<th>title</th>
					<th>year</th>
					<th>director</th>
					<th>stars</th>
					<th>genres</th>
				</tr>
			</thead>
				<c:forEach items="${movieList}" var="movie">
				<tr>
					<td class="table-cell-pad">${movie.id}</td>
					<td class="table-cell-photo"><img src="${movie.banner }" width=90 height=120></td>
					<td class="table-cell-pad">${movie.title}</td>
					<td class="table-cell-pad">${movie.year}</td>
					<td class="table-cell-pad">${movie.director}</td>
					<td class="table-cell-pad">${movie.stars}</td>
					<td class="table-cell-pad">${movie.genres}</td>
				</tr>

		        </c:forEach>
			</table>
		</div>
	</div>






</body>
</html>