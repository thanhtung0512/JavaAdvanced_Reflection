import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Test {

    public static boolean testAge(int age) {
        return age > 3;
    }

    public static void main(String[] args) {
        // Predicate <Integer> predicate = Test::testAge;
        // System.out.println(predicate.test(3));
        Integer i = new Integer(3);
        Integer ii = new Integer(4);
        Object o = new Object();
        System.out.println(i.getClass() + " " + ii.getClass() + " " + o);
    }
}
