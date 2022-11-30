import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PracticeWildcar {

    public static void testClass(Class<?>... clazz) {
        for (Class<?> eachClass : clazz) {
            System.out.printf("Class %s from package %s \n", eachClass.getSimpleName(), eachClass.getPackage() );

            Class<?>[] implementedInterface = eachClass.getInterfaces();
            for (Class<?> eachInterface : implementedInterface) {
                System.out.printf("class %s implemented interfaces : %s \n", eachClass.getName(), eachInterface.getSimpleName());
            }

            System.out.println("is Interface " + eachClass.isInterface());
            System.out.println("is Array " + eachClass.isArray());
            System.out.println("is Primitive " + eachClass.isPrimitive());
            System.out.println("is member Class " + eachClass.isMemberClass());

            System.out.print("\n\n");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;
        
        Map<Integer,String> hashMap = new HashMap<>();
        Class<?> hashMapClass = hashMap.getClass();

        Class<?> squareClass = Class.forName("Shape$Square");


        Class<?> objectClass = Object.class;

        testClass(stringClass, hashMapClass, squareClass, objectClass, Collection.class, boolean.class, int [][].class);
    }
}

interface IDraw {

}

class Shape {
    static class Square implements IDraw, Cloneable {

    }
}
