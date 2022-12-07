import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Method to get Constructor<?> objects

/* 
Class.getDeclaredConstructors()
    - Return all declared constructors within the class
    - Includes all the public and non public constructors

Class.getConstructors() 
    - Return only public constructor.
*/

public class Constructorr {
    public static void main(String[] args) {
        getConstructorsData(Person.class);
    }

    public static void getConstructorsData(Class<?> input) {
        Constructor<?>[] constructors = input.getDeclaredConstructors();
        System.out.printf("Class %s has %d declared constructors \n ", input.getSimpleName(), constructors.length);
        for (Constructor<?> curConstr : constructors) {
            System.out.printf("Constructor name: %s \n", curConstr.getName());
            Class<?>[] types = curConstr.getParameterTypes();
            List<String> typesName = Arrays.stream(types).map(type -> type.getName()).collect(Collectors.toList());
            System.out.println(typesName);
        }
    }
}

class Address {
    private String road;
    private String country;
}

class Person {
    private int age;
    private String name;
    private Address address;

    private Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private Person(int age, String name, Address address) {
        this(age,name);
        this.address = address;
    }
}