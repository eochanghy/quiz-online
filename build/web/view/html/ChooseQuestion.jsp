<%-- 
    Document   : ChooseQuestion
    Created on : Jul 9, 2021, 4:18:25 PM
    Author     : Eo Chang Hy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Question</title>
        <link href="view/css/layout.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/chooseQuestion.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

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
                                    <div class="content_body_title">
                                        <span class="content_body_font">Welcome </span>
                                        <span class="content_body_blue">${account.getName()}!</span>
                                    </div>
                                    <c:if test="${not empty invalid}">
                                        <p class="invalid">${invalid}</p>
                                    </c:if>
                                    <form action="ChooseQuestion" method="post">
                                        <div class="content_body_font">Enter number of question:</div>
                                        <input class="inputNumber" value="${numQues}" name="number">
                                        <div class="content_body_font">Choose subject of question:</div>
                                        <select class="inputNumber" name="subject">
                                            <option value="Math">Math</option>
                                            <option value="English">English</option>
                                            <option value="Physic">Physic</option>
                                        </select>
                                        <div class="content_body_font">Choose level of question:</div>
                                        <select class="inputNumber" name="level">
                                            <option value="Easy">Easy</option>
                                            <option value="Medium">Medium</option>
                                            <option value="Hard">Hard</option>
                                        </select>


                                        <div class="btnStart">
                                            <input type="submit" value="Start">
                                        </div>
                                    </form>
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
