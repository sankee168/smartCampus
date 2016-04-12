package models.database;

import com.avaje.ebean.Model;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mallem on 3/19/16.
 */

/**
 * This Table Would be prepopulated
 */
@Data
@Entity
@Builder
@Table(name = "location")
public class Location extends Model {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<Beacon> beacons;

}
