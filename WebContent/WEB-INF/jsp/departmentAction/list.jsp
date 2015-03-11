<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>部門列表</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--頁面標題-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				部門管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">
			<!-- 表頭-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="150px">部門名稱</td>
					<td width="150px">上級部門名稱</td>
					<td width="200px">職能說明</td>
					<td>相關操作</td>
				</tr>
			</thead>
			<!--顯示資料清單-->
			<tbody id="TableData" class="dataContainer" datakey="departmentList">
				<s:iterator value="#departmentList">
					<tr class="TableDetail1 template">
						<td><s:a action="department_list?parentId=%{id}">${name}&nbsp;</s:a></td>
						<td>${parent.name}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td><s:a
								action="department_delete?id=%{id}&parentId=%{parent.id}"
								onclick="return window.confirm('這將刪除所有的下級部門，您確定要刪除嗎？')">刪除</s:a>
							<s:a action="department_editUI?id=%{id}">修改</s:a>&nbsp;</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<!-- 其他功能超連結 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<s:a action="department_addUI?parentId=%{parentId}">
					<img
						src="${pageContext.request.contextPath}/style/images/createNew.png" />
				</s:a>
				<s:a action="department_list?parentId=%{#parent.parent.id}">
					<img
						src="${pageContext.request.contextPath}/style/blue/images/button/ReturnToPrevLevel.png" />
				</s:a>
			</div>
		</div>
	</div>
	<!--說明-->
	<div id="Description">
		說明：<br /> 1，清單頁面只顯示一層的（同級的）部門資料，預設顯示最頂級的部門列表。<br />
		2，點擊部門名稱，可以查看此部門相應的下級部門列表。<br /> 3，刪除部門時，同時刪除此部門的所有下級部門。
	</div>
</body>
</html>