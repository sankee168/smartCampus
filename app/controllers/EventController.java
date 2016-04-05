package controllers;


import com.avaje.ebean.Ebean;
import com.google.inject.Inject;
import models.database.Beacon;
import models.database.Event;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.main;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

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

    public Result createEvent() {
        DynamicForm form = formFactory.form().bindFromRequest();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy/hh:mm:ss");
        try {
            Date startTime = new Date(format.parse(form.get("startTime")).getTime());
            Date endTime = new Date(format.parse(form.get("endTime")).getTime());
            Beacon beacon = Ebean.find(Beacon.class).where().ieq("id", form.get("beaconId")).findUnique();
            Event event = Event.builder()
                    .name(form.get("name"))
                    .description(form.get("description"))
                    .externalLink(form.get("externalLink"))
                    .category(form.get("category"))
                    .startTime(startTime)
                    .endTime(endTime)
                    .isActive(Boolean.valueOf(form.get("isActive")))
                    .location(form.get("location"))
                    .beacon(beacon).build();
            event.save();
        } catch (ParseException e) {
            return badRequest("Invalid Date Format");
        } catch (PersistenceException p) {
            return badRequest("Event Already Exists");
        }
        return ok();
    }

    public Result testUI() {
        return ok(main.render("Test", null));
    }

}
