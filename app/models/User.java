package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mallem on 3/19/16.
 */
@Entity
@Table(name = "User")
public class User extends Model {

    @Id
    private int id;

    private String userName;

    private String role;

    private String category;
}
