package models.database;

import com.avaje.ebean.Model;
import lombok.Builder;
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
@Builder
@Table(name = "user")
public class User extends Model {

    @Id
    private int deviceId;

    private String userName;

    private String role;

    private String categories;

}
