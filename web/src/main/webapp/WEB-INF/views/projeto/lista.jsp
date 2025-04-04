<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Projetos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Projetos</h2>
    <c:if test="${not empty mensagemErro}">
        <div class="alert alert-danger">${mensagemErro}</div>
    </c:if>

    <a href="/projetos/novo" class="btn btn-primary mb-3">Novo Projeto</a>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Status</th>
            <th>Risco</th>
            <th>Gerente</th>
            <th>Início</th>
            <th>Previsão Fim</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="projeto" items="${projetos}">
            <tr>
                <td>${projeto.nome}</td>
                <td>${projeto.status.descricao}</td>
                <td>${projeto.risco.descricao}</td>
                <td>${projeto.gerente.nome}</td>
                <td>${projeto.dataInicioFormatada}</td>
                <td>${projeto.dataPrevisaoFimFormatada}</td>
                <td>
                    <a href="/projetos/editar/${projeto.id}" class="btn btn-sm btn-warning">Editar</a>
                    <a href="/projetos/excluir/${projeto.id}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Deseja excluir este projeto?')">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>