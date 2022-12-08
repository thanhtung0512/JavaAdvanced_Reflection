import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import WebSeverr.WebSever;
import init.ServerConfiguration;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        initServerConfiguration();
        WebSever webSever = new WebSever();
        webSever.startSever();
    }

    public static void initServerConfiguration() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        Constructor<?> constructor = ServerConfiguration.class.getDeclaredConstructor(int.class,String.class);
        constructor.setAccessible(true);
        constructor.newInstance(8080,"Good day!");
       
    }
}
