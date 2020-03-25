


/**
 *コメントする
 */
function sentComment() {
    //
    var textId = $("#text_id").val();
    // コメントを取得する
    var content = $("#comment_content").val();

    console.log(textId);
    console.log(content);
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            "comment": content,
            "parentId":textId,
            "type":1
        }),
        success: function (response) {
            if (response.code==200){
                $("#comment_section").hide();
            }else {
                if (response==2001){
                    var isAccepted = confirm(response.message);
                    if (isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.3e4bf8c3d2021cde&redirect_uri=http://localhost:8080/callback&scope=user&status=1");
                        window.localStorage.setItem("closable","true");
                    }
                    // ログイン画面を閉じる

                }else {
                    alert(response.measure());
                }
            }
        },
        dataType: "json",
        contentType:"application/JSON"
    });
}