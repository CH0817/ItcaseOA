package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.TopicService;

@Service
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> findByForum(Forum forum) {
		// 排序：所有置頂帖在最上面，並按最後更新的時間排序，讓新狀態的在上面。
		return getSession().createQuery(//
		        "FROM Topic WHERE forum = ? ORDER BY (CASE type WHEN 2 THEN 2 ELSE 0 END) DESC, lastUpdateTime DESC")//
		        .setParameter(0, forum)//
		        .list();
	}

	@Override
	public void save(Topic topic) {
		// 1.設置屬性並保存
		topic.setType(Topic.TYPE_NORMAL); // 默認為普通帖
		topic.setReplyCount(0);
		topic.setLastReply(null);
		topic.setLastUpdateTime(topic.getPostTime());
		getSession().save(topic);// 保存

		// 2.維護相關的特殊屬性
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() + 1); // 主題數量
		forum.setArticleCount(forum.getArticleCount() + 1); // 文章數量(主題數+回覆數)
		forum.setLastTopic(topic); // 最後發表主題
		getSession().save(forum);
	}

}
