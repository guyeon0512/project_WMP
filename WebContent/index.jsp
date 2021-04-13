<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<style>
  table,td {
    border: 1px solid #444444;
    padding: 5px;
  }
  #td1 {
    width:120px;
  }
  #td2 {
    width:300px;
  }
  
</style>
<title>입력</title>
</head>
<body>
<div>
<form action="/PrintServlet" method="post" name = "inputScreen">
<table>
<tr><td colspan="2" style="text-align:center">입력</td></tr>
<tr>
<td id="td1">URL</td>
<td id="td2"><input type ="text" name ="url" size=50/></td>
</tr>
<tr>
<td id="td1">Type</td>
<td id="td2">
	<select name="type" id="type-select" style="width:170px">
    <option value="0">HTML 태그제외</option>
    <option value="1">Text 전체</option>
	</select>
</td>
</tr>
<tr>
<td id="td1">출력 단위 묶음</td>
<td id="td2"><input type ="number" name ="unit" id ="unit" min="0" max="1000000000"/></td>
</tr>
<tr><td colspan="2" align="right"><input type="submit" value="출력" id="btn"></td></tr>
</table>
</form>
</div>
</body>
</html>