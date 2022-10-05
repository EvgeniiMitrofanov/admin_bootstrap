package ru.javamentor.java_pp_313.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users6")
public class User implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "username")
   private String username;

   @Column(name = "lastName")
   private String lastName;

   @Column(name = "mail")
   private String mail;

   @Column(name = "password")
   private String password;

   @ManyToMany
   @JoinTable(name = "users_roles6",
           joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
   private Set<Role> roles = new java.util.HashSet<>();

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
   }

   public Collection<? extends GrantedAuthority> getRoles() {
      return roles;
   }

   public void setRoles (Set<Role> roles) {
      this.roles = roles;
   }

   public User() {

   }
   public User(String username, String lastName, String mail, String password) {
      this.username = username;
      this.lastName = lastName;
      this.mail = mail;
      this.password = password;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }
   public String getMail() {
      return mail;
   }
   public void setMail(String mail) {
      this.mail = mail;
   }
   public String getLastName() {
      return lastName;
   }
   public void setLastName (String lastName) {
      this.lastName = lastName;
   }
   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public String toString() {
      return "User{" +
              "id = " + id +
              ", first name = '" + username + '\'' +
              ", last Name = '" + lastName + '\'' +
              ", email = " + mail +
              '}';
   }

}
