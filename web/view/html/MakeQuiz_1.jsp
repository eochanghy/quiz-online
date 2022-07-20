<%-- 
    Document   : MakeQuiz
    Created on : Jul 8, 2021, 1:45:15 AM
    Author     : Eo Chang Hy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Quiz</title>
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/makeQuiz.css" rel="stylesheet" type="text/css"/>
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
                        <form action="MakeQuiz" method="post">
                        <input type="hidden" name="accountId" value="${account.getId()}">
                        <c:if test="${not empty success}">
                            <p class="content_body_blue">${success}</p>
                        </c:if>
                        <table>
                            <tr>
                                <td valign="top"><span class="content_body_font">Question:</span></td>
                                <td>
                                    <span class="content_body_red">${questionEmpty}</span>
                                    <textarea class="txtQuestion" name="question">${question}</textarea>
                                    
                                </td>
                            </tr>
                            <c:forEach var="i" begin="1" end="4">
                                <tr>
                                    <td valign="top"><span class="content_body_font">Option${i}:</span></td>
                                    <td>
                                        <span class="content_body_red">${opEmpty[i-1]}</span>
                                        <textarea class="txtOption" name="option${i}">${op[i-1]}</textarea>   
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td><span class="content_body_font">Answer(s):</span></td>
                                <td>
                                    <span class="content_body_red">${resultFail}</span><br/>
                                    <c:forEach var="i" begin="1" end="4">
                                        <input type="checkbox" name="cbxOption" value="${i}"
                                               <c:forEach items="${result}" var="x">
                                                   <c:if test="${x eq i}">
                                                       checked="checked"
                                                   </c:if>
                                               </c:forEach>>
                                        <span class="content_body_font">Option${i}</span>
                                    </c:forEach>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="Save"></td>
                            </tr>
                        </table>
                    </form>
                    </c:if>
                    <c:if test="${!allowed}">
                        <span class="content_body_red">You are not allowed to use this function</span>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
