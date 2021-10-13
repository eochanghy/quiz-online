<%-- 
    Document   : Home
    Created on : Jul 7, 2021, 8:04:00 PM
    Author     : Eo Chang Hy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="view/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
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
                <!--Menu-->
                <jsp:include page="Menu.jsp"/>
                <div class="content_body">
                    <c:if test="${not empty success}">
                        <p class="content_body_font" style="color: red;">${success}</p>
                    </c:if>
                        <p>
                            <span class="content_body_font">Welcome</span>
                            <span class="content_body_blue">${account.name}</span>
                        </p>    
                        <p>
                            <span class="content_body_font">Type: </span>
                            <span class="content_body_blue">${account.type}</span>
                        </p>
                        <p>
                            <span class="content_body_font">Email: </span>
                            <span class="content_body_blue">${account.email}</span>
                        </p>
                </div>
            </div>
        </div>
    </body>
</html>
