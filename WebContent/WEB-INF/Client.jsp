<%@page import="com.google.common.base.Strings"%>
<%@page import="com.M2I.ecommerce.beans.Client"%>
<jsp:include page="/Head.jsp" />
<% Client client = (Client)request.getAttribute("clientBean");%>
<body>
	<div class="container">
		<h2>
			Page client : <i><%=client.nom.toUpperCase() + " " + client.prenom%></i>
		</h2>
		<br>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<%=client.nom.toUpperCase() + " " + client.prenom%><br>
					<%=client.nrue + " " + client.rue%><br>
					<%=client.codePostal.toUpperCase() + " - " + client.ville.toUpperCase() + " - " + client.pays.toUpperCase() %><br>
				</h3>
			</div>

			<ul class="list-group">
				<% if(!Strings.isNullOrEmpty(client.telephone)){ %>
					<li class="list-group-item">Tel : <%=client.telephone %>
				<%} %>
				<li class="list-group-item">Email : <%=client.email %></li>

			</ul>

		</div>
		<div class="container clearfix">
			<div class="pull-left">
				<a href="index.jsp" class="btn btn-primary">Accueil</a>
			</div>
		</div>
	</div>
</body>
<jsp:include page="/Footer.jsp" />