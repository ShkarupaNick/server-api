<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:forEach var="comment" items="${com.children}">

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
                         <a ${comment.likedByCurrentUser?"liked='true'":"liked='false'"} class="like glyphicon ${comment.likedByCurrentUser?"glyphicon-heart":"glyphicon-heart-empty"}"></a> <a>${comment.likeCnt}</a></span>
                    </div>
                    <c:if test="${fn:length(comment.children)>0}">
                        <c:set var="com" value="${comment}" scope="request"/>
                        <jsp:include page="comment.jsp">
                            <jsp:param name="com" value="${com}"/>
                        </jsp:include>
                    </c:if>

                </div>
            </div>

</c:forEach>
