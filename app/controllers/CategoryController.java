package controllers;

import com.avaje.ebean.Ebean;
import com.google.inject.Inject;
import models.database.Category;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by mallem on 4/2/16.
 */
public class CategoryController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result getAll() {
        List<Category> categoryList = Ebean.find(Category.class).findList();
        return ok(categoryList.toString());
    }

    public Result getByName(String name) {
        Category category = Ebean.find(Category.class).where().ieq("name", name).findUnique();
        return ok(category.toString());
    }

    public Result createCategory() {
        DynamicForm form = formFactory.form().bindFromRequest();
        Category category = Category.builder()
                .name(form.get("name"))
                .description(form.get("description"))
                .build();
        category.save();
        return ok();
    }
}
