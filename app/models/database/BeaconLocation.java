package models.database;

import com.avaje.ebean.Model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mallem on 3/19/16.
 */
@Data
@Entity
@Table(name = "beacon_location")
public class BeaconLocation extends Model {

    @Id
    @GeneratedValue
    private int beaconId;

    private String location;

}
