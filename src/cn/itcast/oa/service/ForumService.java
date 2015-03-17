package cn.itcast.oa.service;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {

	/**
	 * 上移，最上面的不能再上移
	 * 
	 * @author Rex
	 *
	 * @param id
	 */
	void moveUp(Long id);

	/**
	 * 下移，最下面的不能再下移
	 * 
	 * @author Rex
	 *
	 * @param id
	 */
	void moveDown(Long id);

}
