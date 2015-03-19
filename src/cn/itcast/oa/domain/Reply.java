package cn.itcast.oa.domain;

public class Reply extends Article {

	private Topic topic; // 所屬的主題

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
