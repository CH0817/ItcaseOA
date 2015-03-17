package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;

	private Long departmentId;
	private Long[] roleIds;

	/** 列表 */
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}

	/** 刪除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加頁面 */
	public String addUI() throws Exception {
		// 準備部門
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 準備崗位
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 封裝資料
		// 設置所屬部門
		model.setDepartment(departmentService.getById(departmentId));
		// 設置關連崗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		// 設置默認密碼1234(使用MD5)
		model.setPassword(DigestUtils.md5Hex("1234"));
		// 保存資料
		userService.save(model);
		return "toList";
	}

	/** 修改頁面 */
	public String editUI() throws Exception {
		// 準備部門
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 準備崗位
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		// 準備回顯的資料
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1.從資料庫中取出原對象
		User user = userService.getById(model.getId());
		// 2.設置要修改的屬性
		// user.setLoginName(model.getLoginName());
		// user.setName(model.getName());
		// user.setGender(model.getGender());
		// user.setPhoneNumber(model.getPhoneNumber());
		// user.setEmail(model.getEmail());
		// user.setDescription(model.getDescription());
		BeanUtils.copyProperties(model, user, new String[] { "id", "department", "roles" });
		// 設置所屬部門
		user.setDepartment(departmentService.getById(departmentId));
		// 設置關連崗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		// 3.更新到資料庫
		userService.update(user);
		return "toList";
	}

	/** 初始化密碼為1234 */
	public String initPassword() {
		// 1.從資料庫中取出原對象
		User user = userService.getById(model.getId());
		// 2.設置要修改的屬性(使用MD5)
		user.setPassword(DigestUtils.md5Hex("1234"));
		// 3.更新到資料庫
		userService.update(user);
		return "toList";
	}

	/** 登入頁面 */
	public String loginUI() {
		return "loginUI";
	}

	/** 登入 */
	public String login() {
		User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPassword());
		if (user == null) {
			addFieldError("login", "用戶名或密碼錯誤");
			return "loginUI";
		} else {
			// 登入用戶
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
	}

	/** 註銷 */
	public String logout() {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
