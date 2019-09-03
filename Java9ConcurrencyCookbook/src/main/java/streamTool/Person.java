package streamTool;

import java.util.Comparator;
import java.util.Date;

public class Person implements Comparable<Person> {
    static Comparator<Person> comparator = Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName);

    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private int salary;
    private double coeficient;

    @Override
    public int compareTo(Person o) {
        /*int compareToLastName = this.getLastName().compareTo(o.getLastName());
        if (compareToLastName != 0) return compareToLastName;
        return this.getFirstName().compareTo(o.getFirstName());*/
        return comparator.compare(this, o);
    }

    @Override
    public boolean equals(Object obj) {
        return this.compareTo((Person) obj) == 0;
    }

    @Override
    public int hashCode() {
        return (this.getLastName() + this.getFirstName()).hashCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }
}
