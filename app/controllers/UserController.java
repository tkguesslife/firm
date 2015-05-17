package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import java.util.logging.Level;
import lib.MessageDigestPasswordEncoder;
import lib.Uniqid;
import models.Contact;
import models.ContactDetail;
import models.SysUser;
import models.SysUserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import static play.data.Form.form;
import play.libs.Json;
import play.libs.Yaml;

import views.html.*;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
@org.springframework.stereotype.Controller
public class UserController extends SecuredContoller {

    @Autowired
    SysUserRepository sysUserRepository;

    /**
     * Save user from POST
     *
     * @return
     */
    public Result saveUser() {

        try {
            DynamicForm requestData = Form.form().bindFromRequest();

            SysUser sysUser = new SysUser();
            sysUser.setRoles("ADMIN");

            sysUser.setEmail(requestData.get("emailAddress"));
            sysUser.setUsername(requestData.get("username"));
            sysUser.setPassword(MessageDigestPasswordEncoder.encodePassword(requestData.get("password"), sysUser.getSalt()));

            Contact contact = new Contact();
            contact.setFirstname(requestData.get("firstname"));
            contact.setLastname(requestData.get("lastname"));

            ContactDetail contactDetail = new ContactDetail();
            contactDetail.setEmail(requestData.get("emailAddress"));
            contactDetail.setContact(contact);

            sysUser.setContact(contact);

            sysUserRepository.save(sysUser);

            return ok("UserController.save");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return badRequest("Failed to create user");
    }
    
    /**
     * get list of users
     * @return 
     */
    public Result list(){
        
//        List<SysUser> users =(List<SysUser>) sysUserRepository.findAll();
        JSONArray result;
        try {
            result = getUsers();
        return ok(result.toString());
        } catch (JSONException ex) {
            java.util.logging.Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return badRequest("Failed to get users");
    }
    
    
    /**
     * Get all users
     * @return
     * @throws JSONException 
     */
    private JSONArray getUsers() throws JSONException{
        List<SysUser> users = (List<SysUser>) sysUserRepository.findAll();
        JSONArray result =  new JSONArray();
        for(SysUser sysUser: users){
            JSONObject juser = new JSONObject();
            juser.put("id", sysUser.getId());
            juser.put("username", sysUser.getUsername());
            juser.put("firstname", sysUser.getContact().getFirstname());
            juser.put("lastname", sysUser.getContact().getLastname());
            juser.put("emailaddress", sysUser.getEmail());
            
            result.put(juser);
        }
        
        
        return result;
    }

}
