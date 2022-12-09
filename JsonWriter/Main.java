import java.io.File;
import java.lang.reflect.Field;

class Address {
    private final String street;
    private final short apartment;

    public Address(String street, short apartment) {
        this.street = street;
        this.apartment = apartment;
    }
}

class Company {
    private final String name;
    private final String city;
    private final Address address;

    public Company(String name, String city, Address address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

}

class Person {
    private final String name;
    private final boolean employed;
    private final int age;
    private final float salary;
    private final Company job;

    private final Person manager;

    public Person(String name, boolean employed, int age, float salary, Company job, Person manager) {
        this.name = name;
        this.employed = employed;
        this.age = age;
        this.salary = salary;
        this.job = job;
        this.manager = manager;
    }

}

public class Main {
    public static void main(String[] args) throws Exception {
        Company company = new Company("Netflix", "NYC", new Address("56A", (short) 14));
        Person person = new Person("Tung", true, 18, 1000,
                company, new Person("Manager", true, 22, 2000, company, null));
        int indent = 1;
        try {
            System.out.println(objectToJson(person, indent));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Has some exception");
        }
    }

    public static String objectToJson(Object instance, int indent) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(returnIndent(indent - 1) + "{" + "\n");

        Field[] fields = instance.getClass().getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            if (field.isSynthetic()) {
                continue;
            }
            stringBuilder.append(returnIndent(indent));
            field.setAccessible(true);
            stringBuilder.append(formatStringValue(field.getName()));
            stringBuilder.append(": ");
            if (field.getType().isPrimitive()) {
                stringBuilder.append(formatPrimitiveValue(field, instance));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatStringValue(field.get(instance).toString()));
            } else {
                if (field.get(instance) != null) 
                    stringBuilder.append(String.format("\n %s ", objectToJson(field.get(instance), indent + 1)));
            }

            if (i != fields.length - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
            i++;
        }
        stringBuilder.append(returnIndent(indent - 1) + "}");
        return stringBuilder.toString();
    }

    public static String formatPrimitiveValue(Field field, Object instance) throws Exception {
        Class<?> fieldType = field.getType();
        if (fieldType.equals(int.class) || fieldType.equals(boolean.class) ||
                fieldType.equals(long.class) || fieldType.equals(short.class)) {
            return field.get(instance).toString();
        } else if (fieldType.equals(double.class) || fieldType.equals(float.class)) {
            return String.format("%.2f", field.get(instance));
        }
        throw new RuntimeException(String.format("Type :%s is not supported  ", fieldType.getName()));
    }

    public static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }

    public static String returnIndent(int indent) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < indent; i++) {
            stringBuilder.append("\t");
        }
        return stringBuilder.toString();
    }
}
