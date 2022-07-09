<%-- 
    Document   : Login
    Created on : Jul 7, 2021, 5:05:25 PM
    Author     : Eo Chang Hy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="view/css/login.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="display">
            <!--Header-->
            <div class="header">
                <jsp:include page="Header.jsp"/>
            </div>
            <div class="content">
                <jsp:include page="Menu.jsp"/>
                <div class="content_body">
                    <p class="content_body_title" id="title">Login Form</p>
                    <!--If wrong login-->
                    <c:if test="${not empty invalid}">
                        <p class="invalid">${invalid}</p>
                    </c:if>
                    <!--Form login-->
                    <form action="Login" method="post">
                        <table>
                            <tr>
                                <td valign="top"><span class="content_body_font">User name:</span></td>
                                <td>
                                    <c:if test="${not empty userFail}">
                                        <span class="content_body_red">${userFail}</span><br/>
                                    </c:if>
                                    <input type="text" value="${user}" name="user">                           
                                    
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><span class="content_body_font">Password:</span></td>
                                <td>
                                    <c:if test="${not empty passwordFail}">
                                        <span class="content_body_red">${passwordFail}</span><br/>
                                    </c:if>
                                    <input type="password" value="${password}" name="password">
                                    
                                </td>
                            
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <input class="content_body_submit" type="submit" value="Sign in">
                                    <a id ="link_register" class="content_body_font" href="Register">Register</a>
                                </td>
                            </tr>
                        </table>
                        
                    </form>
                    
                </div>
            </div>
        </div>
    </body>
</html>
