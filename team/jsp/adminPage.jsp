<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css"/>
<link rel="stylesheet" href="./css/admin.css">

<title>管理者画面</title>


</head>

<body>

	<div>
	<s:include value="adminHeader.jsp" />

	<div class="admin-complete">

		<div class="top1">
			<h1>管理者画面</h1>
		</div>

			<div class="admin">

				<!-- 商品追加画面に移動 -->
				<form action="ProductAddAction" method="post">
					<s:submit value="商品追加" class="btn" />
				</form>
				<br><br>
				<!-- 商品削除画面に移動 -->
				<form action="ProductDeleteAction" method="post">
					<input type="hidden" name="pageNo" value="1">
					<s:submit value="商品削除" class="btn">
					</s:submit>
				</form>

			</div>

		</div>
		<s:include value="footer.jsp" />
	</div>

</body>
</html>