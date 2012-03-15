package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import controllers.CRUD.Hidden;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.templates.JavaExtensions;

@Entity
public class Post extends Model{

	//タイトル
	@Required
	public String title;

	//開催日時
	@Required
	public Date holdAt;

	@Required
	public Date postedAt;

	@Required
	public String place;

	@Required
	public String theme;


	@Required
	@ManyToOne
	public User lecturer;


	@Required
	@Lob
	@MaxSize(10000)
	public String content;

	@Required
	@ManyToOne
	public User author;

//	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
//	public List<Comment> comments;

	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	public List<Participant> participants;



//	@ManyToMany(cascade=CascadeType.PERSIST)
//	public Set<Tag> tags;


	/**
	 * @param title
	 * @param postedAt
	 * @param content
	 */
	public Post(User authror ,String title, String content) {
//		this.comments = new ArrayList<Comment>();
//		this.tags = new TreeSet<Tag>();

		this.holdAt = new Date();

		this.author = authror;
		this.title = title;
		this.postedAt =  new Date();
		this.content = content;

		participants = new ArrayList();
	}



	public Post addComment(String author,String content){
		Comment newComment= new Comment(this, author, content).save();
//		this.content.add(newComment);
		this.save();
		return this;


	}


	public Post	previous() {
		return Post.find("postedAt < ? order by holdAt desc",postedAt).first();
	}


	public Post next() {
	    return Post.find("postedAt > ? order by holdAt asc", postedAt).first();
	}


	public Post tagIDWith(String name) {

//		tags.add(Tag.findOrCreateByName(name));
		return this;

	}

	public static List<Post> findTaggedWith(String tag) {
		return Post.find("select distinct p from Post p join p.tags as t where t.name=?", tag).fetch();


	}


	@Override
	public String toString() {
		return(JavaExtensions.format(holdAt) + " " + title);
	}



}
