package models;

import javax.persistence.Entity;

import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model{


	@Email
	@Required
	public String email;

	@Required
	@Password
	public String password;

	@Required
	public String fullname;

	@Required
	public String nickname;

	public boolean isAdmin ;




	public User(String email, String password, String fullname, String nickname) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.nickname = nickname;
	}


	public static User connect(String email ,String password){
		return find("byEmailAndPassword",email,password).first();

	}

	@Override
	public String toString() {
		return fullname + "（" + email + "）";
	}







}
