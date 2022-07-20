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
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/ManageQuiz.css" rel="stylesheet" type="text/css"/>
        <script src="view/js/Manage.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="display">
            <div class="header">
                <jsp:include page="Header.jsp"/>
            </div>
            <div class="content">
                <jsp:include page="Menu.jsp"/>
                <div class="content_body">
                    <c:if test="${allowed}">
                        <div class="content_body_title">
                            <span class="content_body_font">Number of question: </span>
                            <span class="content_body_blue">${numQuestion}</span>
                        </div>
                        <table>
                            <tr>
                                <th class="content_body_blue">Question</th>
                                <th class="content_body_blue">Date Created</th>
                                <!--<th class="content_body_blue">Edit</th>-->
                            </tr>
                            <c:forEach items="${questions}" var="q">
                                <tr>
                                    <td class="content_body_font">${q.getQuestion()}</td>
                                    <td id="td_date" class="content_body_font">${q.getDate()}</td>
                                    <td><button onclick="confirmDelete(${q.getId()})">Delete</button></td>
                                <br/>
                                </tr>
                            </c:forEach>
                        </table>
                        <!--Paging-->
                        <div id="bottomPagger"></div>
                        <script>
                        renderPager("bottomPagger", ${pageIndex}, ${totalPage}, 1);
                        </script>
                    </c:if>
                    <c:if test="${!allowed}">
                        <span class="content_body_red">You are not allowed to use this function</span>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
