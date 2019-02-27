package OOP;

public class Parent implements Cloneable {
    private String name;
    private Integer age;
    private Child child;
    private CloneModel cloneModel;

    public CloneModel getCloneModel() {
        return cloneModel;
    }

    public void setCloneModel(CloneModel cloneModel) {
        this.cloneModel = cloneModel;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Parent parent = (Parent) super.clone();
        if (parent.getCloneModel() != null)
            parent.cloneModel = (CloneModel) parent.cloneModel.clone();
        return parent;
    }
}
