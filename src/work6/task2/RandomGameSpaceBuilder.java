package work6.task2;

import java.util.Random;

/**
 * Альтернативна реалізація будівельника, яка створює випадковий ігровий простір.
 */
public class RandomGameSpaceBuilder implements GameSpaceBuilder {
    private RectangleGameSpace gameSpace;
    private Random random;

    public RandomGameSpaceBuilder() {
        this.gameSpace = new RectangleGameSpace();
        this.random = new Random();
        System.out.println("Створено RandomGameSpaceBuilder");
    }

    @Override
    public void setWidth(int width) {
        gameSpace.setWidth(width);
        System.out.println("Встановлено ширину: " + width);
    }

    @Override
    public void setHeight(int height) {
        gameSpace.setHeight(height);
        System.out.println("Встановлено висоту: " + height);
    }

    @Override
    public void setBackground(String background) {
        gameSpace.setBackground(background);
        System.out.println("Встановлено фон: " + background);
    }

    @Override
    public void addObject(String objectName) {
        gameSpace.addObject(objectName);
        System.out.println("Додано об'єкт: " + objectName);
    }

    @Override
    public RectangleGameSpace build() {
        System.out.println("Створення випадкового ігрового простору");

        setWidth(400 + random.nextInt(801));
        setHeight(300 + random.nextInt(601));

        String[] backgrounds = {"forest.png", "desert.jpg", "space.png", "dungeon.jpg"};
        setBackground(backgrounds[random.nextInt(backgrounds.length)]);

        String[] possibleObjects = {"Player", "Enemy", "Tree", "Rock", "Coin", "Chest", "Portal", "NPC"};
        int objectCount = 2 + random.nextInt(6);

        for (int i = 0; i < objectCount; i++) {
            String objectName = possibleObjects[random.nextInt(possibleObjects.length)] + (i + 1);
            addObject(objectName);
        }

        System.out.println("Випадковий ігровий простір побудовано");
        return gameSpace;
    }
}
