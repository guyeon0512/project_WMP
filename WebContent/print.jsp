<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���</title>
<style>
  table {
    width: 95%;
    border: 1px solid #444444;
    padding: 5px;
  }
  td {
    border: 1px solid #444444;
    padding: 5px;
  }
</style>
</head>
<body>

<% 
request.setCharacterEncoding("UTF-8"); //�޾ƿ� �������� ���ڵ�
String content = request.getParameter("content"); 
String result = request.getParameter("result"); 
String quotient = request.getParameter("quotient");
String remainder = request.getParameter("remainder");
String type = request.getParameter("type");
%>
<div>
<table style="word-break:break-all">
<tr><td colspan="2" style="text-align:center">���</td></tr>
<tr><td colspan="2"  style="background-color:yellow;">�� ���� ���</td></tr>
<tr><td width="150px">�� ���ڿ�</td>
<td>
	<c:if test="${ type == 0 }">${content}</c:if>
	<c:if test="${ type != 0 }"><c:out value="${content}" escapeXml="true"/></c:if>
</td></tr>
<tr><td width="150px">�� �����</td><td>${result}</td></tr>
<tr><td colspan="2"  style="background-color:yellow;">�� ��� ���</td></tr>
<tr><td width="150px">�� ��(${q})</td><td>${quotient}</td></tr>
<tr><td width="150px">�� ������(${r})</td><td>${remainder}</td></tr>
</table>
</div>
</body>
</html>