<%--
  Created by IntelliJ IDEA.
  User: sajith
  Date: 8/5/18
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1 align="center">Welcome <%=session.getAttribute("username")%></h1>
<div align="center">
    <form action="validate" method="POST">
        <label><b>Secret Key</b></label>
        <input type="password" placeholder="Enter Secret Key" name="key">
        <input type="hidden" id="tokentxt" name="tokentxt" value="<%=session.getAttribute("csrfToken")%>"/>
        <input type="submit" value="Submit"/>
    </form>
<div/>
</body>
</html>
