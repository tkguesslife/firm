package controllers;

import models.SysUser;
import models.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Controller;
import play.mvc.Security;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
public abstract class SecuredContoller  extends Controller{
    
    @Autowired
    private SysUserRepository sysUserRepository;
    
    public SysUser getLoggedUser(){
        SysUser su = sysUserRepository.findByUsername(request().username());
        
        return su;
        
        
    }
}
