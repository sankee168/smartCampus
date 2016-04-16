package helpers;

import io.prediction.Event;
import models.database.User;
import references.Constants;


import java.util.HashMap;

/**
 * Created by sank on 4/16/16.
 */
public class ConvertToLogFormat {
    public Event convertCreatedEvent(models.database.Event event) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        Event predEvent = new Event();
        predEvent.entityId(String.valueOf(event.getId()));
        predEvent.entityType(Constants.MlConstants.EVENT);
        predEvent.event(Constants.MlConstants.SET);
        map.put("categories", event.getCategories());
        predEvent.properties(map);
        return predEvent;
    }

    public Event convertCreateUser(User user) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        io.prediction.Event predEvent = new io.prediction.Event();
        predEvent.entityId(user.getDeviceId());
        predEvent.entityType(Constants.MlConstants.USER);
        predEvent.event(Constants.MlConstants.SET);
        map.put("categories", user.getCategories().split(","));
        predEvent.properties(map);
        return predEvent;
    }

    public Event convertStarredEvent(String deviceId, String eventId) {
        io.prediction.Event predEvent = new io.prediction.Event();
        predEvent.entityId(deviceId);
        predEvent.entityType(Constants.MlConstants.USER);
        predEvent.event(Constants.MlConstants.STAR);
        predEvent.targetEntityId(eventId);
        predEvent.targetEntityType(Constants.MlConstants.EVENT);

        return predEvent;
    }

    public Event convertViewedEvent(String deviceId, String eventId) {
        io.prediction.Event predEvent = new io.prediction.Event();
        predEvent.entityId(deviceId);
        predEvent.entityType(Constants.MlConstants.USER);
        predEvent.event(Constants.MlConstants.VIEW);
        predEvent.targetEntityId(eventId);
        predEvent.targetEntityType(Constants.MlConstants.EVENT);
        return predEvent;
    }
}
