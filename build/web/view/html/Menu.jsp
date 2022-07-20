<%-- 
    Document   : Menu
    Created on : Jul 7, 2021, 5:24:09 PM
    Author     : Eo Chang Hy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <body>
        <div class="menu">
            <nav class="menu_bar">
                <a href="Home" class="menu_option">Home</a>
                <c:if test="${account.getType() == 'student'}">
                    <a href="ChooseQuestion" class="menu_option">Take quiz</a>
                </c:if>
                <c:if test="${account.getType() == 'teacher'}">
                    <a href="MakeQuiz" class="menu_option">Make Quiz</a>
                    <a href="Manage" class="menu_option">Manage Quiz</a>
                </c:if>
                <c:if test="${not empty account}">
                    <a href="LogOut" class="menu_option">Log Out</a>
                </c:if>

            </nav>
        </div>
    </body>
</html>
