package entities;

public class Senha {

    Integer id;
    String key;

    public Senha() {
    }



    public Senha(Integer id, String key) {
        this.id = id;
        this.key = key;
    }

    public Senha(Integer id) {
        this.id = id;
    }

    public Senha(String key, Integer id) {
        this.key = key;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
