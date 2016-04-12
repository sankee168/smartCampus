package models.database;

import com.avaje.ebean.Model;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

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

    @ManyToOne
    private Beacon beacon;

    private int createdBy;
}
