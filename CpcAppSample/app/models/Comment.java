package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Comment extends Model {


	@Required
	public String author;

	@Required
	public Date postedAt;

	@Required
	@Lob
	@MaxSize(1000)
	public String content;

	@Required
	@ManyToOne
	public Post post;

	/**
	 * @param author
	 * @param content
	 * @param post
	 */
	public Comment(Post post, String author, String content) {
		this.author = author;
		this.content = content;
		this.post = post;
		postedAt = new Date();
	}

}
