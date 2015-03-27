<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>【${forum.name}】中的主題清單</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
<script type="text/javascript">
	function onSortByChange(selectedValue) {
		if (selectedValue == 0) {
			$("select[name=asc]").attr("disabled", "disabled");
		} else {
			$("select[name=asc]").removeAttr("disabled");
		}
	}

	$(function() {
		if ($("select[name=orderBy]").val() == '0') {
			$("select[name=asc]").attr("disabled", "disabled");
		}
	});
</script>
</head>
<body>

	<!-- 標題顯示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--頁面標題-->
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" /> 【${forum.name}】中的主題清單
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1" style="width: 98%;">
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forum_list">論壇</s:a>
				<font class="MenuPoint"> &gt; </font> ${forum.name}
				<span style="margin-left: 30px;">
					<s:a action="topic_addUI?forumId=%{#forum.id}">
						<img align="absmiddle" src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png" />
					</s:a>
				</span>
			</div>

			<div class="ForumPageTableBorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<!--表頭-->
					<tr align="center" valign="middle">
						<td width="3" class="ForumPageTableTitleLeft">
							<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
						</td>
						<td width="50" class="ForumPageTableTitle">
							<!--狀態/圖示-->&nbsp;
						</td>
						<td class="ForumPageTableTitle">主題</td>
						<td width="130" class="ForumPageTableTitle">作者</td>
						<td width="100" class="ForumPageTableTitle">回復數</td>
						<td width="130" class="ForumPageTableTitle">最後回復</td>
						<td width="3" class="ForumPageTableTitleRight">
							<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
						</td>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="8"></td>
					</tr>
					<tr height=3>
						<td colspan=8></td>
					</tr>

					<!--主題清單-->
					<tbody class="dataContainer" datakey="topicList">

						<s:iterator value="#topicList">
							<tr height="35" id="d0" class="template">
								<td></td>
								<td class="ForumTopicPageDataLine" align="center">
									<img src="${pageContext.request.contextPath}/style/images/topicType_${type}.gif" />
								</td>
								<td class="Topic">
									<s:a cssClass="Default" action="topic_show?id=%{id}">${title}</s:a>
								</td>
								<td class="ForumTopicPageDataLine">
									<ul class="ForumPageTopicUl">
										<li class="Author">${author.name}</li>
										<li class="CreateTime">${postTime}</li>
									</ul>
								</td>
								<td class="ForumTopicPageDataLine Reply" align="center">
									<b>${replyCount}</b>
								</td>
								<td class="ForumTopicPageDataLine">
									<ul class="ForumPageTopicUl">
										<li class="Author">${lastReply.author.name}</li>
										<li class="CreateTime">${lastReply.postTime}</li>
									</ul>
								</td>
								<td></td>
							</tr>
						</s:iterator>

					</tbody>
					<!--主題清單結束-->

					<tr height="3">
						<td colspan="9"></td>
					</tr>

				</table>

				<!--其他操作-->
				<div id="TableTail">
					<div id="TableTail_inside">
						<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
							<tr valign=bottom>
								<td></td>
								<td>
									<select name="viewType">
										<option value="0">全部主題</option>
										<option value="1">全部精華貼</option>
										<!--
										<option value="2">當天的主題</option>
										<option value="3">本周的主題</option>
										<option value="4">本月的主題</option>
										-->
									</select> <select name="orderBy" onchange="onSortByChange(this.value)">
										<option value="0">默認排序(所有置頂帖在前面，並按最後更新時間降冪排列)</option>
										<option value="1">只按最後更新時間排序</option>
										<option value="2">只按主題發表時間排序</option>
										<option value="3">只按回復數量排序</option>
									</select> <select name="asc">
										<option value="false">降冪</option>
										<option value="true">昇冪</option>
									</select>
									<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE" />
								</td>
							</tr>
						</table>
					</div>
				</div>

			</div>
		</center>
	</div>

	<!--分頁資訊-->
	<div id=PageSelectorBar>
		<div id=PageSelectorMemo>頁次：7/13頁 &nbsp; 每頁顯示：30條 &nbsp; 總記錄數：385條</div>
		<div id=PageSelectorSelectorArea>
			<!--
		<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage2.png"/>
		-->
			<a href="javascript:void(0)" title="首頁" style="cursor: hand;">
				<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png" />
			</a>
			<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(2);">3</span>
			<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(2);">4</span>
			<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(2);">5</span>
			<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(2);">6</span>
			<span class="PageSelectorNum PageSelectorSelected">7</span>
			<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(2);">8</span>
			<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(2);">9</span>
			<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(2);">10</span>
			<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(2);">11</span>
			<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(2);">12</span>
			<!--
			<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage2.png"/>
			-->
			<a href="#" title="尾頁" style="cursor: hand;">
				<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png" />
			</a> 轉到：
			 <input onFocus="this.select();" maxlength="3" class="inputStyle" type="text" value="1" id="pn" />
			<input type="submit" value="Go" class="MiddleButtonStyle" />
		</div>
	</div>

	<div class="Description">
		說明：<br /> 1，主題預設按最後更新的時間降冪排列。最後更新時間是指主題最後回復的時間，如果沒有回復，就是主題發表的時間。<br /> 2，帖子有普通、置頂、精華之分。置頂貼始終顯示在最上面，精華貼用不同的圖示標示。<br />
	</div>

</body>
</html>