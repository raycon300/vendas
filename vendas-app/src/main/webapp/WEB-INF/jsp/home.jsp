<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>AppVenda</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="/">AppVenda</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/vendedor/lista">Vendedores</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/produto/lista">Produtos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/literatura/lista">Literaturas</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/vestuario/lista">Vestuários</a>
            </li>
        </ul>
        <c:if test="${not empty listagem}">
            <form class="d-flex" action="/${rota}/pesquisar">
                <input class="form-control me-2" type="text" name="campoBusca"
                       placeholder="Search por ${campoDeBusca}">
                <button class="btn btn-primary" type="submit">Search</button>
            </form>
        </c:if>
    </div>
</nav>

<div class="container mt-3">

    <span class="badge rounded-pill bg-primary">Vendedor: ${qtdVendedor}</span>
    <span class="badge rounded-pill bg-secondary">Produto: ${qtdProduto}</span>
    <span class="badge rounded-pill bg-success">Literatura: ${qtdLiteratura}</span>
    <span class="badge rounded-pill bg-danger">Vestuário: ${qtdVestuario}</span>

    <c:if test="${not empty listagem}">
        <h2>AppVenda</h2>
        <p>Gestão de vendas de produtos:</p>
        <c:if test="${not empty erro}">
            <hr>
            <div class="alert alert-error">
                <strong>Erro - </strong> ${erro}
            </div>
        </c:if>

        <table class="table">
            <thead class="table-dark">
            <tr>
                <th>${titulo}</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${listagem}">
                <tr>
                    <td>${item}</td>
                    <td>
                        <form action='/${rota}/excluir' method="POST">
                            <input type="hidden" name="id" value="${item.id}"/>
                            <input type="submit" value="remover" class="btn btn-danger"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>


    <c:if test="${empty listagem}">
        <hr>
        <form action="/informacao/incluir" method="post">
            <div class="row">
                <div class="col">
                    <input type="text" class="form-control"
                           placeholder="Entre com o campo" name="campo">
                </div>
                <div class="col">
                    <input type="text" class="form-control"
                           placeholder="Entre com a descrição" name="descricao">
                </div>
                <div class="col">
                    <button class="btn btn-primary" type="submit">Cadastrar</button>
                </div>
            </div>
        </form>

        <c:if test="${not empty informacoes}">
            <hr>
            <table class="table">
                <thead class="table-dark">
                <tr>
                    <th>Informações:</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${informacoes}">
                    <tr>
                        <td>${item}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>

    <c:if test="${not empty objeto}">
        <hr>
        <div class="alert alert-success">
            <strong>Sucesso!</strong> ${objeto}
        </div>
    </c:if>

</div>
</body>
</html>