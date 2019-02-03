package OOP;

public class Child extends Parent{
    private Long id;

    public Child(Long id,String name) {
        this.id = id;
        setName(name);
    }
}
