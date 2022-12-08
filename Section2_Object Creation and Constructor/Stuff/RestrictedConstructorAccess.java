import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RestrictedConstructorAccess {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> addressClass = Address.class;
        Address address = null;

        Constructor<?>[] constructorr = addressClass.getDeclaredConstructors();
        for (Constructor<?> curr : constructorr) {
            if (!curr.isAccessible()) {
                curr.setAccessible(true);
                address = (Address) curr.newInstance("56A","Vietnam");
            }
        }
        System.out.println(address.toString());
    }
}


class Address {
    private String road;
    private String country;

    private Address() {

    }

    private Address(String road, String country) {
        this.road = road;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address [road=" + road + ", country=" + country + "]";
    }
}

class Person {
    private Integer age;
    private String name;
    private Address address;

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person(Integer age, String name, Address address) {
        this(age, name);
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person [age=" + age + ", name=" + name + ", address=" + address + "]";
    }
}