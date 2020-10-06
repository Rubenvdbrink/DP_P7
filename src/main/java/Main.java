import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static final SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testDAOHibernate();
    }

    private static void testDAOHibernate() {
        Session session = getSession();
        System.out.println(session);

        AdresDAO adao = new AdresDAOHibernate();
        OVChipkaartDAO odao;
        ProductDAO pdao;
        ReizigerDAO rdao;

        Adres new_adres = new Adres((long) 555, "4102AW", "190", "Mendelssohnstraat", "Culemborg");
        adao.save(new_adres);
    }
}