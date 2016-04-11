package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import models.database.Beacon;
import models.database.Event;
import models.database.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;
import scala.collection.JavaConverters;
import views.html.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mallem on 4/5/16.
 */
public class BeaconController extends Controller {

    @Inject
    FormFactory formFactory;


    public Result getBeaconById(String id) {
        Beacon beacon = Ebean.find(Beacon.class).where().ieq("id", id).findUnique();
//        scala.collection.immutable.List<String> ls = JavaConverters.asScalaBufferConverter(beacon.getEvents()).asScala();
        return ok(event.render(beacon.getEvents()));
    }

    public Result createBeacon() {
        Form<Beacon> form = formFactory.form(Beacon.class).bindFromRequest();

        Beacon beacon = Beacon.builder().description(form.data().get("description"))
                .id(Integer.parseInt(form.data().get("id"))).build();
        beacon.save();
        return ok();
    }

    public Result getEventsByUser(String id, String userId) {
        List<User> users = Ebean.find(User.class).where().ieq("id", userId).findList();
        List<String> categories = new ArrayList<>();
        List<Event> returnEvents = new ArrayList<>();
        Iterator<User> iter = users.iterator();
        while(iter.hasNext()) {
            User curr = iter.next();
            categories.add(curr.getCategories());
        }
        Beacon beacon = Ebean.find(Beacon.class).where().ieq("id", id).findUnique();

        List<Event> allEvents = beacon.getEvents();

        Iterator<Event> eventIter = allEvents.iterator();
        while(eventIter.hasNext()) {
            Event currEvent = eventIter.next();
            if(ifIntersectionExists(categories, currEvent.getCategory())) {
                returnEvents.add(currEvent);
            }
        }

        return ok(event.render(returnEvents));
    }

    public boolean ifIntersectionExists(List<String> categories, String dbCategory) {
        String[] dbCategories = dbCategory.split(",");
        for(int i = 0; i< dbCategories.length; i++) {
            if(categories.contains(dbCategories[i])) {
                return true;
            }
        }
        return false;
    }
}
