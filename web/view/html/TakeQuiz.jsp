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
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/takeQuiz.css" rel="stylesheet" type="text/css"/>
        <script src="view/js/TakeQuiz.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="display">
            <div class="header">
                <jsp:include page="Header.jsp"/>
            </div>
            <div class="content">
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
                                    <input type="checkbox" name="option" value="1">
                                    <span class="content_body_font">${question.getOption1()}</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="checkbox" name="option" value="2">
                                    <span class="content_body_font">${question.getOption2()}</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="checkbox" name="option" value="3">
                                    <span class="content_body_font">${question.getOption3()}</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="checkbox" name="option" value="4">
                                    <span class="content_body_font">${question.getOption4()}</span>
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
    </body>
</html>
