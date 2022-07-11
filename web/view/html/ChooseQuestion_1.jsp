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
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/chooseQuestion.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="display">
            <div class="header">
                <jsp:include page="Header.jsp"/>
            </div>
            <div class="content">
                <jsp:include page="Menu.jsp"/>
                <div class="content_body">
                    <div class="content_body_title">
                        <span class="content_body_font">Welcome </span>
                        <span class="content_body_blue">${account.getName()}</span>
                    </div>
                    <c:if test="${not empty invalid}">
                        <p class="invalid">${invalid}</p>
                    </c:if>
                    <div class="content_body_font">Enter number of question:</div>
                    <form action="ChooseQuestion" method="post">
                        <table>
                            <tr>
                                <td><input class="inputNumber" value="${numQues}" name="number"></td>
                            </tr>
                        </table>

                        <div class="btnStart">
                            <input type="submit" value="Start">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
