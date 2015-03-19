package cn.itcast.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Topic extends Article {

	// 普通帖
	public static final int TYPE_NORMAL = 0;
	// 精華帖
	public static final int TYPE_BEST = 1;
	// 至頂帖
	public static final int TYPE_TOP = 2;

	private Forum forum; // 所屬的板塊
	private Set<Reply> replys = new HashSet<Reply>();
	private int type; // 類型
	private int replyCount; // 回覆數量
	private Reply lastReply; // 最後回覆
	private Date lastUpdateTime; // 最後更新時間(發表新主題時間或最後回覆時間)

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Set<Reply> getReplys() {
		return replys;
	}

	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public Reply getLastReply() {
		return lastReply;
	}

	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}