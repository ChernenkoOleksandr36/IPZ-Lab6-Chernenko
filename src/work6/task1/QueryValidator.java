package work6.task1;

/**
 * Інтерфейс для валідатора запитів.
 * Визначає метод перевірки запиту.
 */
public interface QueryValidator {
    /**
     * Перевіряє запит до бази даних.
     * @param query запит для перевірки
     * @param schema схема бази даних
     * @return true, якщо запит валідний
     */
    boolean validate(String query, DatabaseSchema schema);
}
