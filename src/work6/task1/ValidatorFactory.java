package work6.task1;

/**
 * Фабрика для створення валідаторів запитів (Factory Method).
 */
public class ValidatorFactory {
    /**
     * Створює валідатор за типом.
     * @param type тип валідатора
     * @return об'єкт QueryValidator
     */
    public static QueryValidator createValidator(String type) {
        System.out.println("Створюємо валідатор типу: " + type);
        if ("SQL".equalsIgnoreCase(type)) {
            return new SQLQueryValidator();
        }
        // Можна додати інші типи валідаторів
        throw new IllegalArgumentException("Невідомий тип валідатора: " + type);
    }
}
