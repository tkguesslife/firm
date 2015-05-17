package models;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
public interface SysUserRepository extends CrudRepository<SysUser, Serializable> {

    public SysUser findByEmail(String email);
    
    public SysUser findByUsername(String username);
    
//    public SysUser authenticate(String username, String password);
    
}
