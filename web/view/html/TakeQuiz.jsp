<%-- 
    Document   : TakeQuiz
    Created on : Jul 9, 2021, 4:27:14 PM
    Author     : Eo Chang Hy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz</title>
        <link href="view/css/layout.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/takeQuiz.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <script src="view/js/TakeQuiz.js" type="text/javascript"></script>
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
                                    <div>
                                        <span class="content_body_font">Welcome </span>
                                        <span class="content_body_blue">${account.getName()}</span>
                                    </div>
                                    <!--Time remain-->
                                    <div id="timeRemain" class="content_body_title">
                                        <span class="content_body_font">Time remaining: </span>
                                        <span id="time" class="content_body_red">
                                            <script>
                                                setTime(${quiz.getEndTime()});
                                            </script>
                                        </span>
                                    </div>
                                    <form action="TakeQuiz" method="post" id="form">
                                        <input type="hidden" name="currentQuestion" value="${quiz.getCurrentQuestion()}">
                                        <table>
                                            <tr>
                                                <td>                                                   
                                                    <input type="hidden" name="hidden">
                                                    <div class="content_body_font">${question.getQuestion()}</div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="option" onclick="handleOptionClick(this)">
                                                        <input type="checkbox" name="option" value="${question.getOption1()}" hidden>
                                                        <span class="content_body_font">${question.getOption1()}</span>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="option" onclick="handleOptionClick(this)">
                                                        <input type="checkbox" name="option" value="${question.getOption2()}" hidden>
                                                        <span class="content_body_font">${question.getOption2()}</span>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="option" onclick="handleOptionClick(this)">
                                                        <input type="checkbox" name="option" value="${question.getOption3()}" hidden>
                                                        <span class="content_body_font">${question.getOption3()}</span>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="option" onclick="handleOptionClick(this)">
                                                        <input type="checkbox" name="option" value="${question.getOption4()}" hidden>
                                                        <span class="content_body_font">${question.getOption4()}</span>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="btnNext">
                                                    <input type="submit" value="Next">
                                                </td>
                                            </tr>
                                        </table>
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
