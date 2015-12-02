package madhura.sjsu.cache.repo;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import madhura.sjsu.cache.dom.Record;


/**
 * @author Madhura
 *
 * This class implements methods to stores and retrives recods from InMemory Map
 * which acts as a Repository for storing records.
 */
public class InMemoryRecordRepo implements RecordRepoInterface {

    // InMemory map of cache stores (Key, Value)pair corresponds to (Key, Record)
    private final ConcurrentHashMap<Long, Record> inMemoryMap;

    /**
     * Constructor
     * @param record
     */
    public InMemoryRecordRepo(ConcurrentHashMap<Long, Record> record) {
        inMemoryMap = record;
    }

    /**
     * Method to save the record to repository.
     *
     * @precondition checks for NotNull precondition and if satisfied then save the record.
     * @param newRecord: a instance of record to be create in the repo
     * @return the saved newRecord
     */
    @Override
    public Record save(Record newRecord) {
        checkNotNull(newRecord, "newRecord instance must not be null");
        inMemoryMap.putIfAbsent(newRecord.getKey(), newRecord);

        return newRecord;
    }

    /**
     * Method to get a record for the corresponding key provided.
     *
     * @precondition checks if key>0, if successful then searches the inMemorymap to get corresponding record.
     * @param key
     * @return corresponding record to provided key.
     */
    @Override
    public Record get(Long key) {
        checkArgument(key > 0,
                "Key was %s but expected greater than zero value", key);
        return inMemoryMap.get(key);
    }

    /**
     * Method to return all records in repo.
     *
     * @return List for all records
     */
    @Override
    public List<Record> getAll() {
        return new ArrayList<Record>(inMemoryMap.values());
    }
}
