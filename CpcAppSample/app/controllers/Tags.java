package controllers;

import javax.persistence.Entity;

import play.mvc.With;

import controllers.CRUD;

@Check("admin")
@With(Secure.class)
public class Tags extends CRUD {



}
