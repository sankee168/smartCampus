package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by mallem on 3/19/16.
 */
@Entity
@Table(name = "Event")
public class Event extends Model {

    @Id
    private int id;

    private String name;

    private String location;

    private Date startTime;

    private Date endTime;

    private String description;

    private String tags;

    private String externalLink;

}
