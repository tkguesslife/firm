package forms;

import play.data.validation.Constraints;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
public class LoginForm {

    @Constraints.Required
    public String username;
    
    @Constraints.Required
    public String password;
        

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
            
}
