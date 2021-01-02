/**
 *コメントする
 */
function sentComment() {
    //
    var textId = $("#text_id").val();
    // コメントを取得する
    var content = $("#comment_content").val();
    sent(textId, 1, content);
}

/**
 *
 * @param commentId
 * @param content
 */
function sentSecondComment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-"+commentId).val();
    sent(commentId, 2, content)
}

/**
 * 共有方法
 * @param targetId
 * @param type
 * @param content
 */
function sent(targetId, type, content) {
    console.log(targetId);
    console.log(content);
    // コメントをチェックする
    if (!content) {
        alert("回复不能为空！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            "comment": content,
            "parentId": targetId,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response == 2001) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.3e4bf8c3d2021cde&redirect_uri=http://localhost:8080/callback&scope=user&status=1");
                        window.localStorage.setItem("closable", "true");
                    }
                    // ログイン画面を閉じる

                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json",
        contentType: "application/JSON"
    });
}

/**
 *展开二级评论
 */
function collapseComment(e) {
    var commentId = e.getAttribute("data-id");
    var comments = $("#comment-" + commentId);
    // 获取二级元素展开状态
    let collapse = e.getAttribute("collapse");
    if (collapse) {
        comments.removeClass("in");
        e.removeAttribute("collapse");
        e.classList.remove("active");
    } else {
        // 如果已经获取到元素，就不再获取
        var subCommentContainer = $("#comment-" + commentId);
        if (subCommentContainer.children().length != 1){
            // 展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("collapse", "in");/*是否点击评论的flg*/
            e.classList.add("active");/*高亮*/
        }else {
            $.getJSON("/comment/"+commentId,function (data) {
                console.log(data);
                // 自绘二级评论

                var items=[];
                $.each(data.data,function (index,comment) {
                    console.log(index);
                    console.log(comment);
                    debugger;
                    // 二级评论
                    var c = $("<div>",{
                        "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12",
                        html:comment.comment

                    });
                    subCommentContainer.prepend(c);
                });

                // 展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("collapse", "in");/*是否点击评论的flg*/
                e.classList.add("active");/*高亮*/

            });
        }



    }

}