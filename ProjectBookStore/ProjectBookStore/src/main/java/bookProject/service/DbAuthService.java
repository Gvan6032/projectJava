package bookProject.service;

import bookProject.DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

public class DbAuthService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        bookProject.domain.User userAcc = userDao.findUser(username);
        System.out.println("User= " + userAcc);
        if (userAcc == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }
        String role = userAcc.getUserRole();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantList.add(authority);
        boolean enabled = userAcc.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        UserDetails userDetails = (UserDetails) new User (userAcc.getUserName(),userAcc.getPassword(),enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, grantList);
        return userDetails;
    }
}
