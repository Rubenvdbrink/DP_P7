import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {

    private Session session;

    public OVChipkaartDAOHibernate(Session sess) {
        this.session = sess;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.save(ovChipkaart);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.update(ovChipkaart);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.delete(ovChipkaart);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OVChipkaart> findall(){
        try {
            return session.createQuery("FROM OVChipkaart ", OVChipkaart.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        try {
            return session.createQuery("FROM OVChipkaart WHERE reiziger_id=" + reiziger.getReiziger_id(), OVChipkaart.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
