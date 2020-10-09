
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAOHibernate implements ProductDAO {

    private Session session;

    public ProductDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Product product) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.save(product);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.update(product);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.delete(product);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findall() {
        try {
            return session.createQuery("FROM Product", Product.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
