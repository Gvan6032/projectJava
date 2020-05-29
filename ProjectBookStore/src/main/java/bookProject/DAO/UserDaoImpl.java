package bookProject.DAO;

import bookProject.domain.Book;
import bookProject.domain.User;
import bookProject.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUser(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> crit = builder.createQuery(User.class);
        List<Predicate> predicate = new ArrayList<Predicate>();
        Root<User> user = crit.from(User.class);
        Predicate condition = builder.like(user.<String>get("userName"),userName);
        predicate.add(condition);
        TypedQuery<User> query = session.createQuery(crit);
        return query.getSingleResult();
    }
}
