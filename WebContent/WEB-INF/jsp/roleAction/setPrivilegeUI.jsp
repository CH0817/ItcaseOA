<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>配置許可權</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/file.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
</head>
<script type="text/javascript">
	$(function(){
		//bind click even
		$("[name=privilegeIds]").click(function(){
			//當選中或取消一個權限時，同時也選中或取消所有下級權限
			$(this).siblings("ul").find("input").attr("checked", this.checked);
			//當選中一個權限時，也要同時選中其上級權限，取消時不會連上級權限一起取消
			if(this.checked == true){
				$(this).parents("li").children("input").attr("checked", true);
			}
		});
	});
</script>
<body>
	<!-- 標題顯示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--頁面標題-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" /> 配置許可權
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--顯示表單內容-->
	<div id=MainArea>
		<s:form action="role_setPrivilege">
			<s:hidden name="id"></s:hidden>
			<div class="ItemBlock_Title1">
				<!-- 資訊說明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 正在為【${name}】配置許可權
				</div>
			</div>
			<!-- 表單內容顯示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<!--表頭-->
						<thead>
							<tr align="LEFT" valign="MIDDLE" id="TableTitle">
								<td width="300px" style="padding-left: 7px;">
									<!-- 如果把全選元素的id指定為selectAll，並且有函數selectAll()，就會有錯。因為有一種用法：可以直接用id引用元素 --> <input type="checkbox" id="cbSelectAll" onClick="$('[name=privilegeIds]').attr('checked', this.checked)" /> <label for="cbSelectAll">全選</label>
								</td>
							</tr>
						</thead>
						<!--顯示資料清單-->
						<tbody id="TableData">
							<tr class="TableDetail1">
								<td>
									<!-- 顯示許可權樹 --> <%-- <s:checkboxlist name="privilegeIds" list="#privilegeList" listKey="id" listValue="name"></s:checkboxlist> --%> <%-- <s:iterator value="#privilegeList">
										<input type="checkbox" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/>>
										<label for="cb_${id}">${name}</label>
										<br>
									</s:iterator> --%>
									<ul id="tree">
										<s:iterator value="#application.topPrivilegeList">
											<li>
												<input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/>> <label for="cb_${id}"><span class="folder">${name}</span></label>
												<ul>
													<s:iterator value="children">
														<li>
															<input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/>> <label for="cb_${id}"><span class="folder">${name}</span></label>
															<ul>
																<s:iterator value="children">
																	<li><input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/>> <label for="cb_${id}"><span class="folder">${name}</span></label></li>
																</s:iterator>
															</ul>
														</li>
													</s:iterator>
												</ul>
											</li>
										</s:iterator>
									</ul>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<script type="text/javascript">
				$('#tree').treeview();
			</script>
			<!-- 表單操作 -->
			<div id="InputDetailBar">
				<input type="image" src="${pageContext.request.contextPath}/style/images/save.png" /> <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>
	<div class="Description">
		說明：<br /> 1，選中一個許可權時：<br /> &nbsp;&nbsp;&nbsp;&nbsp; a，應該選中他的所有直系上級。<br /> &nbsp;&nbsp;&nbsp;&nbsp; b，應該選中他的所有直系下級。<br /> 2，取消選擇一個許可權時：<br /> &nbsp;&nbsp;&nbsp;&nbsp; a，應該取消選擇他的所有直系下級。<br /> &nbsp;&nbsp;&nbsp;&nbsp; b，如果同級的許可權都是未選擇狀態，就應該取消選中他的直接上級，並向上做這個操作。<br /> 3，全選/取消全選。<br /> 4，默認選中當前崗位已有的許可權。<br />
	</div>
</body>
</html>
