package work6.task2;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що описує прямокутний ігровий простір.
 */
public class RectangleGameSpace {
    private int width;
    private int height;
    private String background;
    private final List<String> objects;

    public RectangleGameSpace() {
        this.objects = new ArrayList<>();
    }

    public void display() {
        System.out.println("\n=== Ігровий простір ===");
        System.out.println("Розміри: " + width + "x" + height);
        System.out.println("Фон/текстура: " + background);
        System.out.println("Кількість об'єктів: " + objects.size());
        System.out.println("Об'єкти: " + String.join(", ", objects));
        System.out.println("=== Кінець відображення ===\n");
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<String> getObjects() {
        return new ArrayList<>(objects);
    }

    public void addObject(String objectName) {
        objects.add(objectName);
    }
}
