<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>崗位列表</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
</head>
<body>
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--頁面標題-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				崗位管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">
			<!-- 表頭-->
			<thead>
				<tr align="CENTER" valign="MIDDLE" id="TableTitle">
					<td width="200px">崗位名稱</td>
					<td width="300px">崗位說明</td>
					<td>相關操作</td>
				</tr>
			</thead>
			<!--顯示資料清單-->
			<tbody id="TableData" class="dataContainer" datakey="roleList">
				<s:iterator value="#roleList">
					<tr class="TableDetail1 template">
						<td>${name}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td><s:a action="role_delete?id=%{id}"
								onclick="return confirm('確定要刪除嗎？')">刪除</s:a> <s:a
								action="role_editUI?id=%{id}">修改</s:a> <s:a
								action="role_setPrivilegeUI?id=%{id}">設置許可權</s:a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<!-- 其他功能超連結 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="role_addUI">
					<img
						src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
			</div>
		</div>
	</div>
</body>
</html>