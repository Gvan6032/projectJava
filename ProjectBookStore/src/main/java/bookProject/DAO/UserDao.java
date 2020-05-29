package bookProject.DAO;

import bookProject.domain.User;
import org.springframework.stereotype.Repository;


public interface UserDao {
    public User findUser(String userName);
}
