package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private static final long serialVersionUID = 1L;
	private Long parentId;

	/** 列表 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		if (parentId == null) {
			departmentList = departmentService.findTopList();
		} else {
			departmentList = departmentService.findChildren(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** 刪除 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 添加頁面 */
	public String addUI() throws Exception {
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		// 保存
		departmentService.save(model);

		return "toList";
	}

	/** 修改頁面 */
	public String editUI() throws Exception {
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 準備回顯的數據
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，從資料庫取出原物件
		Department department = departmentService.getById(model.getId());

		// 2，設置要修改的屬性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));

		// 3，更新到資料庫中
		departmentService.update(department);

		return "toList";
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}