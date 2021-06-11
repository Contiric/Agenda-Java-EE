<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ page import="Model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>

<%-- Laço para percorrer os dados do banco
     for (int i =0; i< lista.size(); i++) {
    	out.println(lista.get(i).getIdcontato());
    	out.println(lista.get(i).getNome());
    	out.println(lista.get(i).getFone());
    	out.println(lista.get(i).getEmail());
    }
     --%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/cel.png">
<link rel="stylesheet" href="css/newstyle.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="botao"> Novo Contato</a>
	<a href="report" class = "BotaoExcluir">Relatório</a>

	<table id="tabela">

		<thead>

			<tr>

				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>

			<%
				for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>

				<td><%=lista.get(i).getIdcontato()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getFone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><a href="select?idcontato=<%=lista.get(i).getIdcontato()%>"
					class="BotaoEditar"> Editar </a> 
					<a href="javascript: confirmar(<%=lista.get(i).getIdcontato()%>)" class="BotaoExcluir">
						Excluir </a>
						
						</td>
			</tr>
			<%
				}
			%>
		</tbody>

	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>