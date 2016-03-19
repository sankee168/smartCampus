package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mallem on 3/19/16.
 */
@Entity
@Table(name = "LiveEvents")
public class LiveEvents extends Model {

    @Id
    private int id;

    private int beaconId;

    private int eventId;

}
