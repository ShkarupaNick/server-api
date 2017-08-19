<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>CouchTalks</title>

    <!-- Bootstrap -->
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/carousel.css" rel="stylesheet">
    <!--link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/img/favicon.ico" /-->

    <script src="${contextPath}/resources/js/jquery-1.4.4.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <!--script src="${contextPath}/resources/js/custom.js"></script-->


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<jsp:include page="nav-bar.jsp"/>

<!-- Carousel
=====================================================-->
<div class="container">
    <div id="myCarousel" class="carousel slide">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <div class="carousel-inner">
            <div class="item active">
                <div class="lite" style="background-image:url('${contextPath}/resources/img/1.jpg')"></div>
                <div class="carousel-caption">
                    <h2>Life</h2>
                </div>
            </div>
            <div class="item">
                <div class="lite" style="background-image:url('${contextPath}/resources/img/2.jpg')"></div>
                <div class="carousel-caption">
                    <h2>Life</h2>
                </div>
            </div>
            <div class="item">
                <div class="lite" style="background-image:url('${contextPath}/resources/img/3.jpg')"></div>
                <div class="carousel-caption">
                    <h2>Life</h2>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div> <!--Carousel -->
    <div class="DivClearGorizontal"></div>
    <!-- Section -->
    <div class="col-lg-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="page-header">Most popular</h3>
            </div>
            <div class="panel-body" name="mpop">
                <c:forEach items="${items}" var="item">
                    <div class="col-md-3 col-sm-3">
                        <a href="/content_page?uuid=${item.uuid}">
                            <img class="img-responsive img-galery img-hover" src="${item.show.image.medium}">
                        </a>
                        <div class="panel-footer">
                            <h4>${item.show.network.name}</h4>
                            <h4>${item.airtime}</h4>
                            <h5>${item.show.name}</h5>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!--============================================-->

    <div class="col-lg-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="page-header">Live</h3>
            </div>
            <div class="panel-body" name="live">
                <c:forEach items="${liveItems}" var="liveItem">
                    <div class="row">
                        <div class="col-sm-3">
                            <a href="#">
                                <img class="img-responsive img-portfolio img-hover" src="${liveItem.show.image.medium}">
                            </a>
                        </div>
                        <div class="col-rg-3">
                            <div class="record-text">
                                <h5>${liveItem.airtime}</h5>
                                <h5>${liveItem.show.name}</h5>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<!-- Footer
<footer>
    <div class="row">
        <div class="col-lg-12">
            <p>&copy; Discuss 2016</p>
        </div>
    </div>
</footer> -->


<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/registration.js"></script>
<script>
    $('.carousel').carousel({
        interval: 5000
    })
</script>
</body>
</html>