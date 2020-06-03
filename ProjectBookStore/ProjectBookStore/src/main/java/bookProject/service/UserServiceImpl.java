package bookProject.service;

import bookProject.DAO.UserDao;
import bookProject.DAO.UserDaoImpl;
import bookProject.domain.User;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Override
    public User findUser(String userName) {
        userDao = new UserDaoImpl();
        return userDao.findUser(userName);
    }
}
