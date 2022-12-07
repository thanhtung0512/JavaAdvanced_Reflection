import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // getConstructorsData(Person.class);

        Address address = (Address) createObjectFromArgument(Address.class, "56A","VietNam");
        Person person = (Person) createObjectFromArgument(Person.class, 18,"Tung",address);
        System.out.println(person.toString());

    }

    public static Object createObjectFromArgument(Class<?> clazz, Object... arg)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == arg.length) {
                return constructor.newInstance(arg);
            }
        }
        System.out.println("Cannot create object");
        return null;
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

    public  Address(String road, String country) {
        this.road = road;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address [road=" + road + ", country=" + country + "]";
    }
}

class Person {
    private int age;
    private String name;
    private Address address;

    public  Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public  Person(int age, String name, Address address) {
        this(age, name);
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person [age=" + age + ", name=" + name + ", address=" + address + "]";
    }
}