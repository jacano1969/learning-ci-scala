package learningci.chapter02.data;

public class Person {

    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        Person otherPerson = (Person) other;
        return this.name.equals(otherPerson.name);
    }


}
