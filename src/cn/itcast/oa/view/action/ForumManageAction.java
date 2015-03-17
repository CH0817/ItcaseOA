package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum> {

	private static final long serialVersionUID = 1L;

	/** 列表 */
	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/** 刪除 */
	public String delete() throws Exception {
		forumService.delete(model.getId());
		return "toList";
	}

	/** 添加頁面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		forumService.save(model);
		return "toList";
	}

	/** 修改頁面 */
	public String editUI() throws Exception {
		// 準備回顯的數據
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1.從資料庫中取得原對象
		Forum forum = forumService.getById(model.getId());
		// 2.設置要修改的屬性
		BeanUtils.copyProperties(model, forum, new String[] { "id" });
		// 3.更新到資料庫
		forumService.update(forum);
		return "toList";
	}

	/** 上移 */
	public String moveUp() throws Exception {
		forumService.moveUp(model.getId());
		return "toList";
	}

	/** 下移 */
	public String moveDown() throws Exception {
		forumService.moveDown(model.getId());
		return "toList";
	}

}
