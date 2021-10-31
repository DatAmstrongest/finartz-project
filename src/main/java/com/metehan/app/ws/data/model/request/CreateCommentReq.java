package com.metehan.app.ws.data.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateCommentReq {
	
	@NotNull(message="Content cannot be null")
	@Size(max=300, message="Cotent must not be more than 300 characters")
	private String content;
	
	@Min(0)
	@Max(5)
	@NotNull(message="Point cannot be null")
	private int point;
	
	private String commentId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	
	

}
