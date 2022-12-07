import java.util.*;
import java.util.function.Predicate;

public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES = Arrays.asList("com.sun.", "java", "javax", "jdk",
            "org.w3c", "org.xml");

    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {
        PopupTypeInfo popupTypeInfo = new PopupTypeInfo();

        /** Complete the Code **/

        popupTypeInfo.setPrimitive(inputClass.isPrimitive())
                .setInterface(inputClass.isInterface())
                .setEnum(inputClass.isEnum())
                .setName(inputClass.getSimpleName())
                .setJdk(isJdkClass(inputClass))
                .addAllInheritedClassNames(getAllInheritedClassNames(inputClass));

        return popupTypeInfo;
    }

    /*********** Helper Methods ***************/

    public static boolean isJdkClass(Class<?> inputClass) {
        return JDK_PACKAGE_PREFIXES.stream().anyMatch(packagePrefix -> inputClass.getPackage() == null ||
                inputClass.getPackage().getName().startsWith(packagePrefix));

        /**
         * Complete the code
         * Hint: What does inputClass.getPackage() return when the class is a primitive
         * type?
         **/
    }

    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
        String[] inheritedClassNames;
        if (inputClass.isInterface()) {
            inheritedClassNames = Arrays.stream(inputClass.getInterfaces())
                    .map(Class::getSimpleName)
                    .toArray(String[]::new);
        } else {
            Class<?> superClass = inputClass.getSuperclass();
            if (superClass != null) {
                inheritedClassNames = new String[] { superClass.getSimpleName() };
            } else
                inheritedClassNames = null;
        }
        return inheritedClassNames;
        /**
         * Complete the code
         * Hints: What does inputClass.getSuperclass() return when the inputClass
         * doesn't inherit from any class?
         * What does inputClass.getSuperclass() return when the inputClass is a primitve
         * type?
         * 
         **/
    }
}