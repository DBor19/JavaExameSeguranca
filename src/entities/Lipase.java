package entities;

public class Lipase {
    Integer id;
    String uL; 

    public Lipase() {
    }

    public Lipase(Integer id, String uL) {
        this.id = id;
        this.uL = uL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuL() {
        return uL;
    }

    public void setuL(String uL) {
        this.uL = uL;
    }
}
