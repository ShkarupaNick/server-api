<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-100838060-1', 'auto');
    ga('send', 'pageview');

</script>

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

                <sec:authorize access="isAnonymous()">
                    <li><a href="registration">Sign up</a></li>
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

                                        <form:form method="POST" class="form" role="form" action="login" 
                                                   modelAttribute="userForm" accept-charset="UTF-8" id="login-nav">
                                                <div class="form-group">
                                                    <label class="sr-only" for="exampleInputLogin2">email</label>
                                                    <form:input path="email" id="exampleInputLogin2" name="email"
                                                                type="text"
                                                                class="form-control" placeholder="Email"
                                                                autofocus="true"/>
                                                </div>

                                                <div class="form-group">
                                                    <label class="sr-only" for="exampleInputPassword2">Password</label>
                                                    <form:input path="password" name="password" type="password"
                                                                class="form-control"
                                                                id="exampleInputPassword2"
                                                                placeholder="Password"/>
                                                    <div class="help-block text-right"><a href="">Forget the password
                                                        ?</a>
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
                                        </form:form>
                                    </div>
                                    <div class="bottom text-center">
                                        New here? <a href="registration"><b>Sign up</b></a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li><img class="media-object img-rounded img-avatar-header"
                             src="data:image/jpg;base64,<c:out value='${user.profilePicture!=null?user.profilePictureString:"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"}'/>">
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" userId="${user.id}" id="user">${user.username}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile">Profile</a></li>
                            <li><a href="#">Favorites</a></li>
                            <li><a href="#">Settings</a></li>
                            <li><a href="/logout">Log off</a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
