<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Discuss</title>

    <!-- Bootstrap -->
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/jquery-1.4.4.min.js"></script>
    <!--script src="http://code.jquery.com/jquery-1.9.1.js"></script-->
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
                <span class="sr-only">Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">CouchTalks</a>
        </div>
        <div class="collapse navbar-collapse" id="responsive-menu">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Shedule</a></li>
                <li><a href="#">Browse</a></li>
                <li><a>|</a></li>
                <li><a href="#">Sign up</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>Sign in</b> <span
                            class="caret"></span></a>
                    <ul id="login-dp" class="dropdown-menu">
                        <li>
                            <div class="row">
                                <div class="col-md-12">
                                    Login via
                                    <div class="social-buttons">
                                        <a href="#" class="btn btn-fb"><i class="fa fa-facebook"></i> Facebook</a>
                                        <a href="#" class="btn btn-tw"><i class="fa fa-twitter"></i> Twitter</a>
                                    </div>
                                    or
                                    <form class="form" role="form" method="post" action="login" accept-charset="UTF-8"
                                          id="login-nav">
                                        <div class="form-group">
                                            <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                            <input type="email" class="form-control" id="exampleInputEmail2"
                                                   placeholder="Email address" required="">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="exampleInputPassword2">Password</label>
                                            <input type="password" class="form-control" id="exampleInputPassword2"
                                                   placeholder="Password" required="">
                                            <div class="help-block text-right"><a href="">Forget the password ?</a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                                        </div>
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox"> keep me logged-in
                                            </label>
                                        </div>
                                    </form>
                                </div>
                                <div class="bottom text-center">
                                    New here? <a href="registration.html"><b>Sign up</b></a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-sm-2 col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-lg-10">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="panel-title">
                        <h3 class="text-center">Join the community right now!</h3>
                        <h3 class="text-center">Sign up for free!</h3>
                    </div>
                </div>
                <form:form method="POST" role="form" modelAttribute="userForm">

                <div class="panel-body">
                    <div class="col-md-6 col-sm-6 col-md-6 separator login-box">
                        <h4 class="text-left">Create new account</h4>
                        <br>
                            <spring:bind path="username">
                                <form:errors path="username" class="error-text"></form:errors>
                                <div class="input-group ${status.error ? 'has-error' : ''}">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-user"></span></span>
                                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                                autofocus="true"></form:input>
                                </div>

                            </spring:bind>

                            <spring:bind path="email">
                                <form:errors path="email" class="error-text"></form:errors>
                                <div class="input-group ${status.error ? 'has-error' : ''}">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                    <form:input path="email" class="form-control"
                                                placeholder="Email"></form:input>
                                </div>
                            </spring:bind>

                            <spring:bind path="password">
                                <form:errors path="password" class="error-text"></form:errors>
                                <div class="input-group ${status.error ? 'has-error' : ''}">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-lock"></span></span>
                                    <form:input type="password" path="password" class="form-control"
                                                placeholder="Password"></form:input>
                                </div>
                            </spring:bind>

                            <spring:bind path="repeatPassword">
                                <form:errors path="repeatPassword" class="error-text"></form:errors>
                                <div class="input-group ${status.error ? 'has-error' : ''}">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-lock"></span></span>
                                    <form:input path="repeatPassword" type="password" class="form-control"
                                                placeholder="Repeat password"></form:input>
                                </div>
                            </spring:bind>

                    </div>

                    <div class="col-md-6 col-sm-6 col-md-6  social-login-box">
                        <h4 class="text-left">Sign up using</h4>
                        <br>
                        <a href="#" class="btn facebook btn-block" role="button">Sign up with Facebook</a>
                        <br>
                        <a href="#" class="btn twitter btn-block" role="button">Sign up with Twitter</a>
                    </div>
                </div>
                <div class="DivClearGorizontal"></div>
                <div class="DivClearGorizontal"></div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-7">
                            <button type="submit" class="btn btn-labeled pull-right">Sign Up</button>
                        </div>
                    </div>
                </div>
                </form:form>

            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="alert alert-success hidden" id="success-alert">
        <h2>Успех</h2>
        <div>Ваши данные были успешно отправлены.</div>
    </div>
</div>

<!-- Подлючаем библиотеку jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- Подлючаем js файл Bootstrap -->
<script src="${contextPath}/resources/js/bootstrap.js"></script>


<!--...-->

</body>
</html>
