package models;

import org.springframework.data.repository.CrudRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
@Repository
public interface SysUserRepository extends CrudRepository<SysUser, Long> {

    public SysUser findByEmail(String email);
    
    public SysUser findByUsername(String username);
    
//    public SysUser authenticate(String username, String password);
    
}
