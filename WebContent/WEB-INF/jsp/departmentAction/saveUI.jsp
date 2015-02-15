<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>部門設置</title>
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
				部門資訊
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--顯示表單內容-->
	<div id=MainArea>
		<s:form action="department_%{id == null ? 'add' : 'edit'}">
			<s:hidden name="id"></s:hidden>
			<div class="ItemBlock_Title1">
				<!-- 資訊說明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部門資訊 </DIV>  -->
			</div>
			<!-- 表單內容顯示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="100">上級部門</td>
							<td><s:select name="parentId" cssClass="SelectStyle"
									list="#departmentList" listKey="id" listValue="name"
									headerKey="" headerValue="請選擇部門">
								</s:select></td>
						</tr>
						<tr>
							<td>部門名稱</td>
							<td><s:textfield name="name" cssClass="InputStyle" /> *</td>
						</tr>
						<tr>
							<td>職能說明</td>
							<td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
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
		說明：<br /> 1，上級部門的清單是有層次結構的（樹形）。<br />
		2，如果是修改：上級部門清單中不能顯示當前修改的部門及其子孫部門。因為不能選擇自已或自已的子部門作為上級部門。<br />
	</div>
</body>
</html>