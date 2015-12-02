package madhura.sjsu.cache.client;

import madhura.sjsu.cache.client.HRWcache.HRWHash;

import java.util.ArrayList;

/**
 * @author Madhura
 *
 *  Client for Rendezvous Hash
 */
public class HRWCacheClient {

    public static void main(String[] args) {

        System.out.println("\nStarting Rendezvous(HRW) Cache Client: \n");

        //Nodes
        String cache1 = "http://localhost:3000";
        String cache2 = "http://localhost:3001";
        String cache3 = "http://localhost:3002";

        //Adding nodes to collection
        ArrayList collection = new ArrayList();
        collection.add(cache1);
        collection.add(cache2);
        collection.add(cache3);

        System.out.println("\nDistributed Caches: \n");

        HRWHash<String> HRWHash = new HRWHash(collection);

        for (int i = 1; i <= 10; i++) {
            //adding characters a to j to cache
            addCache(i, String.valueOf((char) (i + 96)), HRWHash);
        }


        System.out.println("\nRetrieve from Distributed-Caches\n");
        for (int i = 1; i <= 10; i++) {
            String value = (String) retriveCache(i, HRWHash);
        }

    }

    /**
     * This method adds record(key,value) to particular server and distributes it using HRW
     *
     * @param addKey
     * @param addValue
     * @param HRWHash
     */
    public static void addCache(int addKey, String addValue, HRWHash HRWHash) {

        String cacheUrl = (String) HRWHash.getCache(addKey);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        cache.put(addKey, addValue);
        System.out.println("Adding -->  Key " + addKey + " Value " + addValue + " to server: "+cacheUrl);
    }

    /**
     * This method retrives the values to corresponding keys given
     *
     * @param key
     * @param HRWHash
     * @return value of corresponding key
     */
    public static Object retriveCache(int key, HRWHash HRWHash) {

        String cacheUrl = (String) HRWHash.getCache(key);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        String value = cache.get(key);
        System.out.println("Retrieve -->  Key " + key + " Value " + value+ " from Server: "+cacheUrl);

        return value;
    }

}
