import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Test {

    public static boolean testAge(int age) {
        return age > 3;
    }

    public static void main(String[] args) {
        // Predicate <Integer> predicate = Test::testAge;
        // System.out.println(predicate.test(3));

        Constructor<?>[] constructor = HashMap.class.getDeclaredConstructors(); 
        for (Constructor<?> cur : constructor) {
            System.out.println(cur.getParameterTypes());
        }
    }    
}