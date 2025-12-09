package work6.task2;

/**
 * Директор, який керує процесом побудови ігрового простору.
 */
public class GameSpaceDirector {
    private GameSpaceBuilder builder;

    public GameSpaceDirector(GameSpaceBuilder builder) {
        this.builder = builder;
        System.out.println("Створено GameSpaceDirector з будівельником: " + builder.getClass().getSimpleName());
    }

    public RectangleGameSpace construct() {
        System.out.println("\nПочаток побудови ігрового простору...");
        System.out.println("Завершення побудови...");
        return builder.build();
    }

    public void setBuilder(GameSpaceBuilder builder) {
        this.builder = builder;
        System.out.println("Змінено будівельника на: " + builder.getClass().getSimpleName());
    }
}
