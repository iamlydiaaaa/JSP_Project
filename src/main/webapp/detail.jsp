<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/10/19
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
detail
dtlcont의 모든 img 태그를 작동 안되게 하고 img_url 사용?
${fn:replace(fn:replace(culture.getDtlcont(), "\\r\\n", " "), "\\t", " ")}
</body>
</html>
