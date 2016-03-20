package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by mallem on 3/19/16.
 */
@Entity
@Table(name = "location")
public class Location extends Model {

    @Id
    private int id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "location")
    private List<Beacon> beaconId;
}
