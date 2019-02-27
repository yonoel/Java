package OOP;

public class Child extends Parent{
    private Long id;
    private Integer name;
    public Child(Long id,String name) {
        this.id = id;
        setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setName(Integer name) {
        this.name = name;
    }
}
