var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

var buttonClickFunc = function (button) {
    var parentCont = button.parent().attr("id");
    console.log("\n\n\n-----------------\n\n\n")

    var request = JSON.stringify({
         "parent": {
            "uuid": button.parent().attr("id")
        },
         "item": {
            "uuid": getUrlParameter("uuid")
        },
         "text": button.parent().parent().children("div").children("div").children("textarea").val()

    });
    console.log("request: "+request);
    $.ajax({
        type: "post",
        url: "/comment/save",
        contentType: 'application/json',
        data: request,
        success: function (msg) {
            console.log("response: ");
            console.log(msg);
            var media = button.parent().parent();
            var reply;
            console.log(parentCont!=null);

            if(parentCont!=null) {
                var parent = button.parent();
                parent.children(".footer-comment").children(".comment-reply").children(".reply").attr('enabled',"false"); /*флаг дл яродительского елемента*/
                button.prev().remove();
                button.remove();
                parent.append("<div class='media'><div class='media-left'><a><img class='media-object img-rounded img-avatar' src='data:image/jpg;base64," + msg.user.profilePictureString + "'></a></div><div class='media-body' id='" + msg.uuid + "'><div class='media-heading'><div class='author'>" + msg.user.username+ "</div><div class='metadata'><span class='date'>" + msg.createdDate + "</span></div></div><div class='media-text text-left'>" + msg.text + "</div><div class='footer-comment'><span class='comment-reply'><a class='reply' style='cursor: pointer;'>reply </a><a liked='false' class='like glyphicon glyphicon-heart-empty'/><a> 0</a></span></div></div></div></div>");
                reply = parent.children(".media").children(".media-body").children(".footer-comment").children(".comment-reply").children(".reply");
            } else {
                media.after("<div class='media'><div class='media-left'><a><img class='media-object img-rounded img-avatar' src='data:image/jpg;base64," + msg.user.profilePictureString + "'></a></div><div class='media-body' id='" + msg.uuid + "'><div class='media-heading'><div class='author'>" + msg.user.username+ "</div><div class='metadata'><span class='date'>" + msg.createdDate + "</span></div></div><div class='media-text text-left'>" + msg.text + "</div><div class='footer-comment'><span class='comment-reply'><a class='reply' style='cursor: pointer;'>reply </a><a liked='false' class='like glyphicon glyphicon-heart-empty'/><a> 0</a></span></div></div></div></div>")
                reply = media.next().children(".media-body").children(".footer-comment").children(".comment-reply").children(".reply");
            }
            reply.next().click(function () {
                likeFunc($(this));
            });
            reply.click(function () {
                console.log($(this));
                replyFunc($(this));
            })
        },
        error: function (xhr,
                         ajaxOptions,
                         thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        }
    });
};

var replyFunc = function reply(replyBtn) {
    //var root = $(this);
    var enabled =  replyBtn.attr('enabled');
    if(enabled!='true') {
        console.log(replyBtn.parent().parent().parent());
        replyBtn.parent().parent().parent().append("<div class='inputTextDiv'><textarea  class='form-control' name='addComment' rows='2'></textarea></div>");
        replyBtn.parent().parent().parent().append("<button type='button' class='btn btn-default comment-button'>Submit</button>");
        var button = replyBtn.parent().parent().parent().children().last();
        button.click(function () {
            buttonClickFunc($(this));
        });
        replyBtn.attr('enabled','true');
    }
};


var likeFunc = function(like){
    var commentUUID = like.parent().parent().parent().attr('id');
    var request = JSON.stringify({
        commentUUID: commentUUID,
        userId: $("#user").attr("userId")
    });
    if (like.attr('liked') == 'true') {
        like.removeClass("glyphicon-heart");
        like.addClass("glyphicon-heart-empty");
        like.attr("liked", "false");


        console.log("request: " +request);
        $.ajax({
            type: "post",
            url: "/comment/dislike",
            contentType: 'application/json',
            data: request,
            success: function (msg) {
                console.log(msg.likeCnt);
                like.parent().children().last().text(" " + msg.likeCnt);

            },
            error: function (xhr,
                             ajaxOptions,
                             thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            }
        });
    } else {
        like.removeClass("glyphicon-heart-empty");
        like.addClass("glyphicon-heart");
        like.attr("liked", "true");
        $.ajax({
            type: "post",
            url: "/comment/like",
            contentType: 'application/json',
            data: request,
            success: function (msg) {
                console.log(msg.likeCnt);
                like.parent().children().last().text(" " + msg.likeCnt);
            },
            error: function (xhr,
                             ajaxOptions,
                             thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            }
        });
    }
};

function loadCommentEvents() {
    $(".like").click(function () {
        likeFunc($(this));
    });

    $(".reply").click(function () {
        replyFunc($(this));
    });

    $(".comment-button").click(function () {
        buttonClickFunc($(this));
    });
}