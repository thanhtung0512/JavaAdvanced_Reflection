import java.lang.reflect.Field;

public class ClassField {
    public static <T> void printDeclaredFieldsInfo(Class<? extends T> clazz, T curInstance) throws Exception {
        System.out.printf("Class %s has fields: \n", clazz.getName());
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.printf("Field: %s,  type: %s , Field is synthetic : %b , Field value: %s  \n ", field.getName(),
                    field.getType(),
                    field.isSynthetic(),
                    field.get(curInstance));
        }
        System.out.println("\n");
    }

    public static Field getFieldObject(Class<?> clazz) throws Exception {
        Field fieldObject = clazz.getDeclaredField("category");
        return fieldObject;
    }

    public static void main(String[] args) throws Exception {
        // printDeclaredFieldsInfo(String.class);
        // printDeclaredFieldsInfo(Movie.MovieStats.class);

        // printDeclaredFieldsInfo(Category.class);

        // Field field = getFieldObject(Movie.class);
        // System.out.println(field.getBoolean(field));

        Movie lordOfTheRings = new Movie("Lord of the Rings",
                true,
                Category.ACTION,
                12.99);

        printDeclaredFieldsInfo(lordOfTheRings.getClass(), lordOfTheRings);


        // Field miniumPriceStaticField = Movie.class.getDeclaredField("MINIUM_PRICE");
        // System.out.println(miniumPriceStaticField.get(null));

    }
}

class Movie {
    public static final double MINIUM_PRICE = 10.99;

    private String name;
    private boolean isReleased;
    private Category category;
    private double actualPrice;

    public Movie(String name, boolean isReleased, Category category, double actualPrice) {
        this.name = name;
        this.isReleased = isReleased;
        this.category = category;
        this.actualPrice = actualPrice;
    }

}

enum Category {
    ADVENTURE,
    ACTION,
    COMEDY
}
