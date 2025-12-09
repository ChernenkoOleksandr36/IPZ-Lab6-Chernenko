package work6.task1;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton клас, що представляє схему бази даних.
 * Забезпечує єдиний екземпляр схеми для всієї програми.
 */
public class DatabaseSchema {
    private static DatabaseSchema instance;
    private String name;
    private String version;
    private String description;
    private final Map<String, Table> tables;

    private DatabaseSchema() {
        tables = new HashMap<>();
        System.out.println("Створено єдиний екземпляр DatabaseSchema");
    }

    /**
     * Глобальна точка доступу до єдиного екземпляра DatabaseSchema.
     * @return єдиний екземпляр DatabaseSchema
     */
    public static synchronized DatabaseSchema getInstance() {
        if (instance == null) {
            instance = new DatabaseSchema();
        }
        return instance;
    }

    /**
     * Додає таблицю до схеми бази даних.
     * @param table таблиця для додавання
     */
    public void addTable(Table table) {
        tables.put(table.getName(), table);
        System.out.println("Таблицю '" + table.getName() + "' додано до схеми");
    }

    /**
     * Видаляє таблицю зі схеми бази даних.
     * @param tableName назва таблиці для видалення
     */
    public void removeTable(String tableName) {
        if (tables.remove(tableName) != null) {
            System.out.println("Таблицю '" + tableName + "' видалено зі схеми");
        }
    }

    /**
     * Отримує таблицю за назвою.
     * @param tableName назва таблиці
     * @return об'єкт Table або null, якщо таблиця не знайдена
     */
    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    /**
     * Перевіряє, чи існує таблиця в схемі.
     * @param tableName назва таблиці
     * @return true, якщо таблиця існує
     */
    public boolean containsTable(String tableName) {
        return tables.containsKey(tableName);
    }

    // Геттери та сеттери
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Table> getTables() {
        return new HashMap<>(tables); // повертаємо копію
    }
}
