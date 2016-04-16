package controllers;

import com.avaje.ebean.Ebean;
import com.google.inject.Inject;
import models.database.Event;
import models.database.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;
import java.util.Map;

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
        Map<String, String[]> form = request().body().asFormUrlEncoded();
        String[] categoryList = form.get("categories");
        String categories = "";
        if (categoryList != null) {
            for (String category : categoryList) {
                categories += category + ",";
            }
        } else {
            categories += "all";
        }
        User user = User.builder()
                .deviceId(form.get("deviceId")[0])
                .userName(form.get("userName")[0])
                .categories(categories)
                .role("user").build();
        try {
            user.save();
        } catch (PersistenceException p) {
            return badRequest("userName already exists");
        }
        return ok();
    }

    /* Add events starred by users. Should be called from each Event page */
    public Result addEventToUser() {
        DynamicForm form = formFactory.form().bindFromRequest();
        User user = Ebean.find(User.class).where().ieq("device_id", form.get("deviceId")).findUnique();
        Event event = Ebean.find(Event.class).where().ieq("id", form.get("eventId")).findUnique();
        user.getEvents().add(event);
        try {
            user.save();
        } catch (PersistenceException e) {
            return badRequest("Already Starred Event");
        }
        return ok();
    }
}
