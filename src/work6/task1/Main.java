package work6.task1;

import java.util.HashMap;
import java.util.Map;

/**
 * Головний клас для демонстрації роботи патернів.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрація роботи патернів ===");

        // 1. Singleton: отримуємо єдиний екземпляр DatabaseSchema
        DatabaseSchema schema1 = DatabaseSchema.getInstance();
        schema1.setName("MyDatabase");
        schema1.setVersion("1.0");
        schema1.setDescription("Тестова база даних");

        DatabaseSchema schema2 = DatabaseSchema.getInstance();
        System.out.println("schema1 == schema2: " + (schema1 == schema2));
        System.out.println("Один і той же об'єкт? " + (schema1.hashCode() == schema2.hashCode()));

        // 2. Builder: створюємо таблиці
        Table usersTable = new Table.Builder("users")
                .addField("id", "INT")
                .addField("name", "VARCHAR(100)")
                .addField("email", "VARCHAR(255)")
                .build();

        Table productsTable = new Table.Builder("products")
                .addField("id", "INT")
                .addField("title", "VARCHAR(200)")
                .addField("price", "DECIMAL(10,2)")
                .build();

        // 3. Додаємо таблиці до схеми
        schema1.addTable(usersTable);
        schema1.addTable(productsTable);

        // 4. Factory Method: створюємо валідатор
        QueryValidator validator = ValidatorFactory.createValidator("SQL");

        // 5. Валідація запитів
        String validQuery = "SELECT * FROM users";
        String invalidQuery = "SELECT * FROM non_existent_table";

        System.out.println("\nПеревірка валідного запиту:");
        boolean isValid1 = validator.validate(validQuery, schema1);
        System.out.println("Результат: " + isValid1);

        System.out.println("\nПеревірка невалідного запиту:");
        boolean isValid2 = validator.validate(invalidQuery, schema1);
        System.out.println("Результат: " + isValid2);

        // 6. Демонстрація роботи з таблицею
        System.out.println("\nДемонстрація роботи з таблицею:");
        Map<String, Object> row1 = new HashMap<>();
        row1.put("id", 1);
        row1.put("name", "Іван");
        row1.put("email", "ivan@example.com");
        usersTable.addRow(row1);

        // 7. Виведення інформації про схему
        System.out.println("\nІнформація про схему бази даних:");
        System.out.println("Назва: " + schema1.getName());
        System.out.println("Версія: " + schema1.getVersion());
        System.out.println("Опис: " + schema1.getDescription());
        System.out.println("Кількість таблиць: " + schema1.getTables().size());
    }
}
