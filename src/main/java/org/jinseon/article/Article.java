package org.jinseon.article;

public class Article {
	
	String articleId;
	String title;
	String content;
	String userId;
	String name;
	String cdate;
	String udate;

	
	
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}

	/**
	 * \n를 <br/> 로 바꾼다. 글을 볼때 띄워쓰기를 할수있다
	 */
	public String getContentHtml() {
		if (content != null)
			return content.replace("\n", "<br/>");
		return null;
}
	
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", name=" + name + ", cdate=" + cdate + ", udate=" + udate + "]\n";
	}
	
}