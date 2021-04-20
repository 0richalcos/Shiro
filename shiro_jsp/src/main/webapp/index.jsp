<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>主页</title>
</head>
<body>
<h1>系统主页 v1.0</h1>
<h2><a href="${pageContext.request.contextPath}/logout">退出登录</a></h2>
<ul>
    <shiro:hasAnyRoles name="user,admin">
        <li>
            <a href="">用户管理</a>
            <ul>
                <shiro:hasPermission name="user:add:*">
                    <li><a href="${pageContext.request.contextPath}/save">添加</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:delete:*">
                    <li><a href="">删除</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:update:*">
                    <li><a href="">修改</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:find:*">
                    <li><a href="">查询</a></li>
                </shiro:hasPermission>
            </ul>
        </li>
    </shiro:hasAnyRoles>
    <shiro:hasRole name="admin,product">
        <li><a href="">商品管理</a></li>
    </shiro:hasRole>
    <shiro:hasRole name="admin">
        <li><a href="">订单管理</a></li>
    </shiro:hasRole>
    <shiro:hasRole name="admin">
        <li><a href="">物流管理</a></li>
    </shiro:hasRole>
</ul>
</body>
</html>