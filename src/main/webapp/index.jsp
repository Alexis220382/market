<%--
  Created by IntelliJ IDEA.
  User: B50-30
  Date: 01.07.2015
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Entries</title>
  <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="SellerController" class="login">
  <p class="forgot-password"><a href="SellerController">Registration</a></p>
</form>
<form action="LoginController" class="login">
  <p>
    <label for="login">Login:</label>
    <input type="text" name="login" id="login" value="Write your last name" required>
  </p>

  <p>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" value="111111111" required>
  </p>

  <p class="login-submit">
    <button type="submit" name="btnOK" class="login-button">Login</button>
  </p>
</form>
</body>
</html>
