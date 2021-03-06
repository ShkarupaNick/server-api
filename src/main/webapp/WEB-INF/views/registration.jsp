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
<jsp:include page="nav-bar.jsp"/>

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
                                <form:errors path="username" class="error-text"></form:errors>
                                <div class="input-group ${status.error ? 'has-error' : ''}">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-user"></span></span>
                                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                                autofocus="true"></form:input>
                                </div>

                                <form:errors path="email" class="error-text"></form:errors>
                                <div class="input-group ${status.error ? 'has-error' : ''}">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                    <form:input path="email" class="form-control"
                                                placeholder="Email"></form:input>
                                </div>

                                <form:errors path="password" class="error-text"></form:errors>
                                <div class="input-group ${status.error ? 'has-error' : ''}">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-lock"></span></span>
                                    <form:input type="password" path="password" class="form-control"
                                                placeholder="Password"></form:input>
                                </div>

                                <form:errors path="repeatPassword" class="error-text"></form:errors>
                                <div class="input-group ${status.error ? 'has-error' : ''}">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-lock"></span></span>
                                    <form:input path="repeatPassword" type="password" class="form-control"
                                                placeholder="Repeat password"></form:input>
                                </div>
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
