<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<link rel="stylesheet" href="./css/productDetails.css">
	<link rel="stylesheet" href="./css/admin.css">
	<title>商品削除確認</title>
</head>

<body>

	<!-- ヘッダー -->
	<s:include value="adminHeader.jsp" />

	<!-- 商品詳細情報表示 -->
	<div class="productDetails-main margin-bottom">

		<div class="top">
			<h2>以下の商品を削除してもよろしいですか？</h2>
		</div>

		<!-- 商品画像 -->
		<div class="productAdd-left">
			<img src='<s:property value="#session.imageFilePath"/><s:property value="#session.imageFileName"/>' ><br>
		</div>

		<div class="productAdd-right">
			<table>
				<tr>
					<th>商品名:</th>
					<td><s:property value="#session.productName"/></td>
				</tr>
				<tr>
					<th>商品名(かな):</th>
					<td><s:property value="#session.productNameKana"/></td>
				</tr>
				<tr>
					<th>値段:</th>
					<td><s:property value="#session.price"/>円</td>
				</tr>
				<tr>
					<th>発売会社名:</th>
					<td><s:property value="#session.releaseCompany"/></td>
				</tr>
				<tr>
					<th>発売年月日:</th>
					<td><s:property value="#session.releaseDate"/></td>
				</tr>
				<tr>
					<th>商品詳細情報:</th>
					<td><s:property value="#session.productDescription"/></td>
				</tr>
			</table>
		</div>

		<div class="clearfix"></div>
		<div class="center">
			<div class="button">
				<s:form action="ProductDeleteCompleteAction">
					<s:submit value="削除する" class="btn"/>
				</s:form>
			</div>
			<div class="button">
				<s:form action="ProductDeleteAction">
					<input type="hidden" name="pageNo" value="1">
					<s:submit value="戻る" class="btn"/>
				</s:form>
			</div>
		</div>
	</div>

	<!-- フッター -->
	<s:include value="footer.jsp" />

</body>
</html>