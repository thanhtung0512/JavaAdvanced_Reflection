import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticeWildcar {

    public static void testClass(Class<?>... clazz) {
        for (Class<?> eachClass : clazz) {
            System.out.printf("Class %s from package %s \n", eachClass.getTypeName(), eachClass.getPackage() );

            Class<?>[] implementedInterface = eachClass.getInterfaces();
            for (Class<?> eachInterface : implementedInterface) {
                System.out.printf("class %s implemented interfaces : %s \n", eachClass.getName(), eachInterface.getSimpleName());
            }

            System.out.println("is Interface " + eachClass.isInterface());
            System.out.println("is Array " + eachClass.isArray());
            System.out.println("is Primitive " + eachClass.isPrimitive());
            System.out.println("is member Class " + eachClass.isMemberClass());
            System.out.println("is instance " + eachClass.isInstance(eachClass));

            System.out.print("\n\n");
        }
    }


    public static void printSuperClass(Class<?> clazz)  {
        if (clazz == null) return ;
        System.out.println(clazz.getSuperclass().getSimpleName());
    }

    public static void main(String[] args) throws ClassNotFoundException {
        List<String> stringList = new ArrayList<>();
        
        Class<String> stringClass = String.class;
        
        Map<Integer,String> hashMap = new HashMap<>();
        Class<?> hashMapClass = hashMap.getClass();

        Class<?> squareClass = Class.forName("Shape$Square");


        Class<?> objectClass = Object.class;
        Shape shape = new Shape();
        
        Class<?>  shapeSuperClass = shape.getClass().getSuperclass();
        // System.out.println(shapeSuperClass.getName());
        // DRY DONT REPEAT YOURSELF
        System.out.println("super class of primitive type " + int.class.getSuperclass()); // super class of primitive type is null
        testClass(stringClass, hashMapClass, squareClass, objectClass, Collection.class, boolean.class, int [][].class, Shape.class, double.class);
    }
}

interface IDraw {

}

class Shape {
    public static int kind ;
    static class Square implements IDraw, Cloneable {

    }
}
