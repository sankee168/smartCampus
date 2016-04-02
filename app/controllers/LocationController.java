package controllers;

import com.avaje.ebean.Ebean;
import models.database.Location;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by mallem on 4/2/16.
 */
public class LocationController extends Controller {

    public Result getAll() {
        List<Location> locList = Ebean.find(Location.class).findList();
        return ok(locList.toString());
    }

    public Result getByName(String name) {
        Location loc = Ebean.find(Location.class).where().ieq("name", name).findUnique();
        return ok(loc.toString());
    }
}
