package OOP;


public class TestClone {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child(111L,"c");
        CloneModel cloneModel = new CloneModel();
        parent.setCloneModel(cloneModel);
        parent.setChild(child);
        parent.setName("abc");
        try {
            Parent parent1 = (Parent) parent.clone();
            System.out.println(parent.getChild() == parent1.getChild()); //true 引用变量不克隆
            System.out.println(parent == parent1); // false
            System.out.println(parent.getCloneModel() == parent1.getCloneModel()); // 引用类型重写clone方法，可以达到深度克隆
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
