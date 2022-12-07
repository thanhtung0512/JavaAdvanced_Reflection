public class Main {
    public static void main(String[] args) {
        // way1. get from instance of class 
        String s = "String";
        Class<?> classString = s.getClass();
        System.out.println(classString.getSimpleName());        
        
        // way2. .class after the type name
        Class<String> clazzString = String.class;
        System.out.println(clazzString.getSimpleName());

        Class booleanType = boolean.class;
        System.out.println(booleanType.getName()); // can use with primitive types


        // way3. Class.forName() method, least safest way
        Class<?> clString;
        try {
            clString = Class.forName("java.lang.String");
            System.out.println(clString.getSimpleName());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

            // can use for static nested class

            try {
                Class<?> engineType = Class.forName("Car$Engine");
                System.out.println(engineType.getName());
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
    }
}

class Car {
    static class Engine {

    }
}
