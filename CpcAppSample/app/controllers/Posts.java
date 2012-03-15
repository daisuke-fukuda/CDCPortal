package controllers;

import javax.persistence.Entity;

import models.User;

import play.mvc.Before;
import play.mvc.With;

import controllers.CRUD;

@Check("admin")
@With(Secure.class)
public class Posts extends CRUD {

	@Before
	static void setConnectUser() {

		if(Security.isConnected()) {
			User user = User.find("byEmail",Security.connected()).first();
			renderArgs.put("user", user.nickname);
			renderArgs.put("id", user.id);

		}

	}

}
