package models;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Participant extends Model{

	@Required
	@ManyToOne
	public User participant;

	@Required
	public Date postedAt;

	@Required
	@Lob
	@MaxSize(1000)
	public String comment;

	@Required
	@ManyToOne
	public Post post;

	/**
	 * @param author
	 * @param content
	 * @param post
	 */
	public Participant(Post post, User participant, String comment) {
		this.participant = participant;
		this.comment = comment;
		this.post = post;
		postedAt = new Date();
	}





}
