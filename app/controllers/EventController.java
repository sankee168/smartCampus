package controllers;


import com.avaje.ebean.Ebean;
import com.google.inject.Inject;
import models.database.Beacon;
import models.database.Event;
import models.database.Location;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.createEvent;
import views.html.main;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by mallem on 3/19/16.
 */
public class EventController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result getEventsByLocation(String location) {
        List<Event> eventList = Ebean.find(Event.class).where().ieq("location", location).findList();
        return ok(eventList.toString());
    }

    public Result getEventsByCategories() {
        DynamicForm form = formFactory.form().bindFromRequest();
        String[] categories = form.get("categories").split(",");

        HashSet<Event> events = new HashSet<>();

        for (String category : categories) {
            events.addAll(Ebean.find(Event.class).where().ieq("category", category).ieq("is_active", "1").findList());
        }

        return ok(events.toString());
    }

    public Result getEventPage(String deviceId) {
        //todo: see if this devcieId is registered and has admin rights. If deviceId is registered then render the createEvent Page or other thing
        Form<Event> eventForm = formFactory.form(Event.class);
        List<Location> locations = Ebean.find(Location.class).findList();
        return ok(createEvent.render(eventForm, locations));
    }

    /*
        TODO : Should render success page with link to go to all events created.
        TODO : Take locations through multi select. Fix start/end time
        TODO : Find a way to plugin createdBy(Take it as input in GET request)
        TODO : convert category into multiselect
        TODO : prof suggests that we should show events even though the user is not subscribed to them based on timings (e.g., say showing food related events to most of the users at 4 pm.) Make a plan of action for the same.
     */
    public Result createEvent() {
        Map<String, String[]> form = request().body().asFormUrlEncoded();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {
            Date startTime = new Date(format.parse(form.get("startTime")[0]).getTime());
            Date endTime = new Date(format.parse(form.get("endTime")[0]).getTime());
            Beacon beacon = Ebean.find(Beacon.class).where().ieq("id", form.get("beaconId")[0]).findUnique();
            Event event = Event.builder()
                    .name(form.get("name")[0])
                    .description(form.get("description")[0])
                    .externalLink(form.get("externalLink")[0])
                    .category(form.get("category")[0])
                    .startTime(startTime)
                    .endTime(endTime)
                    .isActive(Boolean.valueOf(form.get("isActive")[0]))
                    .location(form.get("location")[0])
                    .createdBy(form.get("createdBy")[0])
                    .beacon(beacon).build();
            event.save();

        } catch (PersistenceException p) {
            return badRequest("Event Already Exists");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public Result testUI() {
        return ok(main.render("Test", null));
    }

    public Result getRecommendedEvents(String deviceId) {
        //todo: return get recommened events for the user
        return ok();
    }

    public Result getStarredEvents(String deviceId) {
        //todo: return starred events for the user
        return ok();
    }

}
