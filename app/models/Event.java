package models;

import com.avaje.ebean.Model;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private int id;

    private String name;

    private int locationId;

    private Date date;

    private String description;

    @OneToMany(mappedBy = "event")
    private List<Category> categoryId;

    private String externalLink;

    private boolean isActive;

}
