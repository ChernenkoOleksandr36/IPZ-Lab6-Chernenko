package work6.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Клас, що представляє реляційну таблицю.
 * Містить назву, поля (стовпці) та рядки даних.
 */
public class Table {
    private final String name;
    private final Map<String, String> fields; // ключ - назва поля, значення - тип даних
    private final List<Map<String, Object>> rows; // список рядків

    private Table(String name) {
        this.name = name;
        this.fields = new HashMap<>();
        this.rows = new ArrayList<>();
    }

    /**
     * Додає поле (стовпець) до таблиці.
     * @param fieldName назва поля
     * @param dataType тип даних
     */
    public void addField(String fieldName, String dataType) {
        fields.put(fieldName, dataType);
        System.out.println("Поле '" + fieldName + "' (" + dataType + ") додано до таблиці '" + name + "'");
    }

    /**
     * Видаляє поле з таблиці.
     * @param fieldName назва поля для видалення
     */
    public void removeField(String fieldName) {
        if (fields.remove(fieldName) != null) {
            System.out.println("Поле '" + fieldName + "' видалено з таблиці '" + name + "'");
        }
    }

    /**
     * Додає рядок до таблиці.
     * @param rowData дані рядка у вигляді Map
     */
    public void addRow(Map<String, Object> rowData) {
        rows.add(new HashMap<>(rowData));
        System.out.println("Рядок додано до таблиці '" + name + "'");
    }

    /**
     * Видаляє рядок за індексом.
     * @param index індекс рядка
     */
    public void removeRow(int index) {
        if (index >= 0 && index < rows.size()) {
            rows.remove(index);
            System.out.println("Рядок з індексом " + index + " видалено з таблиці '" + name + "'");
        }
    }

    /**
     * Отримує всі рядки таблиці.
     * @return список рядків
     */
    public List<Map<String, Object>> getRows() {
        return new ArrayList<>(rows);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getFields() {
        return new HashMap<>(fields);
    }

    /**
     * Внутрішній Builder для створення таблиць (патерн Builder).
     */
    public static class Builder {
        private final String name;
        private final Map<String, String> fields = new HashMap<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder addField(String fieldName, String dataType) {
            fields.put(fieldName, dataType);
            return this;
        }

        public Table build() {
            Table table = new Table(name);
            table.fields.putAll(fields);
            return table;
        }
    }
}
