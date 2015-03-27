<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>查看主題：${topic.title}</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
<script language="javascript" src="${pageContext.request.contextPath}/script/fckeditor/fckeditor.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		var fck = new FCKeditor("content");
		fck.Width = "90%";
		fck.ToolbarSet = "bbs";
		fck.BasePath = "${pageContext.request.contextPath}/script/fckeditor/";
		fck.ReplaceTextarea();
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
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif" /> 查看主題
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--內容顯示-->
	<div id="MainArea">
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1" style="width: 98%">
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forum_list">論壇</s:a>
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forum_show?id=%{#topic.forum.id}">${topic.forum.name}</s:a>
				<font class="MenuPoint"> &gt;&gt; </font> 帖子閱讀
				<span style="margin-left: 30px;">
					<s:a action="topic_addUI?forumId=%{#topic.forum.id}">
						<img align="absmiddle" src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png" />
					</s:a>
				</span>
			</div>

			<div class="ForumPageTableBorder dataContainer" datakey="replyList">
				<!--顯示主題標題等-->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr valign="bottom">
						<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
						<td class="ForumPageTableTitle"><b>本帖主題：${topic.title}</b></td>
						<td class="ForumPageTableTitle" align="right" style="padding-right: 12px;">
							<s:a cssClass="detail" action="reply_addUI?topicId=%{#topic.id}">
								<img border="0" src="${pageContext.request.contextPath}/style/images/reply.gif" />回復
							</s:a>
							<a href="moveUI.html">
								<img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />移動到其他版塊
							</a>
							<a href="#" onClick="return confirm('要把本主題設為精華嗎？')">
								<img border="0" src="${pageContext.request.contextPath}/style/images/topicType_1.gif" />精華
							</a>
							<a href="#" onClick="return confirm('要把本主題設為置頂嗎？')">
								<img border="0" src="${pageContext.request.contextPath}/style/images/topicType_2.gif" />置頂
							</a>
							<a href="#" onClick="return confirm('要把本主題設為普通嗎？')">
								<img border="0" src="${pageContext.request.contextPath}/style/images/topicType_0.gif" />普通
							</a>
						</td>
						<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="4"></td>
					</tr>
				</table>

				<!-- ~~~~~~~~~~~~~~~ 顯示主帖（主帖只在第1頁顯示） ~~~~~~~~~~~~~~~ -->
				<div class="ListArea">
					<table border="0" cellpadding="0" cellspacing="1" width="100%">
						<tr>
							<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
								<!--作者頭像-->
								<div class="AuthorPhoto">
									<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
								</div> <!--作者名稱-->
								<div class="AuthorName">${topic.author.name}</div>
							</td>
							<td align="center">
								<ul class="TopicFunc">
									<!--操作列表-->
									<li class="TopicFuncLi">
										<a class="detail" href="${pageContext.request.contextPath}/BBS_Topic/saveUI.html">
											<img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />編輯
										</a>
										<a class="detail" href="#" onClick="return confirm('確定要刪除本帖嗎？')">
											<img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif" />刪除
										</a>
									</li>
									<!-- 文章的標題 -->
									<li class="TopicSubject">${topic.title}</li>
								</ul>
							</td>
						</tr>
						<tr>
							<!-- 文章內容 -->
							<td valign="top" align="center">
								<div class="Content">${topic.content}</div>
							</td>
						</tr>
						<tr>
							<!--顯示樓層等資訊-->
							<td class="Footer" height="28" align="center" valign="bottom">
								<ul style="margin: 0px; width: 98%;">
									<li style="float: left; line-height: 18px;">
										<font color=#C30000>[樓主]</font> ${topic.postTime}
									</li>
									<li style="float: right;">
										<a href="javascript:scroll(0,0)">
											<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" />
										</a>
									</li>
								</ul>
							</td>
						</tr>
					</table>
				</div>
				<!-- ~~~~~~~~~~~~~~~ 顯示主帖結束 ~~~~~~~~~~~~~~~ -->


				<!-- ~~~~~~~~~~~~~~~ 顯示回復清單 ~~~~~~~~~~~~~~~ -->
				<s:iterator value="#replyList" status="status">
					<div class="ListArea template">
						<table border="0" cellpadding="0" cellspacing="1" width="100%">
							<tr>
								<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
									<!--作者頭像-->
									<div class="AuthorPhoto">
										<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
									</div> <!--作者名稱-->
									<div class="AuthorName">${author.name}</div>
								</td>
								<td align="center">
									<ul class="TopicFunc">
										<!--操作列表-->
										<li class="TopicFuncLi">
											<a class="detail" href="${pageContext.request.contextPath}/BBS_Topic/saveUI.html">
												<img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />編輯
											</a>
											<a class="detail" href="#" onClick="return confirm('確定要刪除本帖嗎？')">
												<img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif" />刪除
											</a>
										</li>
										<!-- 文章表情與標題 -->
										<li class="TopicSubject">${title}</li>
									</ul>
								</td>
							</tr>
							<tr>
								<!-- 文章內容 -->
								<td valign="top" align="center">
									<div class="Content">${content}</div>
								</td>
							</tr>
							<tr>
								<!--顯示樓層等資訊-->
								<td class="Footer" height="28" align="center" valign="bottom">
									<ul style="margin: 0px; width: 98%;">
										<li style="float: left; line-height: 18px;">
											<font color=#C30000>[${status.count}樓]</font> ${postTime}
										</li>
										<li style="float: right;">
											<a href="javascript:scroll(0,0)">
												<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" />
											</a>
										</li>
									</ul>
								</td>
							</tr>
						</table>
					</div>
				</s:iterator>
				<!-- ~~~~~~~~~~~~~~~ 顯示回復清單結束 ~~~~~~~~~~~~~~~ -->
			</div>

			<!--分頁資訊-->
			<div id=PageSelectorBar>
				<div id=PageSelectorMemo>頁次：7/13頁 &nbsp; 每頁顯示：30條 &nbsp; 總記錄數：385條</div>
				<div id=PageSelectorSelectorArea>
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
					<a href="#" title="尾頁" style="cursor: hand;">
						<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png" />
					</a>
					轉到： <input onFocus="this.select();" maxlength="3" class="inputStyle" type="text" value="1" id="pn" />
					<input type="submit" value="Go" class="MiddleButtonStyle" />
				</div>
			</div>
			<div class="ForumPageTableBorder" style="margin-top: 25px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr valign="bottom">
						<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
						<td class="ForumPageTableTitle"><b>快速回復</b></td>
						<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="3"></td>
					</tr>
				</table>
			</div>
		</center>
		<!--快速回復-->
		<div class="QuictReply">
			<form action="">
				<div style="padding-left: 3px;">
					<table border="0" cellspacing="1" width="98%" cellpadding="5" class="TableStyle">
						<tr height="30" class="Tint">
							<td width="50px" class="Deep"><b>標題</b></td>
							<td class="no_color_bg"><input type="text" name="title" class="InputStyle" value="回復：昨天發現在表單裡刪除的圖片" style="width: 90%" /></td>
						</tr>
						<tr class="Tint" height="200">
							<td valign="top" rowspan="2" class="Deep"><b>內容</b></td>
							<td valign="top" class="no_color_bg"><textarea name="content" style="width: 95%; height: 300px"></textarea></td>
						</tr>
						<tr height="30" class="Tint">
							<td class="no_color_bg" colspan="2" align="center"><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right: 15px;" /></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
	<div class="Description">
		說明：<br />
		1，主帖只在第一頁顯示。<br />
		2，只有是管理員才可以進行“移動”、“編輯”、“刪除”、“精華”、“置頂”的操作。<br />
		3，刪除主帖，就會刪除所有的跟帖（回復）。<br />
	</div>
</body>
</html>