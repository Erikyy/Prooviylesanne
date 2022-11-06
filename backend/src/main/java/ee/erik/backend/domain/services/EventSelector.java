package ee.erik.backend.domain.services;

/**
 * This enum is used for retrieving events either before or after today
 */
public enum EventSelector {
    /**
     * Returns events before today
     */
    Before,
    /**
     * Returns events after today
     */
    After,
}
