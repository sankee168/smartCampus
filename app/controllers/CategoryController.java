package controllers;

import com.avaje.ebean.Ebean;
import models.database.Category;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by mallem on 4/2/16.
 */
public class CategoryController extends Controller {

    public Result getAll() {
        List<Category> categoryList = Ebean.find(Category.class).findList();
        return ok(categoryList.toString());
    }

    public Result getByName(String name) {
        Category category = Ebean.find(Category.class).where().ieq("name", name).findUnique();
        return ok(category.toString());
    }
}
