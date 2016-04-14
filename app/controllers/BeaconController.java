package controllers;

import com.avaje.ebean.Ebean;
import com.google.inject.Inject;
import models.database.Beacon;
import models.database.Event;
import models.database.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import references.Constants;
import views.html.createUser;
import views.html.event;

import java.util.*;

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

//    public Result createBeacon() {
//        Form<Beacon> form = formFactory.form(Beacon.class).bindFromRequest();
//
//        Beacon beacon = Beacon.builder().description(form.data().get("description"))
//                .id(Integer.parseInt(form.data().get("id"))).build();
//        beacon.save();
//        return ok();
//    }

    public Result getEventsByUser(String id, String userId) {
        List<User> users = Ebean.find(User.class).where().ieq("id", userId).findList();
        List<String> categories = new ArrayList<>();
        List<Event> returnEvents = new ArrayList<>();
        Iterator<User> iter = users.iterator();
        while (iter.hasNext()) {
            User curr = iter.next();
            categories.add(curr.getCategories());
        }
        Beacon beacon = Ebean.find(Beacon.class).where().ieq("id", id).findUnique();

        List<Event> allEvents = beacon.getEvents();

        Iterator<Event> eventIter = allEvents.iterator();
        while (eventIter.hasNext()) {
            Event currEvent = eventIter.next();
            if (ifIntersectionExists(categories, currEvent.getCategory())) {
                returnEvents.add(currEvent);
            }
        }

        return ok(event.render(returnEvents));
    }

    public Result getEventsByBeacons() {
        String deviceId = "";
        String[] beaconIds = new String[Constants.KeyWords.TOTAL_NUMBER_OF_BEACONS];
        List<String> categories = new ArrayList<>();
        List<Event> returnEvents = new ArrayList<>();
        final Set<Map.Entry<String, String[]>> entries = request().queryString().entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            if (entry.getKey().equals(Constants.KeyWords.DEVICE_ID)) {
                deviceId= entry.getValue()[0];
            } else if (entry.getKey().equals(Constants.KeyWords.BEACON_ID)) {
                beaconIds = entry.getValue();
            }
            else {
                System.out.println("Invalid query parameter");
            }
        }

        User user = Ebean.find(User.class).where().ieq("deviceId", deviceId).findUnique();
        if(user != null) {
            List<Beacon> beacons = Ebean.find(Beacon.class).where().in("id", beaconIds).findList();
            Iterator<Beacon> beaconIter = beacons.iterator();
            while (beaconIter.hasNext()) {
                Beacon currBeacon = beaconIter.next();
                Iterator<Event> currEventsIterator = currBeacon.getEvents().iterator();
                while (currEventsIterator.hasNext()) {
                    Event currEvent = currEventsIterator.next();
                    if (ifCategoryMatches(user.getCategories(), currEvent.getCategory())) {
                        returnEvents.add(currEvent);
                    }
                }
            }

            return ok(event.render(returnEvents));
        }
        else{
            //todo: redirect to page to create the user
            return ok(createUser.render());
        }
    }

    public boolean ifIntersectionExists(List<String> categories, String dbCategory) {
        String[] dbCategories = dbCategory.split(",");
        for (int i = 0; i < dbCategories.length; i++) {
            if (categories.contains(dbCategories[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean ifCategoryMatches(String userCategories, String eventCategory) {
        String[] dbCategories = userCategories.split(",");
        String[] eventCategories = eventCategory.split(",");

        for (int i = 0; i < dbCategories.length; i++) {
            for(int j = 0; j < eventCategories.length; j++){
                if(dbCategories[i].equals(eventCategories[j]))
                    return true;
            }
        }
        return false;

    }
}
