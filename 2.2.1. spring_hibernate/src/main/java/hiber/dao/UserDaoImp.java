package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByModelAndSeries(String model, int series){
      TypedQuery<User> typedQuery =
              sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u WHERE u.car.model=:model and u.car.series=:series");
      typedQuery.setParameter("model", model);
      typedQuery.setParameter("series", series);
      return typedQuery.getSingleResult();
   }

}
