package controllers;

import forms.LoginForm;
import java.util.List;
import java.util.Map;
import models.ContactType;
import models.ContactTypeRepository;
import models.SysUser;
import models.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import static play.data.Form.form;
import play.libs.Yaml;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.redirect;
import services.SysUserService;

import views.html.*;

@org.springframework.stereotype.Controller
public class Application extends Controller {
    
    @Autowired
    SysUserRepository sysUserRepository;
    
    @Autowired
    SysUserService sysUserService;
    
    @Autowired
    ContactTypeRepository contactTypeRepository;

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public Result login(){
       
        Form<LoginForm> loginForm = form(LoginForm.class);  
        return ok(views.html.login.render(loginForm, ""));
    }
    
    /**
     * Handle login form POST
     *
     * @return Result
     */
    public Result doLogin() {
        DynamicForm loginForm = Form.form().bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(views.html.login.render(loginForm, "Invalid username or password"));
        }

        SysUser sysUser = null;
        sysUser = sysUserService.authenticate(loginForm.get("username"), loginForm.get("password"));
        if (sysUser == null) {
            return badRequest(views.html.login.render(loginForm, "Invalid username or password"));
        }
        
        play.Logger.info("Roles       :"+sysUser.getRoles());

        return redirect(routes.Application.app());
    }
    
    public Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.Application.login()
        );
    }
    
    public Result full(){
        return ok(views.html.full.render("Full layout"));
    }
    
    public Result loadInitData(){
        
        return ok(views.html.init.render());
    }
    
    public Result doInit(){
        
        DynamicForm requestData = Form.form().bindFromRequest();
        
        Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");
        
        List<Object> contactTypes = all.get("contactTypes");
        for(Object contactType : contactTypes){
            contactTypeRepository.save((ContactType)contactType);
        }
        
        return ok(views.html.init.render());
    }
    
     
    @Security.Authenticated(Secured.class)
    public Result app(){
        
        return ok(views.html.app.render());
    }
    
   

}
