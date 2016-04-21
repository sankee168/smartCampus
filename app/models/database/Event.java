package models.database;

import com.avaje.ebean.Model;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by mallem on 3/19/16.
 */
@Data
@Builder
@Entity
@Table(name = "event")
public class Event extends Model {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String location;

    private Date startTime;

    private Date endTime;

    private String description;

    private String category;

    private String externalLink;

    private boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "beacon_events")
    private List<Beacon> beacons;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "user_events")
    private List<User> users;

    private String createdBy;

    public String[] getCategories() {
        return category.split(",");
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getId() {
        return this.id;
    }

    public String getExternalLink() {
        return this.externalLink;
    }

    public String getLocation() {
        return this.location;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public String getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return "name=" + name + "description=" + description;
    }
}
