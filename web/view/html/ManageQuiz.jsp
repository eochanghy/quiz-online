<%-- 
    Document   : ManageQuiz
    Created on : Jul 9, 2021, 4:06:15 PM
    Author     : Eo Chang Hy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage</title>
        <link href="view/css/layout.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/ManageQuiz.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <script src="view/js/Manage.js" type="text/javascript"></script>
    </head>
    <body>
        <section class="h-100 h-custom section-contain" style="background-color: #8fc4b7;">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col lg-8 col-xl-5">
                        <div class="card rounded-3">
                            <img src="./image/nha-alpha.jpg" class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;" alt="Sample photo" />
                            <div class="card-body p-4 p-md-12">
                                <jsp:include page="Menu.jsp"/>
                                <div class="content_body">
                                    <c:if test="${allowed}">
                                        <div class="content_body_title">
                                            <span class="content_body_font">Number of question: </span>
                                            <span class="content_body_red">${numQuestion}</span>
                                        </div>
                                        <table class="table align-middle mb-0 bg-white">
                                            <thead class="bg-light">
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Question</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${questions}" var="q">
                                                    <tr>
                                                        <td>
                                                            <p class="fw-normal mb-1">${q.getId()}</p>
                                                        </td>
                                                        <td>
                                                            <p class="fw-normal mb-1">${q.getQuestion()}</p>
                                                        </td>
                                                        <td>
                                                            <button type="button" class="btn btn-outline-danger btn-sm" onclick="confirmDelete(${q.getId()})">
                                                                Delete
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                            </tbody>
                                        </table>
                                        <!--Paging-->
                                        <div id="bottomPagger"></div>
                                        <script>
                                            renderPager("bottomPagger", ${pageIndex}, ${totalPage}, 2);
                                        </script>
                                    </c:if>
                                    <c:if test="${!allowed}">
                                        <span class="content_body_red">You are not allowed to use this function</span>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    </body>
</html>
