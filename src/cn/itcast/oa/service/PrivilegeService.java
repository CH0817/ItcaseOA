package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {

	/**
	 * 查詢所有頂級的權限
	 * 
	 * @author Rex
	 *
	 * @return
	 */
	List<Privilege> findTopList();

}
