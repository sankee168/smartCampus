package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Created by mallem on 3/19/16.
 */
@Entity
@Table(name = "Beacon")
public class Beacon extends Model {

    @Id
    private int beaconId;

    private String data;

    @JoinColumn(name = "locationId")
    private Location location;
}
