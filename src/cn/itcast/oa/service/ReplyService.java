package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

public interface ReplyService extends DaoSupport<Reply> {

	/**
	 * 查詢指定主題中的所有列表，排序：按發表時間升序。
	 * 
	 * @author Rex
	 *
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);

}
