package madhura.sjsu.cache.client;

/**
 * @author Madhura
 *
 * This Interface provices methods to get and put records in Cache Service
 */
public interface CacheServiceInterface {

    /**
     * This method can be used for getting record to corresponding key
     *
     * @param key
     */
    public String get(long key);

    /**
     * This method can be used for inserting a record into the inMemory map
     *
     * @param key
     * @param value
     */
    public void put(long key, String value);
}
