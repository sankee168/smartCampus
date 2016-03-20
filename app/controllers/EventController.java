package controllers;


import play.mvc.Controller;
import play.mvc.Result;
import services.experiment;

/**
 * Created by mallem on 3/19/16.
 */
public class EventController extends Controller {

    public Result getEventsForUser() {
        experiment experiment1 = new experiment();
        experiment1.method1();
        return ok("Need to return list of events matching user interests.");
    }
}
