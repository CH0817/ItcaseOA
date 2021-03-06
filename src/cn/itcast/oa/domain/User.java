package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * 用戶
 * 
 * @author tyg
 * 
 */
public class User implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	private Long id;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();

	private String loginName; // 登錄名
	private String password; // 密碼
	private String name; // 真實姓名
	private String gender; // 性別
	private String phoneNumber; // 電話號碼
	private String email; // 電子郵件
	private String description; // 說明

	/**
	 * 判斷本用戶是否有指定名稱的權限
	 * 
	 * @author Rex
	 *
	 * @param name
	 * @return
	 */
	public boolean hasPrivilegeByName(String name) {
		// 超級管理員有所有權限
		if (isAdmin()) {
			return true;
		}
		// 普通用戶要判斷是否有權限
		for (Role role : roles) {
			for (Privilege priv : role.getPrivileges()) {
				if (StringUtils.equals(name, priv.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判斷本用戶是否有指定Url的權限
	 * 
	 * @author Rex
	 *
	 * @param privUrl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasPrivilegeByUrl(String privUrl) {
		// 超級管理員有所有權限
		if (isAdmin()) {
			return true;
		}

		// 去掉後面的參數
		int pos = privUrl.indexOf("?");
		if (pos > -1) {
			privUrl = privUrl.substring(0, pos);
		}
		// 去掉UI後綴
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}

		// 如果本URL不需要控制，則用戶登入就可以使用
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		} else {
			// 普通用戶要判斷是否有權限
			for (Role role : roles) {
				for (Privilege priv : role.getPrivileges()) {
					if (StringUtils.equals(privUrl, priv.getUrl())) {
						return true;
					}
				}
			}

		}
		return false;
	}

	/**
	 * 判斷本用戶是否為超級管理員
	 * 
	 * @author Rex
	 *
	 * @return
	 */
	private boolean isAdmin() {
		return StringUtils.equals("admin", loginName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
