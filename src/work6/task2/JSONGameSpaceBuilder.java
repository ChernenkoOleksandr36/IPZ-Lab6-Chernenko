package work6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Конкретна реалізація будівельника, яка зчитує параметри з JSON файлу.
 */
public class JSONGameSpaceBuilder implements GameSpaceBuilder {
    private RectangleGameSpace gameSpace;
    private String configFilePath;

    public JSONGameSpaceBuilder(String configFilePath) {
        this.gameSpace = new RectangleGameSpace();
        this.configFilePath = configFilePath;
        System.out.println("Створено JSONGameSpaceBuilder для файлу: " + configFilePath);
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
        try {
            loadConfigurationFromFile();
        } catch (IOException e) {
            System.out.println("Помилка читання файлу конфігурації: " + e.getMessage());
            setDefaultValues();
        }
        System.out.println("Побудовано ігровий простір");
        return gameSpace;
    }

    private void loadConfigurationFromFile() throws IOException {
        System.out.println("Завантаження конфігурації з файлу: " + configFilePath);

        String content = new String(Files.readAllBytes(Paths.get(configFilePath)));

        if (content.contains("\"width\"")) {
            String widthStr = extractValue(content, "width");
            setWidth(Integer.parseInt(widthStr));
        }

        if (content.contains("\"height\"")) {
            String heightStr = extractValue(content, "height");
            setHeight(Integer.parseInt(heightStr));
        }

        if (content.contains("\"background\"")) {
            String background = extractValue(content, "background");
            setBackground(background);
        }

        if (content.contains("\"objects\"")) {
            String objectsPart = content.substring(content.indexOf("\"objects\":") + 10);
            objectsPart = objectsPart.substring(0, objectsPart.indexOf("]"));

            String[] objects = objectsPart.split(",");
            for (String obj : objects) {
                if (obj.contains("\"")) {
                    String objectName = obj.replaceAll("[\"\\[\\]]", "").trim();
                    if (!objectName.isEmpty()) {
                        addObject(objectName);
                    }
                }
            }
        }
    }

    private String extractValue(String json, String fieldName) {
        String field = "\"" + fieldName + "\":";
        int startIndex = json.indexOf(field) + field.length();
        int endIndex = json.indexOf(",", startIndex);
        if (endIndex == -1) endIndex = json.indexOf("}", startIndex);

        String value = json.substring(startIndex, endIndex).trim();
        return value.replaceAll("\"", "");
    }

    private void setDefaultValues() {
        System.out.println("Встановлення значень за замовчуванням");
        setWidth(800);
        setHeight(600);
        setBackground("default_background.png");
        addObject("Player1");
        addObject("Enemy1");
    }
}
