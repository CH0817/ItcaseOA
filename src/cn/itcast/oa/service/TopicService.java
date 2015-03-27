package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	/**
	 * 查詢指定板塊中的所有主題，排序：所有置頂帖在最上面，並按最後更新的時間排序，讓新狀態的在上面。
	 * 
	 * @author Rex
	 *
	 * @param forum
	 * @return
	 */
	List<Topic> findByForum(Forum forum);

}
