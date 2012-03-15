import java.io.FileNotFoundException;

import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class BootStrap extends Job{

	@Override
	public void doJob() throws FileNotFoundException {

		if(User.count() == 0 ){
			Fixtures.delete();
			Fixtures.loadModels("initial-data.yml");
			System.out.println("fileあったよ");

		}


	}


}
