import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {


	@Before
	public void setup(){
		Fixtures.deleteDatabase();

	}

	@Test
	public void createAndRetrieveUser(){


		//Create
//		new User("fukuda@cybeans.co.jp","pass","Fukuda").save();

		//Select
		User fukuda = User.find("byEmail","fukuda@cybeans.co.jp").first();


		//Test
		assertNotNull(fukuda);
		assertEquals("Fukuda",fukuda.fullname);


	}

	@Test
	public void tryConnectAsUser(){

		//Create
//		new User("fukuda@cybeans.co.jp","pass","Fukuda").save();

		//Test
		assertNotNull(User.connect("fukuda@cybeans.co.jp", "pass"));
		assertNull(User.connect("fukuda@cybeans.co.j", "pass"));
		assertNull(User.connect("fukuda@cybeans.co.jp", "ass"));

	}




	@Test
	public void createPost(){

		//Create
//		User fukuda = new User("fukuda@cybeans.co.jp","pass","Fukuda").save();
//		new Post(fukuda, "testTitle", "testContent").save();


		//Test
		assertEquals(1, Post.count());

//		List<Post> fukudaPosts = Post.find("byAuthor",fukuda).fetch();


		//Test
//		assertEquals(1, fukudaPosts.size());

//		Post firstPost = fukudaPosts.get(0);
//		assertNotNull(firstPost);
//
//		assertEquals(fukuda,firstPost.author);
//		assertEquals("testTitle",firstPost.title);
//		assertEquals("testContent",firstPost.content);
//		assertNotNull(firstPost.postedAt);

	}


	@Test
	public void postComment(){

//		//Create User
//		User fukuda = new User("fukuda@cybeans.co.jp","pass","Fukuda").save();
//
//		//Create Post
//		Post fukudaPost = new Post(fukuda, "testTiltle", "testContent").save();
//
//		//Create Comment
//		new Comment(fukudaPost, "Mr.AAA", "comment1").save();
//		new Comment(fukudaPost, "Mr.BBB", "comment2").save();
//
//
//		//Retreive all comments
//		List<Comment> fukudaPostComments = Comment.find("byPost",fukudaPost).fetch();
//
//
//		//Test
//		assertEquals(2, fukudaPostComments.size());
//
//		Comment firstComment = fukudaPostComments.get(0);
//		assertNotNull(firstComment);
//		assertEquals("Mr.AAA",firstComment.author);
//		assertEquals("comment1",firstComment.content);
//		assertNotNull(firstComment.postedAt);
//
//
//		Comment secondComment = fukudaPostComments.get(1);
//		assertNotNull(secondComment);
//		assertEquals("Mr.BBB",secondComment.author);
//		assertEquals("comment2",secondComment.content);
//		assertNotNull(secondComment.postedAt);

	}



	@Test
	public void userTheCommentRelation(){

		//Create User
//		User fukuda = new User("fukuda@cybeans.co.jp","pass","Fukuda").save();
//
//		//Create Post
//		Post fukudaPost = new Post(fukuda, "testTiltle", "testContent").save();
//
//		//Create Comment
//		fukudaPost.addComment("Mr.AAA", "comment1").save();
//		fukudaPost.addComment("Mr.BBB", "comment2").save();
//
//
//		//Count things
//		assertEquals(1, User.count());
//		assertEquals(1, Post.count());
//		assertEquals(2, Comment.count());
//
//		//Retreive fukuda comments
//		fukudaPost = Post.find("byAuthor",fukuda).first();
//		assertNotNull(fukudaPost);
//
//
//		// Navigate to comments
//		assertEquals(2,fukudaPost.comments.size());
//		assertEquals("Mr.AAA",fukudaPost.comments.get(0).author);
//
//
//		//Delete Post
//		fukudaPost.delete();
//
//		//Test
//		assertEquals(1, User.count());
//		assertEquals(0, Post.count());
//		assertEquals(0, Comment.count());
//

	}



	@Test
	public void fullTest(){
		Fixtures.loadModels("initial-data.yml");


		//Count this
		assertEquals(2, User.count());
		assertEquals(3, Post.count());
		assertEquals(3, Comment.count());


		//Try to Connect
		assertNotNull(User.connect("bob@gmail.com", "secret"));
		assertNotNull(User.connect("jeff@gmail.com", "secret"));
		assertNull(User.connect("aaaaabob@gmail.com", "secret"));
		assertNull(User.connect("jeff@gmail.com", "secretooooooooo"));



		//Find all of Bob's posts
		List<Post> bobPosts = Post.find("author.email", "bob@gmail.com").fetch();
		assertEquals(2,bobPosts.size());


		//Find all comments related to Bob's posts
		List<Comment> bobComments = Comment.find("post.author.email","bob@gmail.com").fetch();
		assertEquals(3,bobComments.size());


		//Find the most recent post
		Post frontPost = Post.find("order by postedAt desc").first();
		assertNotNull(frontPost);
		assertEquals("About the model layer",frontPost.title);


		//Check that this post has two comments
//		assertEquals(2, frontPost.comments.size());
		// Post a new comment
		frontPost.addComment("Jim","Hello");
//		assertEquals(3, frontPost.comments.size());
		assertEquals(4, Comment.count());


		//Check the tags
//		assertEquals(3, bobPosts.get(0).tags.size());
//		assertEquals(true, bobPosts.get(0).tags.add(Tag.findOrCreateByName("play")));


    	Post firstPost = Post.find("order by postedAt desc").first();



	}


	@Test
	public void testTags(){

		//Create User
//		User fukuda = new User("fukuda@cybeans.co.jp","pass","Fukuda").save();
//
//		//Create Post
//		Post fukudaPost = new Post(fukuda, "testTiltle", "testContent").save();
//		Post anotherFukudaPost = new Post(fukuda, "second", "testContent").save();
//
//
//		//Well
//		assertEquals(0,Post.findTaggedWith("RED").size());
//
//		//Tag it now
//		fukudaPost.tagIDWith("RED").tagIDWith("BLUE").save();
//		anotherFukudaPost.tagIDWith("RED").tagIDWith("GREEN").save();
//
//
//		//Test
//		assertEquals(2,Post.findTaggedWith("RED").size());
//		assertEquals(1,Post.findTaggedWith("BLUE").size());
//		assertEquals(1,Post.findTaggedWith("GREEN").size());
//
//
//		List<Map> cloud = Tag.getCloud();
//
//		assertEquals("[{tag=BLUE, pound=1}, {tag=GREEN, pound=1}, {tag=RED, pound=2}]"
//				,cloud.toString());
//

	}





}
