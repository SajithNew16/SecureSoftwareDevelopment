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
Welcome <%=session.getAttribute("username")%>
<%--<%=token = session.getAttribute("username")%>--%>
<form action="validate" method="POST">
    <label><b>Identy Card Number</b></label>
    <input type="text" placeholder="Enter Identy Card Number" name="id">
    <label><b>Secret Key</b></label>
    <input type="password" placeholder="Enter Secret Key" name="key">
    <input type="text" id="tokentxt" name="tokentxt"/>
    <input type="submit" value="Submit"/>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: 'validate',
        success: function (data) {
            $("#tokentxt").val(data.csrfToken);
        },
        error: function (xhr, status, error) {
            alert(status);
        }
    });
</script>

</body>
</html>
