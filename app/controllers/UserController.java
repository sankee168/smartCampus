package controllers;

import com.avaje.ebean.Ebean;
import models.database.User;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by mallem on 4/2/16.
 */
public class UserController extends Controller {

    public Result getUser(String username) {
        User user = Ebean.find(User.class).where().ieq("user_name", username).findUnique();
        return ok(user.toString());
    }

    public Result createUser() {
        return ok();
    }
}
