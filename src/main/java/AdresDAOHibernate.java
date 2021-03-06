import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdresDAOHibernate implements AdresDAO {

    private Session session;

    public AdresDAOHibernate(Session sess) {
        this.session = sess;
    }

    @Override
    public boolean save(Adres adres) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.save(adres);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.update(adres);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.delete(adres);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            Adres adres = session.createQuery("FROM Adres WHERE reiziger_id =" + reiziger.getReiziger_id(), Adres.class).getSingleResult();
            return adres;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try {
            List<Adres> adressen = session.createQuery("FROM Adres ", Adres.class).getResultList();
            return adressen;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
