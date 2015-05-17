/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Date;
import play.*;
import models.SysUser;
import models.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Controller;
import play.mvc.Http.*;
import play.mvc.Result;
import static play.mvc.Results.redirect;
import play.mvc.Security;
import services.SysUserService;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
public class Secured extends Security.Authenticator {

    public static final String UNAUTHENTICATED = "unauthenticated";

    @Autowired
    private SysUserRepository sysUserRepository;

    public SysUser getLoggedInUser() {
        if (Controller.session().get("username") == null) {
            return null;
        }

        SysUser su = sysUserRepository.findByUsername(Controller.session().get("username"));

        return su;
    }
// 
//    public static String getLoggedInUsername() {
//        if (session("userId") == null)
//            return null;
//        return User.findById(Long.parseLong(session("userId"))).getUsername();
//    }

    @Override
    public String getUsername(Context ctx) {

        // see if user is logged in
        if (ctx.session().get("username") == null) {
            return null;
        }

        // see if the session is expired
        String previousTick = ctx.session().get("userTime");
        if (previousTick != null && !previousTick.equals("")) {
            long previousT = Long.valueOf(previousTick);
            long currentT = new Date().getTime();
            long timeout = Long.valueOf(Play.application().configuration().getString("sessionTimeout")) * 1000 * 60;
            if ((currentT - previousT) > timeout) {
                // session expired
                ctx.session().clear();
                return null;
            }
        }

        // update time in session
        String tickString = Long.toString(new Date().getTime());
//        session("userTime", tickString);
        ctx.session().put("userTime", tickString);

//        return User.findById(Long.parseLong(session("userId"))).getUsername();
        return ctx.session().get("username");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.login());
    }
    
}
