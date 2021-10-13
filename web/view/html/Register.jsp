<%-- 
    Document   : Register
    Created on : Jul 7, 2021, 11:39:03 PM
    Author     : Eo Chang Hy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/login.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="display">
            <!--Header-->
            <div class="header">
                <jsp:include page="Header.jsp"/>
            </div>
            <div class="content">
                <!--Menu Bar-->
                <jsp:include page="Menu.jsp"/>
                <div class="content_body">
                    <p class="content_body_title" id="title">Registration Form</p>
                    <form action="Register" method="post">
                        <table>
                            <tr>                              
                                <td valign="top" style="position: absolute;"><span class="content_body_font">User Name:</span></td>
                                <td style="padding-left: 12px;">
                                    <c:if test="${not empty usernameFail}">
                                        <span class="content_body_red">${usernameFail}</span><br/>
                                    </c:if>
                                    <input type="text" value="${accountRegis.getName()}" name="user">

                                </td>
                            </tr>            
                            <tr>
                                <td valign="top"><span class="content_body_font">Password:</span></td>
                                <td style="padding-left: 12px;">
                                    <c:if test="${not empty passwordFail}">
                                        <span class="content_body_red">${passwordFail}</span><br/>
                                    </c:if>
                                    <input type="password" value="${accountRegis.getPassword()}" name="password">

                                </td>
                            </tr>
                            <tr>
                                <td><span class="content_body_font">UserType:</span></td>
                                <td style="padding-left: 12px;">
                                    <select name="type">
                                        <option value="0"
                                                <c:if test="${type eq 0}">
                                                    selected="selected"
                                                </c:if>>Student</option>
                                        <option value="1"
                                                <c:if test="${type eq 1}">
                                                    selected="selected"
                                                </c:if>>Teacher</option>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <!--Input email-->
                                <td valign="top"><span class="content_body_font">Email:</span></td>
                                <td style="padding-left: 12px;">
                                    <c:if test="${not empty emailFail}">
                                        <span class="content_body_red">${emailFail}</span><br/>
                                    </c:if>
                                    <input type="text" value="${accountRegis.getEmail()}" name="email">

                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input class="content_body_submit" type="submit" value="Register"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>


    </body>
</html>
