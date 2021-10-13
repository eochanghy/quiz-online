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
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/header.css" rel="stylesheet" type="text/css"/>
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
                    <div>${time}</div>
                    <div>
                        <c:if test="${not empty resultFail}">
                            <div class="content_body_red">${resultFail}</div>
                        </c:if>
                        <c:if test="${not empty result}">
                            <div class="content_body_title">
                                <!--The score-->
                                <span class="content_body_font">Your score </span>
                                <span class="content_body_blue">${result} (${result*10}%) -</span>
                                <!--If not passed-->
                                <c:if test="${result lt 5}">
                                    <span class="content_body_red">Not passed</span>
                                </c:if>
                                <!--If passed-->
                                <c:if test="${result ge 5}">
                                    <span class="content_body_blue">Passed</span>
                                </c:if>
                            </div> 
                        </c:if>
                        <!--Take another test-->
                        <div>
                            <span class="content_body_font">Take another test</span>
                            <a href="ChooseQuestion">
                                <button>Start</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>
