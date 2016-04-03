package controllers;

import com.avaje.ebean.Ebean;
import com.google.inject.Inject;
import models.database.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;

/**
 * Created by mallem on 4/2/16.
 */
public class UserController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result getUser(String username) {
        User user = Ebean.find(User.class).where().ieq("user_name", username).findUnique();
        return ok(user.toString());
    }

    public Result createUser() {
        DynamicForm form = formFactory.form().bindFromRequest();
        User user = User.builder()
                .userName(form.get("userName"))
                .categories(form.get("categories"))
                .role("user").build();
        try {
            user.save();
        } catch (PersistenceException p) {
            return badRequest("userName already exists");
        }
        return ok();
    }
}
