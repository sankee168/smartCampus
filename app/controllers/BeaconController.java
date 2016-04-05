package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import models.database.Beacon;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by mallem on 4/5/16.
 */
public class BeaconController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    ObjectMapper objectMapper;

    public Result getBeaconById(String id) {
        Beacon beacon = Ebean.find(Beacon.class).where().ieq("id", id).findUnique();
        return ok(beacon.toString());
    }

    public Result createBeacon() {
        Form<Beacon> form = formFactory.form(Beacon.class).bindFromRequest();

        Beacon beacon = Beacon.builder().description(form.data().get("description"))
                .id(Integer.parseInt(form.data().get("id"))).build();
        beacon.save();
        return ok();
    }
}
