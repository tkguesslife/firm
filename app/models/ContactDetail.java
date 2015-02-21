/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import play.data.validation.Constraints;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
@Entity
@Table(name = "contact_detail")
@NamedQueries({
    @NamedQuery(name = "ContactDetail.findAll", query = "SELECT c FROM ContactDetail c"),
    @NamedQuery(name = "ContactDetail.findById", query = "SELECT c FROM ContactDetail c WHERE c.id = :id"),
    @NamedQuery(name = "ContactDetail.findByTitle", query = "SELECT c FROM ContactDetail c WHERE c.title = :title"),
    @NamedQuery(name = "ContactDetail.findByGender", query = "SELECT c FROM ContactDetail c WHERE c.gender = :gender"),
    @NamedQuery(name = "ContactDetail.findByEmail", query = "SELECT c FROM ContactDetail c WHERE c.email = :email"),
    @NamedQuery(name = "ContactDetail.findByMobileNumber", query = "SELECT c FROM ContactDetail c WHERE c.mobileNumber = :mobileNumber"),
    @NamedQuery(name = "ContactDetail.findByOfficeNumber", query = "SELECT c FROM ContactDetail c WHERE c.officeNumber = :officeNumber"),
    @NamedQuery(name = "ContactDetail.findByHomeNumber", query = "SELECT c FROM ContactDetail c WHERE c.homeNumber = :homeNumber"),
    @NamedQuery(name = "ContactDetail.findByStreetAddress1", query = "SELECT c FROM ContactDetail c WHERE c.streetAddress1 = :streetAddress1"),
    @NamedQuery(name = "ContactDetail.findByStreetAddress2", query = "SELECT c FROM ContactDetail c WHERE c.streetAddress2 = :streetAddress2"),
    @NamedQuery(name = "ContactDetail.findByStreetAddress3", query = "SELECT c FROM ContactDetail c WHERE c.streetAddress3 = :streetAddress3"),
    @NamedQuery(name = "ContactDetail.findByStreetAddress4", query = "SELECT c FROM ContactDetail c WHERE c.streetAddress4 = :streetAddress4"),
    @NamedQuery(name = "ContactDetail.findByStreetAddress5", query = "SELECT c FROM ContactDetail c WHERE c.streetAddress5 = :streetAddress5"),
    @NamedQuery(name = "ContactDetail.findByStreetCode", query = "SELECT c FROM ContactDetail c WHERE c.streetCode = :streetCode"),
    @NamedQuery(name = "ContactDetail.findByPostalAddress1", query = "SELECT c FROM ContactDetail c WHERE c.postalAddress1 = :postalAddress1"),
    @NamedQuery(name = "ContactDetail.findByPostalAddress2", query = "SELECT c FROM ContactDetail c WHERE c.postalAddress2 = :postalAddress2"),
    @NamedQuery(name = "ContactDetail.findByPostalAddress3", query = "SELECT c FROM ContactDetail c WHERE c.postalAddress3 = :postalAddress3"),
    @NamedQuery(name = "ContactDetail.findByPostalAddress4", query = "SELECT c FROM ContactDetail c WHERE c.postalAddress4 = :postalAddress4"),
    @NamedQuery(name = "ContactDetail.findByPostalAddress5", query = "SELECT c FROM ContactDetail c WHERE c.postalAddress5 = :postalAddress5"),
    @NamedQuery(name = "ContactDetail.findByPostalCode", query = "SELECT c FROM ContactDetail c WHERE c.postalCode = :postalCode"),
    @NamedQuery(name = "ContactDetail.findByCreatedAt", query = "SELECT c FROM ContactDetail c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "ContactDetail.findByUpdatedAt", query = "SELECT c FROM ContactDetail c WHERE c.updatedAt = :updatedAt")})
public class ContactDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "title")
    private String title;

    @Size(max = 50)
    @Column(name = "gender")
    @Constraints.Required(message = "Required")
    private String gender;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 100)
    @Column(name = "mobileNumber")
    private String mobileNumber;
    @Size(max = 100)
    @Column(name = "officeNumber")
    private String officeNumber;
    @Size(max = 100)
    @Column(name = "homeNumber")
    private String homeNumber;
    @Size(max = 100)
    @Column(name = "streetAddress1")
    private String streetAddress1;
    @Size(max = 100)
    @Column(name = "streetAddress2")
    private String streetAddress2;
    @Size(max = 100)
    @Column(name = "streetAddress3")
    private String streetAddress3;
    @Size(max = 100)
    @Column(name = "streetAddress4")
    private String streetAddress4;
    @Size(max = 100)
    @Column(name = "streetAddress5")
    private String streetAddress5;
    @Size(max = 255)
    @Column(name = "streetCode")
    private String streetCode;
    @Size(max = 100)
    @Column(name = "postalAddress1")
    private String postalAddress1;
    @Size(max = 100)
    @Column(name = "postalAddress2")
    private String postalAddress2;
    @Size(max = 100)
    @Column(name = "postalAddress3")
    private String postalAddress3;
    @Size(max = 100)
    @Column(name = "postalAddress4")
    private String postalAddress4;
    @Size(max = 100)
    @Column(name = "postalAddress5")
    private String postalAddress5;
    @Size(max = 255)
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToOne(mappedBy = "contactDetail")
    private Contact contact;

    @PrePersist
    public void onPrePersist() {
        if (createdAt == null) {
            this.createdAt = new Date();
        }
    }

    public ContactDetail() {
    }

    public ContactDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getStreetAddress3() {
        return streetAddress3;
    }

    public void setStreetAddress3(String streetAddress3) {
        this.streetAddress3 = streetAddress3;
    }

    public String getStreetAddress4() {
        return streetAddress4;
    }

    public void setStreetAddress4(String streetAddress4) {
        this.streetAddress4 = streetAddress4;
    }

    public String getStreetAddress5() {
        return streetAddress5;
    }

    public void setStreetAddress5(String streetAddress5) {
        this.streetAddress5 = streetAddress5;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getPostalAddress1() {
        return postalAddress1;
    }

    public void setPostalAddress1(String postalAddress1) {
        this.postalAddress1 = postalAddress1;
    }

    public String getPostalAddress2() {
        return postalAddress2;
    }

    public void setPostalAddress2(String postalAddress2) {
        this.postalAddress2 = postalAddress2;
    }

    public String getPostalAddress3() {
        return postalAddress3;
    }

    public void setPostalAddress3(String postalAddress3) {
        this.postalAddress3 = postalAddress3;
    }

    public String getPostalAddress4() {
        return postalAddress4;
    }

    public void setPostalAddress4(String postalAddress4) {
        this.postalAddress4 = postalAddress4;
    }

    public String getPostalAddress5() {
        return postalAddress5;
    }

    public void setPostalAddress5(String postalAddress5) {
        this.postalAddress5 = postalAddress5;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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
        if (!(object instanceof ContactDetail)) {
            return false;
        }
        ContactDetail other = (ContactDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.ContactDetail[ id=" + id + " ]";
    }

}
