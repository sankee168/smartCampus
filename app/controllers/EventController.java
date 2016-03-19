package controllers;


import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by mallem on 3/19/16.
 */
public class EventController extends Controller {

    public Result getEventsForUser() {
        return ok("Need to return list of events matching user interests.");
    }
}
