<%-- 
    Document   : Result
    Created on : Jul 9, 2021, 4:38:57 PM
    Author     : Eo Chang Hy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
        <link href="view/css/layout.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/result.css" rel="stylesheet" type="text/css"/>
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
                                    <div>${time}</div>
                                    <div>
                                        <c:if test="${not empty resultFail}">
                                            <div class="content_body_red">${resultFail}</div>
                                        </c:if>
                                        <div class="box" style="--percent: ${500-3*result*10}">
                                            <svg>
                                            <circle class="circle-background" cx="60" cy="60" r="50"></circle>
                                            <circle class="circle-percent" cx="60" cy="60" r="50"></circle>
                                            </svg>
                                            <div class="counter" >${result*10}</div>
                                            <div class="stop"></div>
                                            <div class="score">
                                                <span class="content_body_font">Your score: </span>
                                                <span class="score-font">${result} (${result*10}%)</span>
                                            </div>
                                            <div class="btn-form">
                                                <span class="content_body_font">Take another test</span>
                                                <a href="ChooseQuestion">
                                                    <button>Start</button>
                                                </a>
                                            </div>
                                        </div>

                                    </div>
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
