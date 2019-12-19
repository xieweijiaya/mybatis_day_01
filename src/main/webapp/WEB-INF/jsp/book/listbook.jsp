<%@ page import="java.lang.annotation.Target" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/head.jsp"%>
<%@ taglib prefix="m" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="r" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <style>
        table th{
            text-align: center;
            font-size: 20px;
        }
    </style>
    <script>
        function del(id) {
            var flag = confirm("您确定要永久删除该信息吗?");
            if (flag == true) {
                //点击确定后操作
                location.href = "${ctx}/book/delbook?bookId=" + id;
            }else{
                alert("已取消。。。。。")
            }
        }
        function edit(id) {
            var cells = $("#"+id);
            $("#bookId").val(id);
            var bookName = cells.find('td:eq(1)').html();//找到td元素
            var price = cells.find('td:eq(2)').html();//找到td元素
            $("#bookName").val(bookName);
            $("#price").val(price);
            $('#myModal').modal('show');
        }
        function toUpload(bookId) {
            location.href = "${ctx}/book/toUpload?bookId="+bookId;
        }
    </script>
    <title>BookHome</title>
</head>
<body>
<h3 style="text-align: center"><m:message code="title"/></h3>
<a href="${ctx}/book/listJsonBook">json数据</a>
<form action="https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add?access_token=24.b78a59e806f37fcb6e354225a234de5e.2592000.1578657477.282335-18002380" method="post">
    <input type="submit" value="新增人脸信息"/>
</form>

<form class="text-center" action="#" method="post">
    <m:message code="input.label"/><input type="text"/>
    <input type="submit" value=" <m:message code="submit.btn"/>"/>
    <r:hasPermission name="bookmanager:book:add">
    <a href="${ctx}/book/addbook"><m:message code="add.label"/></a>
    </r:hasPermission>
    <br/>
    <a href="${ctx}/changeLocale?locale=zh"><m:message code="zh.label"/></a>
    <a href="${ctx}/changeLocale?locale=us"><m:message code="us.label"/></a>
</form>
<hr/>
<table class="table table-bordered table-hover table-striped table-condensed text-center">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>价格</th>
        <th>图片</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${listbook}" var="book">
        <tr id="${book.bookId}">
            <td>${book.bookId}</td>
            <td>${book.bookName}</td>
            <td>${book.price}</td>
            <td width="130px">
                <c:if test="${empty book.img}">
                    暂无图片
                </c:if>
                <c:if test="${not empty book.img}">
                    <img src="${ctx}/download?fileId=${book.img}" class="img-thumbnail" width="80px" height="80px">
                </c:if>
            </td>
            <td style="width: 300px">
                <r:hasRole name="管理员">
                <button onclick="del(${book.bookId})" class="btn btn-danger">删除</button>
                <button onclick="edit(${book.bookId})" class="btn btn-info">编辑</button>
                <button onclick="toUpload(${book.bookId})" class="btn btn-success">文件上传</button>
                </r:hasRole>
            </td>
        </tr>
    </c:forEach>

</table>
<div class="text-center">
    <label>
        <a href="${ctx}/book/listbook?page=1">首页</a>
        <a href="${ctx}/book/listbook?page=${pageBean.frontPage}">上一页</a>
        /共<span>【${pageBean.total}】</span>条/当前第<span>【${pageBean.page}】</span>页/每页<span>【${pageBean.rows}】</span>条/
        <a href="${ctx}/book/listbook?page=${pageBean.nextPage}">下一页</a>
        <a href="${ctx}/book/listbook?page=${pageBean.maxPage}">尾页</a>
    </label>
</div>

<!-- 按钮触发模态框 -->
<button data-toggle="modal" class="hide" data-target="#myModal"></button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">编辑书本信息</h4>
            </div>
            <form action="${ctx}/book/editbook" method="post">
            <div class="modal-body">
                <div class="form-group">
                    <label>书本编号</label>
                    <input type="text" id="bookId" name="bookId" class="form-control">
                </div>
                <div class="form-group">
                    <label>书本名称</label>
                    <input type="text" class="form-control" id = "bookName" name = "bookName" placeholder="请输入书本名称">
                </div>
                <div class="form-group">
                    <label>书本价格</label>
                    <input type="text" class="form-control" id = "price" name = "price" placeholder="请输入书本价格">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <%--<button type="submit"class="btn btn-primary">提交更改</button>--%>
                <input type="submit" value="提交更改" class="btn btn-primary"/>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>
