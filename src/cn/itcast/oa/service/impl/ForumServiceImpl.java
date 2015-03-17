package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumService;

@Service
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> findAll() {
		return getSession().createQuery(//
		        "FROM Forum ORDER BY position")//
		        .list();
	}

	@Override
	public void save(Forum forum) {
		// 保存
		super.save(forum);
		// 設置position的值
		forum.setPosition(forum.getId().intValue());
		update(forum);
	}

	@Override
	public void moveUp(Long id) {
		// 找出相關的Forum
		Forum forum = getById(id);// 當前要移動的Forum
		Forum other = (Forum) getSession().createQuery(// 當前要移動的Forum的上面的那個Forum
		        "FROM Forum WHERE position < ? ORDER BY position DESC")//
		        .setFirstResult(0)//
		        .setMaxResults(1)//
		        .setParameter(0, forum.getPosition())//
		        .uniqueResult();
		// 最上面不能上移
		if (other == null) {
			return;
		}
		// 交換position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新到資料庫中，可以不寫，因為對象現在正處於持久化狀態
		update(forum);
		update(other);
	}

	@Override
	public void moveDown(Long id) {
		// 找出相關的Forum
		Forum forum = getById(id);// 當前要移動的Forum
		Forum other = (Forum) getSession().createQuery(// 當前要移動的Forum的下面的那個Forum
		        "FROM Forum WHERE position > ? ORDER BY position")//
		        .setFirstResult(0)//
		        .setMaxResults(1)//
		        .setParameter(0, forum.getPosition())//
		        .uniqueResult();
		// 最下面不能下移
		if (other == null) {
			return;
		}
		// 交換position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新到資料庫中，可以不寫，因為對象現在正處於持久化狀態
		update(forum);
		update(other);
	}

}
