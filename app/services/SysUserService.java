package services;

import models.SysUser;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
public interface SysUserService {
    
      
  public SysUser authenticate(String username, String password);
  
  public SysUser getUser();
    
}
