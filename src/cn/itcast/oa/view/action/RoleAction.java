package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 1L;

	private Long[] privilegeIds;

	/** 列表 */
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** 刪除 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** 添加頁面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// // 封裝到對象中
		// Role role = new Role();
		// role.setName(model.getName());
		// role.setDescription(model.getDescription());
		//
		// // 保存到資料庫
		// roleService.save(role);

		roleService.save(model);

		return "toList";
	}

	/** 修改頁面 */
	public String editUI() throws Exception {
		// 準備回顯的數據
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，從資料庫中獲取原物件
		Role role = roleService.getById(model.getId());

		// 2，設置要修改的屬性
		role.setName(model.getName());
		role.setDescription(model.getDescription());

		// 3，更新到資料庫
		roleService.update(role);

		return "toList";
	}

	/** 設置權限頁面 */
	public String setPrivilegeUI() throws Exception {
		// 準備回顯的數據
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege privilege : role.getPrivileges()) {
				privilegeIds[index++] = privilege.getId();
			}
		}
		
		//準備數據privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);

		return "setPrivilegeUI";
	}

	/** 設置權限 */
	public String setPrivilege() throws Exception {
		// 1，從資料庫中獲取原物件
		Role role = roleService.getById(model.getId());

		// 2，設置要修改的屬性
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		// 3，更新到資料庫
		roleService.update(role);

		return "toList";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}