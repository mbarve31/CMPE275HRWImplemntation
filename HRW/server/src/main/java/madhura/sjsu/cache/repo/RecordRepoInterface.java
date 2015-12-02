package madhura.sjsu.cache.repo;

import java.util.List;
import madhura.sjsu.cache.dom.Record;

/**
 * @author Madhura
 *
 * This is a Interface for Record Repository.
 * 
 * This Repository is a mediator between the domain and data mapping layers
 * which act like an in-memory domain for object collection.
 */
public interface RecordRepoInterface {

    /**
     * Save a new record in the repo
     * @param newRecord: a instance of record to be create in the repo
     * @return an instance of record
     */
    Record save(Record newRecord);

    /**
     * Retrieve an existing record by key
     * @param key
     * @return a instance of record
     */
    Record get(Long key);

    /**
     * Retrieve all records
     * 
     * @return a list of records
     */
    List<Record> getAll();

}
