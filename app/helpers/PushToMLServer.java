package helpers;

import io.prediction.Event;
import io.prediction.EventClient;
import references.Constants;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by sank on 4/16/16.
 */
public class PushToMLServer {
    public void pushEvent(Event event) {
        EventClient client = new EventClient(Constants.MlConstants.ACCESS_KEY, Constants.Urls.ML_URL_POST);
        try {
            client.createEvent(event);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
