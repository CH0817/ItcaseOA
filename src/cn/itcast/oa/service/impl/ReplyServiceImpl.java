package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;

@Service
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
		        "FROM Reply WHERE topic = ? ORDER BY postTime ASC")//
		        .setParameter(0, topic)//
		        .list();
	}

	@Override
	public void save(Reply reply) {
		// 1.保存
		getSession().save(reply);
		// 2.維護相關信息
		Topic topic = reply.getTopic();
		topic.setReplyCount(topic.getReplyCount() + 1); // 回覆數量
		topic.setLastReply(reply); // 最後發表回覆
		topic.setLastUpdateTime(reply.getPostTime()); // 最後更新時間(主題的發表時間或最後回覆時間)

		Forum forum = topic.getForum();
		forum.setArticleCount(forum.getArticleCount() + 1); // 文章數量(主題數+回覆數)

		getSession().update(topic);
		getSession().update(forum);
	}

}
