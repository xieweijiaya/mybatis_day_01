<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>身份证验证</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/Cart/CartLogin" method="post">
        身份证号:<input type="text" name="idNo"/><br>
        姓名:<input type="text" name="name"/><br/>
        <input type="submit" value="校验"/>
    </form>
</body>
</html>
