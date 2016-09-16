<%@page import="com.M2I.ecommerce.beans.Client"%>
<jsp:include page="/Head.jsp" />

<%-- 
 	Client client = (Client) request.getAttribute("clientBean");

 	if (client.idClient != null) {

 	}
 --%>
<body>
	<div class="container">
		<div class="page-header">
			<h2 class="">Création de compte :</h2>
		</div>
		<div class="container col-md-8 clearfix">
			<form action="FormulaireClient" method="post">
				<div class="row">
					<div class="col-md-6">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon1">Nom</span> <input
								class="form-control" type="text" name="nom" tabindex=1 ><!-- value="momo"> -->
						</div>
					</div>
					<div class="col-md-6">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon2">Email</span> <input
								class="form-control" type="email" name="email" tabindex=3 ><!-- value="seb@mo.com"> -->
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon3">Prénom</span> <input
								class="form-control" type="text" name="prenom" tabindex=2 ><!-- value="seb"> -->
						</div>
					</div>
					<div class="col-md-6">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon4">Mot de
								passe</span> <input class="form-control" type="password" name="motDePasse"
								tabindex=4 ><!-- value="qdfgqfg"> -->
						</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-5">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon5">Téléphone</span>
							<input class="form-control" type="text" name="telephone"
								tabindex=5 ><!-- value="036541354">-->
						</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-5">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon6">Pays</span> <input
								class="form-control" type="text" name="pays" tabindex=6 ><!-- value="turquie">-->
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon7">C.P.</span> <input
								class="form-control" type="text" name="codePostal" tabindex=7 ><!-- value="748596">-->
						</div>
					</div>
					<div class="col-md-9">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon8">Ville</span> <input
								class="form-control" type="text" name="ville" tabindex=8 ><!-- value="royan">-->
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon9">Numéro</span> <input
								class="form-control" type="text" name="nrue" tabindex=9 ><!-- value="54">-->
						</div>
					</div>
					<div class="col-md-9">
						<div class="input-group input-group-md">
							<span class="input-group-addon" id="basic-addon10">Rue</span> <input
								class="form-control" type="text" name="rue" tabindex=10 ><!-- value="place de l'arnaque">-->
						</div>
					</div>
				</div>
				
				<br>
				<div class="pull-left input-group input-group-md">
					<input class="btn btn-primary" type="submit" value="Créer">
				</div>
				<div class="pull-right">
					<input class="btn btn-warning" type="reset" value="Reset">
				</div>
			</form>

		</div>
	</div>
</body>
<jsp:include page="/Footer.jsp" />