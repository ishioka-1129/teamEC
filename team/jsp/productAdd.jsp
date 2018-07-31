<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css"/>
<link rel="stylesheet" href="./css/admin.css">

<title>商品追加</title>


<!-- css等は後に追加 -->

	<script>
		showImage(false);

		$("#loadFile").onchange = function(evt) {
			showImage(false);
			var files = evt.target.files;
			if (files.length == 0)
				return;
			var file = files[0];
			if (!file.type.match(/image/)) {
				alert('画像ファイルを選んでください');
				return;
			}
			var reader = new FileReader();
			reader.onload = function(evt) {
				$("#thumb").src = reader.result;
				showImage(true);
			}
			reader.readAsDataURL(file);
		}

		function showImage(b) {
			var val = b ? "block" : "none";
			$("#up_btn").style.display = val;
			$("#thumb").style.display = val;
		}

		function $(id) {
			return document.querySelector(id);
		}

</script>

</head>

<body>

	<div>
	<s:include value="adminHeader.jsp" />

<div class="admin-main">

	<div class="top">
		<h1>商品追加画面</h1>
	</div>

		<div>
			<s:form method="post" action="ProductAddConfirmAction" enctype="multipart/form-data">

				<table class="addList">

					<!-- エラーがある場合、各textfieldの上にエラーメッセージを表示する -->
					<tr class="errorMessage">
                        <td colspan="2"><s:property value="productIdError"/><s:property value="productIdCheckError"/></td>
                    </tr>
					<tr>
						<th>商品ID:</th>
						<td><s:textfield name="productId" value="%{productId}" placeholder="半角数字４ケタまで"/></td>
					</tr>

					<tr class="errorMessage">
                        <td colspan="2"><s:property value="productNameError"/></td>
                    </tr>
                    <tr class="errorMessage">
                        <td colspan="2"><s:property value="productNameCheckError"/></td>
                    </tr>
					<tr>
						<th>商品名:</th>
						<td><s:textfield name="productName" value="%{productName}" placeholder="商品名"/></td>
					</tr>

					<tr class="errorMessage">
                        <td colspan="2"><s:property value="productNameKanaError"/></td>
                    </tr>
					<tr class="errorMessage">
                        <td colspan="2"><s:property value="productNameKanaCheckError"/></td>
                    </tr>
					<tr>
						<th>商品名(かな):</th>
						<td><s:textfield name="productNameKana" value="%{productNameKana}" placeholder="商品名(かな)"/></td>
					</tr>

					<tr class="errorMessage">
                        <td colspan="2"><s:property value="productDescriptionError"/></td>
                    </tr>
					<tr>
						<th>商品詳細:</th>
						<td><s:textfield name="productDescription" value="%{productDescription}" placeholder="商品詳細"/></td>
					</tr>

					<!-- 既存カテゴリーから選択式にする -->
					<tr>
						<th>カテゴリーID:</th>
						<td>
							<s:select name="categoryId" list="#session.adminCategoryDTOList"
							listValue="categoryName" listKey="categoryId" class="select" id="categoryId" value="%{categoryId}"/>
						</td>
					</tr>

					<!-- 価格が上限下限を超える場合警告文が出るようにする -->
					<tr class="errorMessage">
                        <td colspan="2"><s:property value="priceError" /></td>
                    </tr>
					<tr>
						<th>価格:</th>
						<td><input name="price" type="number" min="100" max="1000000" value="<s:property value='%{price}' />"></td>
					</tr>

					<tr class="errorMessage">
                        <td colspan="2"><s:property value="releaseCompanyError"/></td>
                    </tr>
					<tr>
						<th>発売会社:</th>
						<td><s:textfield name="releaseCompany" value="%{releaseCompany}" placeholder="発売会社"/></td>
					</tr>

					<tr class="errorMessage">
                        <td colspan="2"><s:property value="imageFilePathError"/></td>
                    </tr>
                    <!-- 初期状態はjpgとjpegしか選べないようにする  -->
					<tr>
						<th>商品画像:</th>
						<td class="image"><s:file name="userImage" accept='image/jpg,image/jpeg'/></td>
					</tr>

				</table>

					<br><s:submit value="商品を追加" class="btn1"/><br>

			</s:form>
			</div>

		</div>
		<s:include value="footer.jsp" />


	</div>

</body>
</html>