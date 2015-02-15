<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>使用者資訊</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
</head>
<body>
	<!-- 標題顯示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--頁面標題-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				使用者資訊
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--顯示表單內容-->
	<div id=MainArea>
		<s:form action="user_%{id == null ? 'add' : 'edit'}">
			<s:hidden name="id"></s:hidden>
			<div class="ItemBlock_Title1">
				<!-- 資訊說明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					使用者資訊
				</div>
			</div>
			<!-- 表單內容顯示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="100">所屬部門</td>
							<td><s:select name="departmentId" cssClass="SelectStyle"
									list="#departmentList" listKey="id" listValue="name"
									headerKey="" headerValue="==請選擇部門==" /></td>
						</tr>
						<tr>
							<td>登錄名</td>
							<td><s:textfield name="loginName" cssClass="InputStyle" />
								* （登錄名要唯一）</td>
						</tr>
						<tr>
							<td>姓名</td>
							<td><s:textfield name="name" cssClass="InputStyle" /> *</td>
						</tr>
						<tr>
							<td>性別</td>
							<td>
							<%--
							<s:radio name="gender" list="%{ #{'男':'男', '女':'女'} }"></s:radio>
							<s:radio name="gender" list="#{'男':'男', '女':'女'}"></s:radio>
                        	 --%> <s:radio name="gender" list="{'男', '女'}"></s:radio>
							</td>
						</tr>
						<tr>
							<td>聯繫電話</td>
							<td><s:textfield name="phoneNumber" cssClass="InputStyle" /></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><s:textfield name="email" cssClass="InputStyle" /></td>
						</tr>
						<tr>
							<td>備註</td>
							<td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="ItemBlock_Title1">
				<!-- 資訊說明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					崗位設置
				</div>
			</div>
			<!-- 表單內容顯示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="100">崗位</td>
							<td><s:select name="roleIds" cssClass="SelectStyle"
									multiple="true" size="10" list="#roleList" listKey="id"
									listValue="name" /> 按住Ctrl鍵可以多選或取消選擇</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 表單操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>
	<div class="Description">
		說明：<br /> 1，用戶的登錄名要唯一，在填寫時要同時檢測是否可用。<br /> 2，新建使用者後，密碼被初始化為"1234"。<br />
		3，密碼在資料庫中存儲的是MD5摘要（不是存儲純文字密碼）。<br /> 4，使用者登錄系統後可以使用“個人設置→修改密碼”功能修改密碼。<br />
		5，新建用戶後，會自動指定默認的頭像。使用者可以使用“個人設置→個人資訊”功能修改自已的頭像<br />
		6，修改使用者資訊時，登錄名不可修改。
	</div>
</body>
</html>