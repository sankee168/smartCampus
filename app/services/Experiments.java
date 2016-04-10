package services;

import com.avaje.ebean.Ebean;
import models.database.Beacon;
import models.database.Location;
import models.database.User;

import javax.persistence.EntityManager;

/**
 * Created by sank on 3/19/16.
 */
public class Experiments {

    public void method() {
        User user = User.builder().userName("asdas").role("asdas").categories("asdfashjgdas").build();
        Beacon beacon = Beacon.builder().id(1).description("asdasd").build();


        Ebean.save(user);
    }
}
