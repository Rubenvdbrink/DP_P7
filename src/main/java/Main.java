import java.sql.Date;

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

    public static void main(String[] args) {
        Session session = getSession();
        testDAOHibernate(session);
    }

    private static void testDAOHibernate(Session session) {
        AdresDAO adao = new AdresDAOHibernate(session);
        OVChipkaartDAO odao = new OVChipkaartDAOHibernate(session);
        ProductDAO pdao = new ProductDAOHibernate(session);
        ReizigerDAO rdao = new ReizigerDAOHibernate(session, adao, odao);

        /**
         * TestData
         */

        //Reiziger 1
        Reiziger r1 = new Reiziger((long) 1 ,"RG", "van der", "Brink", Date.valueOf("2002-02-02"));
        Adres a1 = new Adres((long) 123,"4102AW", "190", "Mendelssohnstraat", "Culemborg", (long) 1);
        OVChipkaart o1 = new OVChipkaart((long) 123, Date.valueOf("2021-09-09"), 2, 50.00, (long) 1);
        Product p1 = new Product(123, "GratisReizen", "U kunt nu voor altijd gratis reizen!", 0.01);

        r1.setAdres(a1);
        o1.getProducten().add(p1);
        r1.getOvChipkaarten().add(o1);

        //Reiziger 2
        Reiziger r2 = new Reiziger((long) 55 ,"RB", "van", "De slager", Date.valueOf("2002-02-03"));
        OVChipkaart o2 = new OVChipkaart((long) 1233, Date.valueOf("2021-09-09"), 2, 50.00, (long) 55);
        Product p2 = new Product(1234, "nieuwproduct", "nieuw product", 10.00);

        o2.getProducten().add(p2);
        p2.getOvChipkaarten().add(o2);
        r2.getOvChipkaarten().add(o2);


        /**
         * Reiziger deel 1
         */
        System.out.println("-----Reiziger (1)-----");
        //SAVE reiziger met bijbehorend adres en ovchipkaart en daarbij behorende producten
        System.out.println("Save reiziger= " + rdao.save(r1));
        //UPDATE reiziger
        r1.setAchternaam("Hendriks");
        System.out.println("Update reiziger= " + rdao.update(r1));
        //FindByGeboortedatum reiziger
        System.out.println("FindByGeboortedatum= " + rdao.findByGbdatum("2002-02-02"));
        //FindById reiziger
        System.out.println("FindById= " + rdao.findById(1));
        //Findall reiziger
        System.out.println("Findall= " + rdao.findAll());


        /**
         * Adres
         */
        System.out.println("\n-----Adres-----");
        //DELETE ADRES
        System.out.println("Delete adres= " + adao.delete(a1));
        //SAVE ADRES
        System.out.println("Save adres= " + adao.save(a1));
        //GetByReiziger Adres
        System.out.println("GetByReiziger= " + adao.findByReiziger(r1));
        //Findall Adres
        System.out.println("Findall= " + adao.findAll());

        /**
         * OVChipkaart
         */
        System.out.println("\n-----OVChipkaart-----");
        //DELETE OVchipkaart en daarbij behorende producten
        System.out.println("Delete ovchipkaart= " + odao.delete(o1));
        //SAVE OVChipkaart en daarbij behorende producten
        System.out.println("Save ovchipkaart= " + odao.save(o1));
        //GetByReiziger ovChipkaart
        System.out.println("GetByReiziger= " + odao.findByReiziger(r1));
        //Findall OVChipkaart
        System.out.println("Findall= " + odao.findall());


        /**
         * Product
         */
        System.out.println("\n------Product------");
        //SAVE Product
        System.out.println("Save product= " + pdao.save(p2));
        //UPDATE product
        p2.setNaam("super nieuw product");
        System.out.println("Update product= " + pdao.update(p2));
        //DELETE product
        System.out.println("Delete product= " + pdao.delete(p2));
        //FINDALL Product
        System.out.println("Findall= " + pdao.findall());

        /**
         * Reiziger deel 2
         */
        System.out.println("\n-----Reiziger (2)-----");
        //DELETE reiziger met bijbehorend adres en ovchipkaart
        System.out.println("Delete reiziger= " + rdao.delete(r1));
    }
}