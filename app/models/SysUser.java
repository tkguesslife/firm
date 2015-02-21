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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
@Entity
@Table(name = "sys_user")
@NamedQueries({
    @NamedQuery(name = "SysUser.findAll", query = "SELECT s FROM SysUser s"),
    @NamedQuery(name = "SysUser.findById", query = "SELECT s FROM SysUser s WHERE s.id = :id"),    
    @NamedQuery(name = "SysUser.findByUsernameCanonical", query = "SELECT s FROM SysUser s WHERE s.usernameCanonical = :usernameCanonical"),
    @NamedQuery(name = "SysUser.findByEmail", query = "SELECT s FROM SysUser s WHERE s.email = :email"),
    @NamedQuery(name = "SysUser.findByEmailCanonical", query = "SELECT s FROM SysUser s WHERE s.emailCanonical = :emailCanonical"),
    @NamedQuery(name = "SysUser.findByEnabled", query = "SELECT s FROM SysUser s WHERE s.enabled = :enabled"),
    @NamedQuery(name = "SysUser.findBySalt", query = "SELECT s FROM SysUser s WHERE s.salt = :salt"),
    @NamedQuery(name = "SysUser.findByPassword", query = "SELECT s FROM SysUser s WHERE s.password = :password"),
    @NamedQuery(name = "SysUser.findByLastLogin", query = "SELECT s FROM SysUser s WHERE s.lastLogin = :lastLogin"),
    @NamedQuery(name = "SysUser.findByLocked", query = "SELECT s FROM SysUser s WHERE s.locked = :locked"),
    @NamedQuery(name = "SysUser.findByExpired", query = "SELECT s FROM SysUser s WHERE s.expired = :expired"),
    @NamedQuery(name = "SysUser.findByExpiresAt", query = "SELECT s FROM SysUser s WHERE s.expiresAt = :expiresAt"),
    @NamedQuery(name = "SysUser.findByConfirmationToken", query = "SELECT s FROM SysUser s WHERE s.confirmationToken = :confirmationToken"),
    @NamedQuery(name = "SysUser.findByPasswordRequestedAt", query = "SELECT s FROM SysUser s WHERE s.passwordRequestedAt = :passwordRequestedAt"),
    @NamedQuery(name = "SysUser.findByCredentialsExpired", query = "SELECT s FROM SysUser s WHERE s.credentialsExpired = :credentialsExpired"),
    @NamedQuery(name = "SysUser.findByCredentialsExpireAt", query = "SELECT s FROM SysUser s WHERE s.credentialsExpireAt = :credentialsExpireAt"),
    @NamedQuery(name = "SysUser.findByCreatedAt", query = "SELECT s FROM SysUser s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "SysUser.findByUpdatedAt", query = "SELECT s FROM SysUser s WHERE s.updatedAt = :updatedAt")})
public class SysUser implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username_canonical")
    private String usernameCanonical;
    
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email_canonical")
    private String emailCanonical;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "enabled")
    private boolean enabled;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "salt")
    private String salt;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "locked")
    private boolean locked;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "expired")
    private boolean expired;
    
    @Column(name = "expires_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresAt;
    
    @Size(max = 255)
    @Column(name = "confirmation_token")
    private String confirmationToken;
    
    @Column(name = "password_requested_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordRequestedAt;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "roles")
    private String roles;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "credentials_expired")
    private boolean credentialsExpired;
    
    @Column(name = "credentials_expire_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date credentialsExpireAt;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    
    
    @JoinColumn(name = "contactId", referencedColumnName = "id")
    @OneToOne
    private Contact contact;
    
    

    public SysUser() {
    }

    public SysUser(Integer id) {
        this.id = id;
    }

    public SysUser(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String salt, String password, boolean locked, boolean expired, String roles, boolean credentialsExpired, Date createdAt) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.locked = locked;
        this.expired = expired;
        this.roles = roles;
        this.credentialsExpired = credentialsExpired;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean getExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public Date getCredentialsExpireAt() {
        return credentialsExpireAt;
    }

    public void setCredentialsExpireAt(Date credentialsExpireAt) {
        this.credentialsExpireAt = credentialsExpireAt;
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
        if (!(object instanceof SysUser)) {
            return false;
        }
        SysUser other = (SysUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.contact.toString();
    }
    
}
