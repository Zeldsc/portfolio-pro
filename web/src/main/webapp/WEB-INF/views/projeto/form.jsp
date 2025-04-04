<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro de Projeto</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Cadastro de Projeto</h2>

    <c:if test="${not empty mensagemErro}">
        <div class="alert alert-danger">${mensagemErro}</div>
    </c:if>

    <form action="${projeto.id == null ? '/projetos/salvar' : '/projetos/atualizar'}" method="post">
        <input type="hidden" name="id" value="${projeto.id}"/>

        <div class="mb-3">
            <label>Nome:</label>
            <input type="text" name="nome" class="form-control" value="${projeto.nome}" required/>
        </div>

        <div class="mb-3">
            <label>Data de Início:</label>
            <input type="date" name="dataInicio" class="form-control" value="${projeto.dataInicio}" required/>
        </div>

        <div class="mb-3">
            <label>Previsão de Término:</label>
            <input type="date" name="dataPrevisaoFim" class="form-control" value="${projeto.dataPrevisaoFim}"/>
        </div>

        <div class="mb-3">
            <label>Data Real de Término:</label>
            <input type="date" name="dataFim" class="form-control" value="${projeto.dataFim}"/>
        </div>

        <div class="mb-3">
            <label>Descrição:</label>
            <textarea name="descricao" class="form-control">${projeto.descricao}</textarea>
        </div>

        <div class="mb-3">
            <label>Status:</label>
            <select name="status" class="form-control" required>
                <c:forEach var="status" items="${statusList}">
                    <option value="${status}" ${projeto.status == status ? 'selected' : ''}>${status.descricao}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Risco:</label>
            <select name="risco" class="form-control">
                <c:forEach var="risco" items="${riscoList}">
                    <option value="${risco}" ${projeto.risco == risco ? 'selected' : ''}>${risco.descricao}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Orçamento:</label>
            <input type="number" step="0.01" name="orcamento" class="form-control" value="${projeto.orcamento}" required/>
        </div>

        <div class="mb-3">
            <label>Gerente:</label>
            <select name="gerente" class="form-control" required>
                <c:forEach var="gerente" items="${gerenteList}">
                    <option value="${gerente.cpf}" ${projeto.gerente != null && projeto.gerente.cpf == gerente.cpf ? 'selected' : ''}>
                        ${gerente.nome}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Membros do Projeto:</label>
            <select multiple name="membros" class="form-control">
                <c:forEach var="funcionario" items="${funcionarioList}">
                    <c:set var="selecionado" value="false"/>
                    <c:forEach var="m" items="${projeto.membros}">
                        <c:if test="${m.cpf == funcionario.cpf}">
                            <c:set var="selecionado" value="true"/>
                        </c:if>
                    </c:forEach>
                    <option value="${funcionario.cpf}" ${selecionado ? 'selected' : ''}>
                        ${funcionario.nome}
                    </option>
                </c:forEach>
            </select>
            <small class="form-text text-muted">Segure Ctrl (ou Cmd no Mac) para selecionar vários.</small>
        </div>

        <button type="submit" class="btn btn-success">Salvar</button>
        <a href="/projetos" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>
