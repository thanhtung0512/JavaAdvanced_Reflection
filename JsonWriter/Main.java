import java.io.File;
import java.lang.reflect.Array;
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

        Actor actor1 = new Actor("Elijah Wood", new String[] { "Lord of the Rings", "The Good Son" });
        Actor actor2 = new Actor("Ian McKellen", new String[] { "X-Men", "Hobbit" });
        Actor actor3 = new Actor("Orlando Broom", new String[] { "Pirates of the Caribbean", "Kingdom of Heaven" });

        String[][] categories = { { "Action", "Adventure" }, { "Comedy", "Horror" } };
        Moviee movie = new Moviee("Lord of The Rings", 8, categories, new Actor[] { actor1, actor2, actor3 });
        try {
            String json = objectToJson(movie, 1);
            System.out.printf("%s", json);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Catch exception");
        }
    }

    public static String objectToJson(Object instance, int indent) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{" + "\n");

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
                stringBuilder.append(formatPrimitiveValue(field.getType(), field.get(instance)));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatStringValue(field.get(instance).toString()));
            } else if (field.getType().isArray()) {
                stringBuilder.append(arrayToJson(field.get(instance), indent + 1));
            } else {
                if (field.get(instance) != null)
                    stringBuilder.append(String.format(" %s ", objectToJson(field.get(instance), indent + 1)));
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

    public static String formatPrimitiveValue(Class<?> type, Object value) throws Exception {

        if (type.equals(int.class) || type.equals(boolean.class) ||
                type.equals(long.class) || type.equals(short.class)) {
            return value.toString();
        } else if (type.equals(double.class) || type.equals(float.class)) {
            return String.format("%.2f", value);
        }
        throw new RuntimeException(String.format("Type :%s is not supported  ", type.getName()));
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

    public static String arrayToJson(Object arrayInstance, int indent) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ \n");

        Class<?> arrayClass = arrayInstance.getClass();
        int length = Array.getLength(arrayInstance);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(arrayInstance, i);
            if (element.getClass().isArray()) {
                stringBuilder.append(returnIndent(indent) + arrayToJson(element, indent + 1));
            } else if (element.getClass().equals(String.class)) {
                stringBuilder.append(returnIndent(indent) + formatStringValue(element.toString()));
            } else if (element.getClass().isPrimitive()) {
                stringBuilder.append(returnIndent(indent) + formatPrimitiveValue(element.getClass(), element));
            } else {
                stringBuilder.append(returnIndent(indent) + objectToJson(element, indent + 1));
            }
            if (i != length - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append(returnIndent(indent));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
