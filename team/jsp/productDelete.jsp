<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/productList.css">
	<link rel="stylesheet" href="./css/admin.css">
	<title>商品削除</title>


		<style type="text/css">
<!--
a:link { color:white; text-decoration:none }
a:visited { color:white; text-decoration:none }
a:hover { color:white; text-decoration:none }
a:active { color:white; text-decoration:none }
-->
</style>

</head>

<body>

	<!-- ヘッダー -->
	<s:include value="adminHeader.jsp" />

	<div class="productList-main">
		<h1>商品削除画面</h1>

		<!-- エラーメッセージを表示 -->
		<s:if test="errorMessage !=null">
			<h1>
				<s:property value="errorMessage" />
			</h1>
		</s:if>


		<s:if test="#session.totalPageSize + 1 > pageNo">

		<h2>削除する商品を選択してください</h2>

		<!-- ページ番号 -->
		<div class="pager-top">
			<!-- 前へボタン -->
			<s:if test="#session.hasPreviousPage">
				<div id="num" class="bot"><a href='<s:url action="ProductDeleteAction">
				<s:param name="pageNo" value="#session.previousPageNo" />
			</s:url>'>&laquo;</a></div>
			</s:if>

			<s:iterator begin="1" end="#session.totalPageSize" status="pageNo">
				<s:if test="#session.currentPageNo == #pageNo.count">
					<div id="num" class="tom"><s:property value="%{#pageNo.count}"/></div>
				</s:if>
				<s:else>
					<div id="num" class="bot"><a href="<s:url action='ProductDeleteAction'><s:param name='pageNo' value='%{#pageNo.count}'/>
					<s:param name='categoryId' value='%{categoryId}'/><s:param name='keywords' value='%{keywords}'/></s:url>"><s:property value="%{#pageNo.count}"/></a></div>
				</s:else>
			</s:iterator>

			<!-- 次へボタン -->
			<s:if test="#session.hasNextPage">
				<div id="num" class="bot"><a href='<s:url action="ProductDeleteAction">
				<s:param name="pageNo" value="#session.nextPageNo" />
				</s:url>'>&raquo;</a></div>
			</s:if>
		</div>

		<div class="flexbox">
		<s:iterator value="#session.productInfoDTOList">
			<div class="productList-box">
				<ul>
					<li>
						<!-- 商品画像 -->
						<a href='<s:url action="ProductDeleteConfirmAction"><s:param name="productId" value="%{productId}"/></s:url>'>
							<img src='<s:property value="imageFilePath"/><s:property value="imageFileName"/>'/>
						</a><br>
						<!-- 商品名 -->
						<s:property value="productName"/><br>
						<!-- 商品名（かな） -->
						<s:property value="productNameKana"/><br>
						<!-- 価格 -->
						<s:property value="price"/>円<br>
					</li>
				</ul>
			</div>
		</s:iterator>
		</div>

		<!-- ページ番号 -->
		<div class="pager-bottom">
			<!-- 前へボタン -->
			<s:if test="#session.hasPreviousPage">
				<div id="num" class="bot"><a href='<s:url action="ProductDeleteAction">
				<s:param name="pageNo" value="#session.previousPageNo" />
				</s:url>'>&laquo;</a></div>
			</s:if>

			<s:iterator begin="1" end="#session.totalPageSize" status="pageNo">
				<s:if test="#session.currentPageNo == #pageNo.count">
					<div id="num" class="tom"><s:property value="%{#pageNo.count}"/></div>
				</s:if>
				<s:else>
					<div id="num" class="bot"><a href="<s:url action='ProductDeleteAction'><s:param name='pageNo' value='%{#pageNo.count}'/>
					<s:param name='categoryId' value='%{categoryId}'/><s:param name='keywords' value='%{keywords}'/></s:url>"><s:property value="%{#pageNo.count}"/></a></div>
				</s:else>
			</s:iterator>

			<!-- 次へボタン -->
			<s:if test="#session.hasNextPage">
				<div id="num" class="bot"><a href='<s:url action="ProductDeleteAction">
				<s:param name="pageNo" value="#session.nextPageNo" />
				</s:url>'>&raquo;</a></div>
			</s:if>
		</div>

		</s:if>
		<s:else>
			<div id="error">
				指定したページは存在しません
			</div>
		</s:else>
	</div>
	<div class="clearfix"></div>

	<!-- フッター -->
	<s:include value="footer.jsp" />


</body>
</html>