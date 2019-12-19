<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/head.jsp"%>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="${ctx}/upload" method="post" enctype="multipart/form-data">
    书本编号:<input type="text" name="bookId" value="${param.bookId}" /><br/>
    <input type="file" name = "file"/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
