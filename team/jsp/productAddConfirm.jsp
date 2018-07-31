<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/admin.css">

<title>商品追加確認</title>


</head>

<body>

	<s:include value="adminHeader.jsp" />

		<div class="admin-main2">

		<div class="top2">
			<h1>商品追加確認</h1><br>
		</div>

			<h2>以下の内容でお間違いないですか？</h2>
		<div>

				<!-- 入力された情報を表示する -->
				<div class="productAdd-left">
					<img src='images/<s:property value="userImageFileName"/>'>
				</div>

				<div class="productAdd-right">
					<table>
						<tr>
							<th>商品ID:</th>
							<td><s:property value='#session.productId' /></td>
						</tr>
						<tr>
							<th>商品名:</th>
							<td><s:property value='#session.productName' /></td>
						</tr>
						<tr>
							<th>商品名(かな):</th>
							<td><s:property value='#session.productNameKana' /></td>
						</tr>
						<tr>
							<th>商品詳細:</th>
							<td><s:property value='#session.productDescription' /></td>
						</tr>
						<tr>
							<th>カテゴリー:</th>
							<td><s:if test="#session.categoryId == 2">
									<label>手持ち花火</label>
								</s:if>
								<s:elseif test="#session.categoryId == 3">
									<label>打ち上げ花火</label>
								</s:elseif>
								<s:elseif test="#session.categoryId == 4">
									<label>花火職人</label>
								</s:elseif>
							</td>
						</tr>
						<tr>
							<th>価格:</th>
							<td><s:property value='#session.price' /></td>
						</tr>
						<tr>
							<th>発売会社:</th>
							<td><s:property value='#session.releaseCompany' /></td>
						</tr>
					</table>
				</div>

				<div class="clearfix"></div>
				<div class="center">
					<div class="button">
						<s:form action="ProductAddCompleteAction">
							<s:submit value="追加する" class="btn"/>
						</s:form>
					</div>
					<!-- 修正ボタン 戻った際入力内容を保持する -->
					<div class="button">
						<s:form action="ProductAddAction" method="post">
							<s:hidden name="productId" value="%{productId}"/>
							<s:hidden name="productName" value="%{productName}"/>
							<s:hidden name="productNameKana" value="%{productNameKana}"/>
							<s:hidden name="productDescription" value="%{productDescription}"/>
							<s:hidden name="categoryId" value="%{categoryId}"/>
							<s:hidden name="price" value="%{price}"/>
							<s:hidden name="releaseCompany" value="%{releaseCompany}"/>
							<s:submit value="戻って修正する" class="btn"/>
						</s:form>
					</div>
				</div>
			</div>
		</div>

		<s:include value="footer.jsp" />


</body>
</html>