/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
public interface ContactTypeRepository extends CrudRepository<ContactType, Serializable>{
 
    public ContactType findByName(String name);
    
    
    
}
