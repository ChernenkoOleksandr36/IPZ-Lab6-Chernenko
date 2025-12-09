package work6.task2;

/**
 * Інтерфейс, що визначає кроки побудови ігрового простору.
 */
public interface GameSpaceBuilder {
    void setWidth(int width);
    void setHeight(int height);
    void setBackground(String background);
    void addObject(String objectName);
    RectangleGameSpace build();
}
