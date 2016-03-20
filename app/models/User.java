package models;

import com.avaje.ebean.Model;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by mallem on 3/19/16.
 */
@Data
@Builder
@Entity
@Table(name = "user")
public class User extends Model {

    @Id
    private int id;

    private String userName;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<Category> category;

}
