package LambdasNStreams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Tester {
    public static void main(String[] args) {
        int a =1;
        int result = ~a;
        System.out.println(result);

        IntStream.rangeClosed(1,10).boxed().forEach(x-> System.out.println(x+"  "+~x));
        System.out.println(personList().stream().sorted(Comparator.comparing(Person::getAge).reversed()).toList());
//        System.out.println(personList().stream().sorted(Comparator.comparing(Person::getName)).toList());


        Supplier<Person> personSupplier = ()->personList().get(0);
        System.out.println(personSupplier.get());

        Consumer<Person> personConsumer = System.out::println;
        personConsumer.accept(personList().get(0));

        Function<Person, Integer> personVoidFunction = Person::getAge;
        System.out.println(personVoidFunction.apply(personList().getFirst()));

        Predicate<Person> personPredicate = person -> person.getAge()==37;
        System.out.println(personPredicate.test(personList().getFirst()));




    }

    public static List<Person> personList() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person("Amit", 37);
        personList.add(person);
        person = new Person("Ruchika", 32);
        personList.add(person);
        person = new Person("Arika", 4);
        personList.add(person);
        person = new Person("Arsh", 1);
        personList.add(person);
        return personList;
    }

    static class Person {
        public String name;
        public int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
