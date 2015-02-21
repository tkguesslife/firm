package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

@org.springframework.stereotype.Controller
public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public Result login(){
        return ok(views.html.login.render());
    }
    
    public Result full(){
        return ok(views.html.full.render("Full layout"));
    }

}
