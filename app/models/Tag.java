package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mallem on 3/19/16.
 */
@Entity
@Table(name = "Tag")
public class Tag extends Model {

    @Id
    private int id;

    private String name;

    private String description;
}
