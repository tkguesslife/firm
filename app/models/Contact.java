/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import play.data.validation.Constraints;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
@Entity
@Table(name = "contact")
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id"),
    @NamedQuery(name = "Contact.findByFirstname", query = "SELECT c FROM Contact c WHERE c.firstname = :firstname"),
    @NamedQuery(name = "Contact.findByLastname", query = "SELECT c FROM Contact c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Contact.findByCompanyName", query = "SELECT c FROM Contact c WHERE c.companyName = :companyName"),
    @NamedQuery(name = "Contact.findByIdnumber", query = "SELECT c FROM Contact c WHERE c.idnumber = :idnumber")})
public class Contact implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 100)
    @Column(name = "firstname")
    @Constraints.Required(message="Required")
    private String firstname;
    
    @Size(max = 100)
    @Column(name = "lastname")
    @Constraints.Required(message="Required")
    private String lastname;
    
    @Size(max = 100)
    @Column(name = "companyName")
    private String companyName;
    
    @Size(max = 100)
    @Column(name = "idnumber")
    @Constraints.Required(message="Required")
    private String idnumber;
    
    
    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    
    
    @JoinColumn(name = "contactDetailId", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private ContactDetail contactDetail;
    
    @JoinColumn(name = "contactTypeId", referencedColumnName = "id")
    @ManyToOne
    private ContactType contactType;
    
    
    @OneToOne(mappedBy = "contact")
    private SysUser sysUser;

    public Contact() {
        contactDetail = new ContactDetail();
    }
    
    public Contact(ContactDetail contactDetail, ContactType contactType){
        this.contactDetail = contactDetail;
        this.contactType = contactType;
    }

    public Contact(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    


    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.firstname+" "+this.lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    
    
}
