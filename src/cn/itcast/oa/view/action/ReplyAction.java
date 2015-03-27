package cn.itcast.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {

	private static final long serialVersionUID = 1L;

	private Long topicId;

	/** 發表新回覆頁面 */
	public String addUI() throws Exception {
		// 準備數據：topic
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	/** 發表新回覆 */
	public String add() throws Exception {
		// 封裝
		// >>表單字段，已經封裝了title、content
		model.setTopic(topicService.getById(topicId));
		// >>當前信息
		model.setAuthor(getCurrentUser()); // 當前用戶
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date()); // 當前時間
		// 保存
		replyService.save(model);
		return "toTopicShow"; // 轉到新回覆所在主題的顯示頁面
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
