import java.lang.reflect.*;

public class ArrayReader {

    public Object getArrayElement(Object array, int index) {
       /**
        * Complete your code here
        */ 
        Object element;
        if (index >= 0) {
            element = Array.get(array, index);
        }
        else {
            element = Array.get(array,Array.getLength(array) + index);

        }
        return element;
    }
}