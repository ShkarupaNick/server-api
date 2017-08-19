function loadCommentEvents() {
    $(".like").click(function () {
        var commentUUID = $(this).parent().parent().parent().attr('id');
        var tempScrollTop = $(window).scrollTop();
        var root = $(this);
        if ($(this).attr('liked') == 'true') {
            $(this).removeClass("glyphicon-heart");
            $(this).addClass("glyphicon-heart-empty");
            $(this).attr("liked", "false");
            $.ajax({
                type: "post",
                url: "/comment/dislike",
                contentType: 'application/json',
                data: JSON.stringify({
                    commentUUID: commentUUID,
                    userId: $("#user").attr("userId")
                }),
                success: function (msg) {
                    console.log(msg.likeCnt);
                    root.parent().children().last().text(" " + msg.likeCnt);

                },
                error: function (xhr,
                                 ajaxOptions,
                                 thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
                }
            });
        } else {
            $(this).removeClass("glyphicon-heart-empty");
            $(this).addClass("glyphicon-heart");
            $(this).attr("liked", "true");
            $.ajax({
                type: "post",
                url: "/comment/like",
                contentType: 'application/json',
                data: JSON.stringify({
                    commentUUID: commentUUID,
                    userId: $("#user").attr("userId")
                }),
                success: function (msg) {
                    console.log(msg.likeCnt);
                    root.parent().children().last().text(" " + msg.likeCnt);

                },
                error: function (xhr,
                                 ajaxOptions,
                                 thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
                }
            });
        }
        $(window).scrollTop(tempScrollTop);
    });




    $(".reply").click(function reply() {
        var root = $(this);
        $(this).parent().parent().parent().append("<div class='inputTextDiv'><textarea  class='form-control' name='addComment' rows='2'></textarea></div>");
        $(this).parent().parent().parent().append("<button type='button' class='btn btn-default comment-button'>Submit</button>");
        var button = $(this).parent().parent().parent().children().last();
        button.click(function () {
            console.log(getUrlParameter("uuid"));
            console.log(root.parent().parent().parent().attr("id"));
            console.log(root.parent().parent().parent().children("div").children("textarea").val());
            $.ajax({
                type: "post",
                url: "/comment/save",
                contentType: 'application/json',

                data: JSON.stringify({
                    "parent": {
                        "uuid": root.parent().parent().parent().attr("id")
                    },
                    "item": {
                        "uuid": getUrlParameter("uuid")
                    },
                    "text": root.parent().parent().parent().children("div").children("textarea").val()

                }),
                success: function (msg) {
                    console.log(msg);
                    button.prev().remove();
                    var parent = button.parent();
                    button.remove();
                    parent.append("<div class='media'><div class='media-left'><a><img class='media-object img-rounded img-avatar' src='data:image/jpg;base64," + msg.user.profilePictureString + "'></a></div><div class='media-body' id='" + msg.uuid + "'><div class='media-heading'><div class='author'>" + msg.user.username + "</div><div class='metadata'><span class='date'>" + msg.date + "</span></div></div><div class='media-text text-left'>" + msg.text + "</div><div class='footer-comment'><span class='comment-reply'><a class='reply' style='cursor: pointer;'>reply </a><a liked='false' class='like glyphicon glyphicon-heart-empty'/><a> 0</a></span></div></div></div></div>")
                },
                error: function (xhr,
                                 ajaxOptions,
                                 thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
                }
            });
        });
    });

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
}


/*$(function() {

 // We can attach the `fileselect` event to all file inputs on the page
 $(document).on('change', ':file', function() {
 var input = $(this),
 numFiles = input.get(0).files ? input.get(0).files.length : 1,
 label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
 input.trigger('fileselect', [numFiles, label]);
 });

 // We can watch for our custom `fileselect` event like this
 $(document).ready( function() {
 $(':file').on('fileselect', function(event, numFiles, label) {

 var input = $(this).parents('.input-group').find(':text'),
 log = numFiles > 1 ? numFiles + ' files selected' : label;

 if( input.length ) {
 input.val(log);
 } else
 if( log ) alert(log);
 }

 });
 });

 });

 */