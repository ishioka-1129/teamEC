<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="3;URL=AdminAction" />
<link rel="stylesheet" href="./css/admin.css">

<title>商品追加完了</title>


</head>

<body>

	<div>
	<s:include value="adminHeader.jsp" />

		<div class="admin-complete">
			<h2>商品を追加しました</h2><br><br>
			<h2>３秒後に管理者画面に戻ります</h2>

		</div>
		<s:include value="footer.jsp" />

	</div>

</body>
</html>