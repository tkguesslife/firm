/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lib.MessageDigestPasswordEncoder;
import models.SysUser;
import models.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.Logger;
import play.mvc.Controller;

/**
 * Holds SysUser interactions
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @PersistenceContext
    EntityManager em;
    
    @Autowired
    SysUserRepository sysUserRepository;
    

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public SysUser authenticate(String username, String password) {
        SysUser sysUser = sysUserRepository.findByUsername(username);

        if (sysUser != null) {

            try {
                String hashPassword = MessageDigestPasswordEncoder.encodePassword(password, sysUser.getSalt());
                if (hashPassword == null ? sysUser.getPassword() == null : hashPassword.equals(sysUser.getPassword())) {
                    Logger.info("Matched");
                    Controller.session().clear();
                    Controller.session("username", sysUser.getUsername());
                    Controller.session("userFullname", sysUser.toString());
                    return sysUser;
                } else {
                    Logger.info("Wrong password");
                }
            } catch (Exception e) {
                Logger.error("Failed to authenticate user.");
                Logger.error(e.toString());
            }

        }

        return null;
    }

    public SysUser getUser() {
        SysUser user = sysUserRepository.findByUsername(Controller.session().get("username"));
        return user;
    }

    
}
