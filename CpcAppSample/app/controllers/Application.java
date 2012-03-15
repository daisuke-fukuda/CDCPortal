package controllers;

import java.util.List;

import models.Participant;
import models.Post;
import models.User;
import play.Play;
import play.cache.Cache;
import play.data.validation.Min;
import play.data.validation.Required;
import play.libs.Codec;
import play.libs.Images;
import play.mvc.Before;
import play.mvc.Controller;

public class Application extends Controller {




	@Before
	static void addDefaults() {
		renderArgs.put("blogTitle",Play.configuration.getProperty("blog.title"));
		renderArgs.put("blogBaseline",Play.configuration.getProperty("blog.baseline"));
		renderArgs.put("displayName","");
	}


	@Before
	static void setConnectUser() {

		if(Security.isConnected()) {
			User user = User.find("byEmail",Security.connected()).first();
			renderArgs.put("user", user.nickname);
			renderArgs.put("userId", user.id);
		}

	}
    public static void index() {

    	Post frontPost = Post.find("order by holdAt desc").first();
    	List<Post> olderPosts = Post.find("order by holdAt desc").from(1).fetch(10);
    	List<Post> allPosts = Post.find("order by holdAt desc").fetch();


		renderArgs.put("displayName","index");



    	render(frontPost, olderPosts, allPosts);
    }



    public static void show(Long id) {

    	List<Post> allPosts = Post.find("order by holdAt desc").fetch();
    	Post post = Post.findById(id);


    	Boolean isParticipate = false;
    	if(Security.isConnected() ){

    		User loginUser = User.find("byEmail", Security.connected()).first();
    		Participant participant = Participant.find("byPostAndParticipant" ,post, loginUser).first();

    		if(participant != null){
    			isParticipate = true;
    		}

    	}

		renderArgs.put("displayName","show");

    	render(post, allPosts, isParticipate);

    }

    public static void join(Long id, String comment) {

    	Post post = Post.findById(id);
    	User user = User.find("byEmail", Security.connected()).first();
    	if(request.params.get("cancel") != null){
    		Participant participant = Participant.find("byPostAndParticipant", post, user).first();
    		participant.delete();

    	} else {
    		Participant participant = new Participant(post, user, comment);
        	participant.save();


    	}
    	show(id);



    }

//    public static void show() {
//    	render();
//
//	}

	public static void postComment(
			Long postId,
			@Required String author,
			@Required String content,
			@Required String code,
			String randomID) {
		Post post = Post.findById(postId);

		if(!Play.id.equals("test")) {
			validation.equals(code,Cache.get(randomID)).message("文字列が間違っています");
		}

		if (validation.hasErrors()) {
			render("Application/show.html", post,randomID);
		}

		post.addComment(author, content);
		flash.success("Thanks post %s", author);

		show(postId);

	}





}