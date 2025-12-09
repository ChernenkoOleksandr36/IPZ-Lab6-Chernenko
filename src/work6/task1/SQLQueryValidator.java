package work6.task1;

/**
 * Конкретна реалізація валідатора SQL-запитів.
 * Перевіряє синтаксис, наявність таблиць та полів.
 */
public class SQLQueryValidator implements QueryValidator {
    @Override
    public boolean validate(String query, DatabaseSchema schema) {
        System.out.println("Валідатор SQLQueryValidator викликано для запиту: " + query);

        // Спрощена перевірка наявності ключових слів
        if (!query.toUpperCase().contains("SELECT") && !query.toUpperCase().contains("INSERT") &&
                !query.toUpperCase().contains("UPDATE") && !query.toUpperCase().contains("DELETE")) {
            System.out.println("Помилка: Запит не містить ключових слів SQL");
            return false;
        }

        // Спрощена перевірка наявності таблиць
        // Припустимо, що назва таблиці йде після FROM або INTO
        String[] words = query.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("FROM") || words[i].equalsIgnoreCase("INTO")) {
                if (i + 1 < words.length) {
                    String tableName = words[i + 1].replace(";", "").trim();
                    if (!schema.containsTable(tableName)) {
                        System.out.println("Помилка: Таблиця '" + tableName + "' не існує в схемі");
                        return false;
                    }
                }
            }
        }

        System.out.println("Запит валідний");
        return true;
    }
}
