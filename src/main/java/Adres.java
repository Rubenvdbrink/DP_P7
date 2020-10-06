import javax.persistence.*;

@Entity
@Table(name = "adres")
public class Adres {

    @Id
    private Long adres_id;

    @Column
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    @OneToOne
    @JoinColumn (name = "adres_id")
    private Reiziger reiziger;

    public Adres() {}

    public Adres(Long id, String postcode, String huisnummer, String straat, String woonplaats) {
        this.adres_id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public Long getAdres_id() {
        return adres_id;
    }

    public void setAdres_id(Long adres_id) {
        this.adres_id = adres_id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String pc) {
        this.postcode = pc;
    }



    @Override
    public String toString() {
        return "Adres {" + woonplaats + " | " + postcode + '\'' + "}";
    }
}
