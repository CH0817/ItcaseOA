package cn.itcast.oa.view.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {

	private static final long serialVersionUID = 1L;

	private Long forumId;

	/** 顯示單個主題(主題+回帖列表) */
	public String show() throws Exception {
		// 準備數據：topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		// 回覆列表：replyList
		List<Reply> replyList = replyService.findByTopic(topic);
		ActionContext.getContext().put("replyList", replyList);
		return "show";
	}

	/** 發表新主題頁面 */
	public String addUI() throws Exception {
		// 準備數據
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	/** 發表新主題 */
	public String add() throws Exception {
		// 封裝
		// >> 表單參數，已經封裝了title、content
		model.setForum(forumService.getById(forumId));
		// >> 當前直接獲取的信息
		model.setAuthor(getCurrentUser()); // 當前登入用戶
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr()); // 當前請求中的IP
		model.setPostTime(new Date()); // 當前時間

		// 保存
		topicService.save(model);
		return "toShow"; // 轉到新主題的顯示頁面
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

}
