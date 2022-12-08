import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

class Character {
    public Character() {
        System.out.println("Instantiating Character");
    }
}

class BackgroundImage {
    public BackgroundImage() {
        System.out.println("Instantiating background image");
    }
}

class Background {
    BackgroundImage backgroundImage;

    public Background(BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        System.out.println("Instantiating background");
    }
}

class Enemy {
    public Enemy() {
        System.out.println("Instantiating enemy");
    }
}

class Game {
    private Character character;
    private Background background;
    private Enemy enemy;

    public Game(Character character, Background background, Enemy enemy) {
        this.character = character;
        this.background = background;
        this.enemy = enemy;
    }

    public void run() {
        System.out.println("Running");
    }
}

public class Main {
    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Game game = createObjectRecursively(Game.class);

        game.run();
    }

    public static <T> T createObjectRecursively(Class<T> clazz)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Constructor<?> firstConstructor = getFirstConstructor(clazz);

        List<Object> constructorArguments = new ArrayList<>();

        for (Class<?> eachClass : firstConstructor.getParameterTypes()) {
            constructorArguments.add(createObjectRecursively(eachClass));
        }

        return (T) firstConstructor.newInstance(constructorArguments.toArray());
    }

    private static Constructor<?> getFirstConstructor(Class<?> clazz) {

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        if (constructors.length == 0) {
            throw new IllegalStateException(
                    String.format("No constructor has been found for class %s ", clazz.getSimpleName()));
        }

        return constructors[0];
    }
}
