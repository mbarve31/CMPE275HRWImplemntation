package madhura.sjsu.cache.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import madhura.sjsu.cache.repo.RecordRepoInterface;
import madhura.sjsu.cache.dom.Record;


/**
 * @author Madhura
 *
 * This class defines the REST Backend Services for  GET single record, GET complete list of records
 * and to PUT the record in map of type Record.
 *
 * The service produces and consumes a media type of "application/json"
 *
 * Access the InMemoryRecordRepo through the Interface RecordRepoInterface.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceCache {
    private final RecordRepoInterface repocache;

    /**
     * ResourceCache constructor creates a InMemoryRecordRepo instance.
     * @param rescache
     */
    public ResourceCache(RecordRepoInterface rescache) {
        this.repocache = rescache;
    }

    /**
     * Method to GET a single record for the corresponding key, from the InMemoryRecordRepo.
     *
     * The @Timed annotation is used for monitoring the execution time of a method
     * where MethodName is written as attribute name.
     *
     * @param key
     * @return valid corresponding record
     */
    @GET
    @Path("{key}")
    @Timed(name = "get-record")
    public Record get(@PathParam("key") LongParam key) {
        return repocache.get(key.get());
    }

    /**
     * Method to GET the list of all saved records form the InMemoryRecordmap.
     *
     * @return List of all Records
     */
    @GET
    @Timed(name = "view-all-records")
    public List<Record> getAll() {
        return repocache.getAll();
    }


    /**
     * Method to PUT/add a record to the InMemoryRecordRepo.
     *
     * @param key
     * @param value
     * @return HTTP Response 200 OK
     */
    @PUT
    @Path("{key}/{value}")
    @Timed(name = "add-record")
    public Response put(@PathParam("key") LongParam key, @PathParam("value") String value) {

        Record record = new Record();
        record.setKey(key.get());
        record.setValue(value);

        // Save record in the InMemoryRecordRepo.
        repocache.save(record);
        // Return a HTTP response 200 OK on successful addition of record
        return Response.status(200).build();
    }
}
