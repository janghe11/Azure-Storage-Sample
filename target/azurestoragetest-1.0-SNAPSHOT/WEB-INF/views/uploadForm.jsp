<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 18. 11. 29
  Time: 오전 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id='form1' action = "uploadForm" method="post"
      enctype="multipart/form-data">
    <input type="file" name="files" multiple="multiple"> <input type="submit">
</form>

</body>
</html>