package cn.itcast.oa.util;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.User;

@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 執行安裝
	 * 
	 * @author Rex
	 *
	 */
	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();
		// 保存超級管理員用戶
		User user = new User();
		user.setLoginName("admin");
		user.setName("超級管理員");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user);
		// 保存權限數據
		Privilege menu, menu1, menu2, menu3, menu4, menu5;
		menu = new Privilege(null, "系統管理", null);
		menu1 = new Privilege(menu, "崗位管理", "/role_list");
		menu2 = new Privilege(menu, "部門管理", "/department_list");
		menu3 = new Privilege(menu, "用戶管理", "/user_list");
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		session.save(new Privilege(menu1, "崗位列表", "/role_list"));
		session.save(new Privilege(menu1, "崗位刪除", "/role_delete"));
		session.save(new Privilege(menu1, "崗位添加", "/role_add"));
		session.save(new Privilege(menu1, "崗位修改", "/role_edit"));

		session.save(new Privilege(menu2, "部門列表", "/deparment_list"));
		session.save(new Privilege(menu2, "部門刪除", "/deparment_delete"));
		session.save(new Privilege(menu2, "部門添加", "/deparment_add"));
		session.save(new Privilege(menu2, "部門修改", "/deparment_edit"));

		session.save(new Privilege(menu3, "用戶列表", "/user_list"));
		session.save(new Privilege(menu3, "用戶刪除", "/user_delete"));
		session.save(new Privilege(menu3, "用戶添加", "/user_add"));
		session.save(new Privilege(menu3, "用戶修改", "/user_edit"));
		session.save(new Privilege(menu3, "初始化密碼", "/user_edit"));

		// 將來要增加的
		menu = new Privilege(null, "網上交流", null);
		menu1 = new Privilege(menu, "論壇管理", "/forumManage_list");
		menu2 = new Privilege(menu, "論壇", "/forum_list");
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		// 將來要增加的
		menu = new Privilege(null, "審批流轉", null);
		menu1 = new Privilege(menu, "審批流程管理", "/processDefinition_list");
		menu2 = new Privilege(menu, "申請單模版管理", "/template_list");
		menu3 = new Privilege(menu, "起草申請", "/flow_templateList");
		menu4 = new Privilege(menu, "待我批審", "/flow_myTaskList");
		menu5 = new Privilege(menu, "我的申請查詢", "/flow_myApplicationList");
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
	}

}
