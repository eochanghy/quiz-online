<%-- 
    Document   : MakeQuiz
    Created on : Jul 8, 2021, 1:45:15 AM
    Author     : Eo Chang Hy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Quiz</title>
        <link href="view/css/layout.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="view/css/makeQuiz.css" rel="stylesheet" type="text/css"/>
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
                                        <c:if test="${not empty success}">
                                            <p class="content_body_blue">${success}</p>
                                        </c:if>
                                        <form action="MakeQuiz" method="post">
                                            <div class="form-group">
                                                <span class="content_body_red">${questionEmpty}</span>
                                                <textarea rows="3" cols="20" type="text" class="form-control" placeholder="Question" name="question"></textarea>
                                            </div>
                                            <c:forEach var="i" begin="1" end="4">
                                                <div class="form-group">
                                                    <span class="content_body_red">${opEmpty[i-1]}</span>
                                                    <input " type="text" class="form-control" placeholder="Answer ${i}" name="option${i}"/>
                                                </div>
                                            </c:forEach>
                                            <div class="d-flex justify-content-around">
                                                <div class="correctAnswer">
                                                    <p>Correct Answer</p>
                                                    <select name="correctAnswer" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                                        <option value="1">Answer 1</option>
                                                        <option value="2">Answer 2</option>
                                                        <option value="3">Answer 3</option>
                                                        <option value="4">Answer 4</option>
                                                    </select>
                                                </div>
                                                <div class="subject">
                                                    <p>Subject</p>
                                                    <select name="subject" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                                        <option value="Math">Math</option>
                                                        <option value="English">English</option>
                                                        <option value="Physic">Physic</option>
                                                    </select>
                                                </div>
                                                <div class="level">
                                                    <p>Level</p>
                                                    <select name="level" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                                        <option value="Easy">Easy</option>
                                                        <option value="Medium">Medium</option>
                                                        <option value="Hard">Hard</option>
                                                    </select>
                                                </div>
                                            </div>

                                                <input type="submit" class="btn shadow-sm submitBtn" value="Create"></input>
                                        </form>
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
</body>
</html>
