package bookProject.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    public static final String ADMIN = "ADMIN";
    public static final String EMPLOYEE = "EMPLOYEE";

    private Integer id;
    private String userName;
    private String password;
    private boolean active;
    private String userRole;

    public User(){

    }

    public User(String userName, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, List<GrantedAuthority> grantList){
        this.userName=userName;
        this.password=password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "userName", length = 20, nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", length = 20, nullable = false)
    @Size(min = 6, message = "At least 6 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "active", length = 1, nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "userRole", length = 20, nullable = false)
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString()  {
        return "["+ this.userName+","+ this.password+","+ this.userRole+"]";
    }
}

