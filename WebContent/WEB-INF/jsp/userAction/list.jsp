<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>用戶列表</title>
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
				用戶管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">
			<!-- 表頭-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="100">登錄名</td>
					<td width="100">姓名</td>
					<td width="100">所屬部門</td>
					<td width="200">崗位</td>
					<td>備註</td>
					<td>相關操作</td>
				</tr>
			</thead>
			<!--顯示資料清單-->
			<tbody id="TableData" class="dataContainer" datakey="userList">
				<s:iterator value="#userList">
					<tr class="TableDetail1 template">
						<td>${loginName}&nbsp;</td>
						<td>${name}&nbsp;</td>
						<td>${department.name}&nbsp;</td>
						<td><s:iterator value="roles">
                		${name}<br>
							</s:iterator></td>
						<td>${description}&nbsp;</td>
						<td><s:a action="user_delete?id=%{id}"
								onclick="return delConfirm()">刪除</s:a> <s:a
								action="user_editUI?id=%{id}">修改</s:a> <s:a
								action="user_initPassword?id=%{id}"
								onclick="return window.confirm('您確定要初始化密碼為1234嗎？')">初始化密碼</s:a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<!-- 其他功能超連結 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="user_addUI">
					<img
						src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
			</div>
		</div>
	</div>
</body>
</html>