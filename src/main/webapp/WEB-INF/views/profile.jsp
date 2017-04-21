<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>profile</title>

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
<div class="container">
    <h1>Edit Profile</h1>
    <hr>
    <div class="row">
        <!-- left column -->
        <div class="col-xs-3 col-sm-3 col-lg-3">
            <div class="text-center">
                <img src="data:image/jpeg;base64,${user_avatar}" class="avatar img-circle" alt="avatar">
                <h6>Upload the photo...</h6>
                <form:form method="POST" role="form" modelAttribute="userForm" action="loadavatar"
                           enctype="multipart/form-data">
                    <spring:bind path="profilePictureFile">
                    <span class="btn btn-default btn-file">
                    Browse <form:input type="file" path="profilePictureFile" onchange="this.form.submit()" class="form-control" placeholder="Avatar"></form:input>
                </span>
                    </spring:bind>
                </form:form>
            </div>
        </div>
        <div class="col-xs-9 col-sm-9 col-lg-9">
            <div class="panel-body">
                <div class="col-md-12 col-sm-12 col-md-12 separator login-box">
                    <br>
                    <form:form method="POST" role="form" modelAttribute="userForm">
                        <spring:bind path="profilePicture">
                            <input type="hidden" name="profilePicture" value="${user_avatar}"/>
                        </spring:bind>

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
                                <form:input path="email" class="form-control" placeholder="Email"></form:input>
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
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<hr>
</body>
</html>
