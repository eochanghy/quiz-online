<%-- 
    Document   : PageNotFound
    Created on : Jul 21, 2021, 11:01:52 AM
    Author     : Eo Chang Hy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Not Found</title>
        <link href="view/css/common.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/menu.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
         <div class="display">
            <!--Header-->
            <div class="header">
                <%@include file="Header.jsp"%>
            </div>
            <div class="content">
                <!--Menu Bar-->
                <%@include file="Menu.jsp"%>
                <div class="content_body">
                    <!--Error content-->
                    <div class="content_body_font" style="font-size: 24px">Page Not Found.</div>
                </div>
            </div>
        </div>
    </body>
</html>
