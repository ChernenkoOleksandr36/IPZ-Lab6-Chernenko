package work6.task2;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Головний клас для демонстрації завдання.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ігровий простір ===\n");

        try {
            createTestConfigFile("game_config.json");

            // 1. Створюємо будівельника для JSON файлу
            GameSpaceBuilder jsonBuilder = new JSONGameSpaceBuilder("game_config.json");

            // 2. Створюємо директора
            GameSpaceDirector director = new GameSpaceDirector(jsonBuilder);

            // 3. Побудова ігрового простору
            RectangleGameSpace gameSpace1 = director.construct();

            // 4. Відображення результату
            System.out.println("\nРезультат побудови з JSON файлу:");
            gameSpace1.display();

            // 5. Демонстрація розширюваності
            System.out.println("\n--- Демонстрація розширюваності ---");
            GameSpaceBuilder randomBuilder = new RandomGameSpaceBuilder();
            director.setBuilder(randomBuilder);

            RectangleGameSpace gameSpace2 = director.construct();
            System.out.println("\nРезультат побудови випадкового простору:");
            gameSpace2.display();

            deleteTestConfigFile("game_config.json");

        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        }
    }

    private static void createTestConfigFile(String filename) throws IOException {
        String jsonConfig = """
            {
                "width": 1024,
                "height": 768,
                "background": "fantasy_forest.png",
                "objects": ["Player", "Dragon", "Castle", "Treasure", "NPC_Merchant"]
            }
            """;

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(jsonConfig);
            System.out.println("Створено тестовий файл конфігурації: " + filename);
        }
    }

    private static void deleteTestConfigFile(String filename) {
        try {
            java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(filename));
            System.out.println("Тестовий файл видалено: " + filename);
        } catch (IOException e) {
            System.out.println("Не вдалося видалити файл: " + filename);
        }
    }
}
