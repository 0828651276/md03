<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Danh Sách Sản Phẩm Phông Bạt</title>
    <!--Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <%@ include file="../layouts/navbar.jsp" %>
    <div class="card">
        <div class="card-header">
            User List
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-md-6">
                    <a class="btn btn-success" href="/admin/products/create">Add new</a>

                </div>
                <div class="col-12 col-md-6">
                    <form class="d-flex" action="/admin/products" method="get">
                        <input class="form-control" type="text" name="keyword">
                        <button class="btn btn-primary" type="submit">Search</button>
                    </form>
                </div>
            </div>

            Total user: <c:out value="${data.size()}"/>
            <table class="table">
                <tr>
                    <td>#</td>
                    <td>Name</td>
                    <td>Description</td>
                    <td>Inventory</td>
                    <td>Price</td>
                    <td>Image</td>
                    <td></td>
                </tr>
                <c:forEach items="${data}" var="product" >
                    <tr>
                        <td><c:out value="${product.id}"/></td>
                        <td><c:out value="${product.name}"/></td>
                        <td><c:out value="${product.description}"/></td>
                        <td><c:out value="${product.inventory}"/></td>
                        <td><c:out value="${product.price}"/></td>
                        <td>
                            <img src="${product.imageUrl}" alt="${product.imageUrl}" width="100" height="100"/>
                        </td>
                        <td>
                            <a class="btn btn-danger" onclick="return confirm('Are you sure?')" href="${pageContext.request.contextPath}/admin/products/delete?id=<c:out value="${product.id}"/> ">Delete</a>
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/products/update?id=<c:out value="${product.id}"/> ">Update</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="/admin/products?page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach var = "i" begin = "1" end = "${totalPage}">

                        <c:if test="${currentPage == i}">
                            <li class="page-item active" aria-current="page" ><a class="page-link" href="/admin/products?page=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                        </c:if>
                        <c:if test="${currentPage != i}">
                            <li class="page-item" aria-current="page" ><a class="page-link" href="/admin/products?page=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                        </c:if>

                    </c:forEach>


                    <li class="page-item ${currentPage == totalPage ? 'disabled' : ''}">
                        <a class="page-link" href="/admin/products?page=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>

                </ul>
            </nav>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
