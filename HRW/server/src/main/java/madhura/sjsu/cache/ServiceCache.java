package madhura.sjsu.cache;

import java.util.concurrent.ConcurrentHashMap;

import madhura.sjsu.cache.dom.Record;
import madhura.sjsu.cache.repo.RecordRepoInterface;
import madhura.sjsu.cache.resources.ResourceCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import madhura.sjsu.cache.conf.ConfigCacheService;
import madhura.sjsu.cache.repo.InMemoryRecordRepo;

/**
 * @author Madhura
 *
 * This is the main entry point into the REST Server Stack(Dropwizard) service.
 * This class parse a configuration file in bootstrapping phase
 * Run servers and add them to the environment.
 */
public class ServiceCache extends Service<ConfigCacheService> {

    private final Logger cachelog = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) throws Exception {
        new ServiceCache().run(args);
    }

    /**
     * Bootstrap to Service subclass’s initialize method.
     * @param bootstrap
     */
    @Override
    public void initialize(Bootstrap<ConfigCacheService> bootstrap) {
        bootstrap.setName("cache-server");
    }

    /**
     * Runs the bootstrap's bundles with the given configuration and environment.
     * Create new resource instances and adds the Server Resources to environment.
     * @param configuration
     * @param environment
     * @throws Exception
     */
    @Override
    public void run(ConfigCacheService configuration,Environment environment) throws Exception {

        //Stores a Record type and it`s corresponding key in InMemoryMap (Record Repo)
        ConcurrentHashMap<Long, Record> recordmap = new ConcurrentHashMap<Long, Record>();
        RecordRepoInterface cache = new InMemoryRecordRepo(recordmap);

        //Add the server/Resource instances to environment.
        environment.addResource(new ResourceCache(cache));
        cachelog.info("resources is loaded");

    }
}
