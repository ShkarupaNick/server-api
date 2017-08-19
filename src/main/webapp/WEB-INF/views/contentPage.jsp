<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date"/>

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
<sec:authorize access="isAuthenticated()">
    <body onload="loadCommentEvents()">
</sec:authorize>
<sec:authorize access="isAnonymous()">
    <body>
</sec:authorize>
<jsp:include page="nav-bar.jsp"/>

<div class="container">
    <!--Carousel -->
    <div class="DivClearGorizontal"></div>
    <!-- Section -->
    <div class="col-lg-8">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-lg-4">
                    <a href="#"><img class="img-responsive img-galery img-hover" src="${item.show.image.original}"
                                     alt=""></a>
                </div>
                <div class="col-lg-8">
                    <h4>${item.show.network.name}</h4>
                    <h5>${item.show.summary}</h5></div>
            </div>
        </div>
        <div class="panel panel-default">
            <sec:authorize access="isAnonymous()">
                <div class="panel-heading">
                    <h4 class="page-header">Log in or <a href="/registration">Sign up</a> to write a comment</h4>
                </div>
            </sec:authorize>
            <div class="comments">
                <sec:authorize access="isAuthenticated()">

                    <div class="media-left">
                        <a href="#">
                            <img class="media-object img-rounded img-avatar"
                                 src="data:image/jpg;base64,<c:out value='${user.profilePictureString}'/>">
                        </a>
                    </div>
                    <div class="media-body">
                        <div class="media-heading">
                            <div class="form-group">
                                <label for="exampleTextarea">Leave your comment here...</label><textarea
                                    class="form-control" id="exampleTextarea"
                                    rows="1"></textarea>
                            </div>

                        </div>
                    </div>
                </sec:authorize>
                <ul class="media-list">
                    <c:forEach var="comment" items="${comments}">
                        <div class="media">
                            <div class="media-left">
                                <a href="#">
                                    <img class="media-object img-rounded img-avatar" src="data:image/jpg;base64,
                                <c:out value='${comment.user.profilePictureString}'/>">

                                </a>
                            </div>
                            <div class="media-body" id="${comment.uuid}">
                                <div class="media-heading">
                                    <div class="author">${comment.user.username}</div>
                                    <div class="metadata">
                                        <span class="date">${comment.date}</span>
                                    </div>
                                </div>
                                <div class="media-text text-left">${comment.text}</div>
                                <div class="footer-comment">
                                    <span class="comment-reply">
                                         <a class="reply" style="cursor: pointer;">reply</a>
                                            <a ${comment.likedByCurrentUser?"liked='true'":"liked='false'"}
                                                    class="like glyphicon ${comment.likedByCurrentUser?"glyphicon-heart":"glyphicon-heart-empty"}"></a> <a>${comment.likeCnt}</a></span>
                                </div>
                                <c:set var="com" value="${comment}" scope="request"/>
                                <jsp:include page="comment.jsp" />
                            </div>
                        </div>
                    </c:forEach>
                </ul>
            </div>

        </div>
    </div>
    <div class="col-lg-4">
        <div class="panel panel-default">
            <div class="comments">
                <h3 class="text-center">Top comments</h3>
                <ul class="media-list">
                    <c:forEach items="${topComments}" var="comment">

                        <li class="media">
                            <div class="media-left">
                                <a href="#">
                                    <img class="media-object img-rounded img-avatar" src="data:image/jpg;base64,
                                    <c:out value='${comment.user.profilePictureString}'/>">
                                </a>
                            </div>
                            <div class="media-body">
                                <div class="media-heading">
                                    <div class="author">${comment.user.username}</div>
                                    <div class="metadata">
                                        <a href="#" class="glyphicon glyphicon-heart-empty"></a> 1333
                                    </div>
                                </div>
                                <div class="media-text text-left">${comment.text}</div>
                            </div>
                        </li>

                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>


<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/custom.js"></script>

</body>
</html>