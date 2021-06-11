<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Editar Contato</title>
<link rel="icon" href="imagens/cel.png">
<link rel="stylesheet" href="css/newstyle.css">
</head>
<body>
	<h1>Novo Contato</h1>
	<form name="cadastroContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcontato" id="caixa3" readonly
					value="<%out.print(request.getAttribute("idcontato"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="caixa1"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="caixa2"
					value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="email" class="caixa1"
					value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>

		</table>
		<input type="button" value="Salvar" class="botao" onclick="validar()">

	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>