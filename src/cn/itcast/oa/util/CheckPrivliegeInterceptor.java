package cn.itcast.oa.util;

import org.apache.commons.lang.xwork.StringUtils;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivliegeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// System.out.println("攔截之前");
		// String result = invocation.invoke();// 放行
		// System.out.println("攔截之後");
		// return result;
		// 獲取信息
		User user = (User) ActionContext.getContext().getSession().get("user");// 當前登入用戶
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privUrl = namespace + actionName;// 對應權限的Url
		// 如果未登入，轉到登入頁面
		if (user == null) {
			// 如果是去登入就放行
			if (StringUtils.equals(privUrl, "/user_login")) {// "/user_userUI"、"/user_login"
				return invocation.invoke();
			}// 如果不是救主到登入頁面
			else {
				return "loginUI";
			}
		}
		// 如果已登入，判斷權限
		else {
			// 如果有權限就放行
			if (user.hasPrivilegeByUrl(privUrl)) {
				return invocation.invoke();
			}
			// 如果沒權限就轉到提示頁面
			else {
				return "noPrivilegeError";
			}
		}
	}
}
