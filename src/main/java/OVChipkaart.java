import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {

    @Id
    private Long kaart_nummer;

    @Column
    private Date geldig_tot;
    private int klasse;
    private double saldo;
    private Long reiziger_id;

    @ManyToOne
    @JoinColumn (name = "reiziger_id", insertable = false, updatable = false)
    private Reiziger reiziger;

    @ManyToMany
    @JoinTable (name = "ov_chipkaart_product", joinColumns = @JoinColumn(name = "kaart_nummer"), inverseJoinColumns = @JoinColumn(name = "product_nummer"))
    private List<Product> producten = new ArrayList<>();

    public OVChipkaart() {}

    public OVChipkaart(Long kaart_nummer, Date geldig_tot, int klasse, double saldo, Long reiziger_id) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger_id;
    }

//    public boolean verwijderProduct(int productNummer) {
//        for (Product product : this.producten) {
//            if (product.getProduct_nummer() == productNummer) {
//                this.producten.remove(product);
//                return true;
//            }
//        }
//        return false;
//    }
    //not needed for this assignment

    public Long getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(Long kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Long getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(Long reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "kaart_nummer=" + kaart_nummer +
                ", geldig_tot=" + geldig_tot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reiziger_id=" + reiziger_id +
                ", producten=" + producten +
                '}';
    }
}
