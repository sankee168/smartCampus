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

/**
 * This Table Would be prepopulated
 */
@Data
@Entity
@Table(name = "location")
public class Location extends Model {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

}
