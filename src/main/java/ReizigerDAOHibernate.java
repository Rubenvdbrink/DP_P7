import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class ReizigerDAOHibernate implements  ReizigerDAO {

    private Session session;
    private AdresDAO adao;
    private OVChipkaartDAO odao;

    public ReizigerDAOHibernate(Session sess, AdresDAO adao, OVChipkaartDAO odao) {
        this.session = sess;
        this.adao = adao;
        this.odao = odao;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.save(reiziger);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        Transaction transaction = this.session.beginTransaction();
        try {
            //update reiziger
            session.update(reiziger);
//            //update adres van reiziger
//            if (reiziger.getAdres() != null) {
//                session.update(reiziger.getAdres());
//            }
//            //update ovchipkaarten van reiziger
//            if (!reiziger.getOvChipkaarten().isEmpty()) {
//                for (OVChipkaart ovChipkaart : reiziger.getOvChipkaarten()) {
//                    session.update(ovChipkaart);
//                }
//            }
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        Transaction transaction = this.session.beginTransaction();
        try {

            //delete adres van reiziger
            if (reiziger.getAdres() != null) {
                session.delete(reiziger.getAdres());
            }

            //delete ovchipkaarten van reiziger
            if (!reiziger.getOvChipkaarten().isEmpty()) {
                for (OVChipkaart ovChipkaart : reiziger.getOvChipkaarten()) {
                    session.delete(ovChipkaart);
                }
            }

            //delete reiziger
            session.delete(reiziger);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try {
            return session.createQuery("FROM Reiziger WHERE reiziger_id =" + id, Reiziger.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
//        System.out.println(Date.valueOf(datum));
        try {
//             List<Reiziger> reizigers = session.createQuery(
//                     "FROM Reiziger WHERE geboortedatum =" + Date.valueOf(datum), Reiziger.class)
//                     .getResultList();
            Query<Reiziger> query = session.createQuery("from Reiziger where geboortedatum = :datum", Reiziger.class);
            query.setParameter("datum", Date.valueOf(datum));
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            return session.createQuery("FROM Reiziger ", Reiziger.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
