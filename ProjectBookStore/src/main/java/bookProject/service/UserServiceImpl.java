package bookProject.service;

import bookProject.DAO.UserDao;
import bookProject.DAO.UserDaoImpl;
import bookProject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User findUser(String userName) {
        userDao = new UserDaoImpl();
        return userDao.findUser(userName);
    }
}
