import java.util.HashMap;

/**
 * @author Madhura
 */
public class MyNode {
    String name;
    HashMap<String,String > data;

    public MyNode(String server) {
        this.name=server;
        this.data = new HashMap<>();
    }

    /**
     * Method to add data to in-memory map
     * @param key
     * @param value
     */
    public void addData(String key,String value){
        data.put(key,value);
    }

    /**
     * Method to read data from in-memory map
     * @param key
     * @return
     */
    public String readData(String key){
        return data.get(key);
    }

    /**
     * Method to display all the data in in-memory map
     */
    public void displayAllData() {
        System.out.printf(name+" data : {");
        for (String s:data.values()) {
            System.out.printf(" "+s+",");
        }
        System.out.println("}");
    }
}
