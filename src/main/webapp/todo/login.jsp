<%--
  Created by IntelliJ IDEA.
  User: jieunlee
  Date: 2024. 9. 10.
  Time: 오전 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>login</title>
<style>
  div {
    margin: 10px;
    height: 30px;
  }
input {
  width: 100px;
}
button {
  width: 205px;
}
</style>
</head>
<body>
<c:if test="${param.result == 'error'}">
    <h1>로그인 에러</h1>
</c:if>
<form action="/login" method="post">
    <div>
        <input type="text" name="id" placeholder="ID" required>
        <input type="text" name="pw" placeholder="PW" required>
    </div>
    <div>
        <button type="submit">LOGIN</button>
    </div>

</form>
</body>
</html>
