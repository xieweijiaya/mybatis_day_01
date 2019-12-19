<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文件上传</title>

    <style>
        .img {
            width: 150px;
            height: 90px;
        }
    </style>

</head>
<body>
<form enctype="multipart/form-data" return:false;>
    <input type="text" name='goodsName'>
    <input type="file" id="loadfile" class="imgInput" name="upfile" value="上传" multiple="multiple"/><br>
    <div id="imgCont"></div>
</form>
<button id="sub">提交</button>

<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script>
    var curFiles = [];
    $(function () {
        $(".imgInput").change(function () {
            console.log($(this));
            console.log(this.files.length);
            var files = this.files;
            if (files && files.length) {
                Array.prototype.push.apply(curFiles, files);
            }
            console.log(curFiles);
            var len = this.files.length;
            var html = "";
            for (var i = 0; i < len; i++) {
                html += '<img src="' + URL.createObjectURL($(this)[0].files[i]) + '"' +
                    'class="img"><span class="delImg" index="' + i + '">删除</span>'
            }
            $("#imgCont").html(html);
            demo();

        });


    })

    function demo() {
        $(".delImg").click(function () {
            var index = $(this).attr('index');
            delete curFiles[index];
            console.log(curFiles);
            $(this).prev().remove();
            $(this).remove();

        })

    }
</script>
<script>
    $(function () {

        $("#sub").click(function () {
            var data = new FormData($('#form')[0]);
            for (var i = 0; i < curFiles.length; i++) {
                data.append('files', curFiles[i]);
            }
            console.log('woshidata' + data);
            $.ajax({
                url: 'morePic',
                method: 'post',
                data: data,
                processData: false,
                contentType: false,
                success: function (data) {

                }

            });


        });
    })
</script>
<br>


</body>
</html>