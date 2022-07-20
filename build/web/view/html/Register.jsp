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
        <link href="view/css/layout.css" rel="stylesheet" type="text/css"/>
        <link href="view/css/login.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    </head>
    <body>
        <section class="h-100 h-custom section-contain" style="background-color: #8fc4b7;">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col lg-8 col-xl-5">
                        <div class="card rounded-3">
                            <img src="./image/nha-alpha.jpg" class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;" alt="Sample photo" />
                            <div class="card-body p-4 p-md-12">
                                <div class="login2">
                                    <div class="center">
                                        <h1>Register</h1>

                                        <form action="Register" method="post">
                                            <div class="txt_field">
                                                <c:if test="${not empty usernameFail}">
                                                    <p class="content_body_red">${usernameFail}</p>
                                                </c:if>
                                                <input type="text" value="${user}" name="user" required />
                                                <span></span>
                                                <label >Username</label>
                                            </div>
                                            <div class="txt_field">
                                                <c:if test="${not empty passwordFail}">
                                                    <p class="content_body_red">${passwordFail}</p>
                                                </c:if>
                                                <input type="password" value="${password}" name="password" required />
                                                <span></span>
                                                <label >Password</label>
                                            </div>                                           
                                            <div class="txt_field">
                                                <c:if test="${not empty emailFail}">
                                                    <p class="content_body_red">${emailFail}</p>
                                                </c:if>
                                                <input type="text" value="${accountRegis.getEmail()}" name="email" required>
                                                <span></span>
                                                <label >Email</label>
                                            </div>
                                            <div class="txt_field">
                                                <input type="number" value="${accountRegis.getAge()}" name="age" required>
                                                <span></span>
                                                <label >Age</label>
                                            </div>
                                            <div class="txt_field">
                                                <input type="text" value="${accountRegis.getClassName()}" name="class" required>
                                                <span></span>
                                                <label >Class</label>
                                            </div>
                                            <div class="select_field">
                                                <span>Type: </span>
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

                                            </div>
                                            <input type="submit" value="Sign Up" />
                                            <div class="signup_link">
                                                Already have account? <a href="Login">Login</Link>
                                            </div>
                                        </form>
                                    </div>

                                </div >
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>


    </body>
</html>
