package spittr.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spittr.domain.Spittle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository
public class HibernateSpittleRepository implements SpittleRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateSpittleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public long count() {
        return findAll().size();
    }

    public List<Spittle> findRecent() {
        return findSpittles(10, 10);
    }

    @Override
    @Transactional
    public List<Spittle> findSpittles(long max, int count) {
        //Session session = sessionFactory.getCurrentSession();
        //Transaction tx = session.beginTransaction();
        List<Spittle> list = spittleCriteria()
                .setMaxResults(count)
                .list();
        return list;
    }

    public Spittle findOne(long id) {
        return (Spittle) currentSession().get(Spittle.class, id);
    }

    @Override
    public Spittle saveSpittle(Spittle spittle) {
        Serializable id = currentSession().save(spittle);
        return new Spittle(
                (Long) id,
                spittle.getMessage(),
                spittle.getTime(),
                spittle.getLatitude(),
                spittle.getLongitude());
    }

    public List<Spittle> findBySpitterId(long spitterId) {
        return spittleCriteria()
                .add(Restrictions.eq("spitter.id", spitterId))
                .list();
    }

    public void delete(long id) {
        currentSession().delete(findOne(id));
    }

    public List<Spittle> findAll() {
        return (List<Spittle>) spittleCriteria().list();
    }

    private Criteria spittleCriteria() {
        return currentSession()
                .createCriteria(Spittle.class)
                .addOrder(Order.desc("time"));
    }

}