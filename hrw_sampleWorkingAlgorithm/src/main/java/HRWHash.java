
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Madhura
 *
 * This class implements the Rendezvous Hash Algorithm
 */
public class HRWHash<N> {

    private final HashFunction hf;
    private final HashMap<Integer, N> listOfNodes = new HashMap<Integer, N>();
    /**
     * Constructor
     * @param nodes
     */
    public HRWHash(Collection<N> nodes) {

        this.hf = Hashing.sha512();
        for (N node : nodes) {
            addServer(node);
        }

        System.out.println("\nList of Nodes: " + listOfNodes);
    }

    /**
     * This method adds a node in the list of nodes.
     *
     * @param node
     */
    public void addServer(N node) {

        int hash = this.hf.newHasher().putString(node.toString(), Charset.defaultCharset()).hash().asInt();
        listOfNodes.put(hash, node);

    }

    /**
     * This method removes the node from the list
     *
     * @param node
     */
    public void removeServer(N node) {

        listOfNodes.remove(hf.newHasher().putString(node.toString(), Charset.defaultCharset()).hash().asInt());

    }

    /**
     * This method returns the value of maximum node where the record is to be placed.
     *
     * @param key
     * @return maxNode
     */
    public N getCache(Object key) {

        if (listOfNodes.isEmpty()) {
            return null;
        }

        Integer maxHash = Integer.MIN_VALUE;
        N maxNode = null;

        for (Map.Entry<Integer, N> node : listOfNodes.entrySet()) {

            int hashValue = hashFunction(key, node);

            if (hashValue > maxHash) {
                maxHash = hashValue;
                maxNode = node.getValue();
            }
        }
        return maxNode;
    }

    /**
     * This method calculates the hash value.
     *
     * @param key
     * @param node
     * @return hashValue
     */
    private int hashFunction(Object key, Map.Entry<Integer, N> node) {

        int hashValue = hf.newHasher()
                .putString(key.toString(), Charset.defaultCharset())
                .putString(node.getValue().toString(), Charset.defaultCharset())
                .hash()
                .asInt();

        return hashValue;
    }

}
