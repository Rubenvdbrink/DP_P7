import java.util.List;

public class AdresDAOHibernate implements AdresDAO {

    @Override
    public boolean save(Adres adres) {
        System.out.println(adres);
        return true;
    }

    @Override
    public boolean update(Adres adres) {
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<Adres> findAll() {
        return null;
    }
}
