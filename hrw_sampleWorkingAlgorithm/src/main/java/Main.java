import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Madhura
 */
public class Main {
    public static void main(String[] args) {

        //Creating new servers
        ArrayList<MyNode> list = new ArrayList<>();
        MyNode server1 = new MyNode("server1");
        MyNode server2 = new MyNode("server2");
        MyNode server3 = new MyNode("server3");

        list.add(server1);
        list.add(server2);
        list.add(server3);


        HRWHash<MyNode> hrw = new HRWHash<>(list);

        //inserting data
        putData("1","data1",hrw);
        putData("2","data2",hrw);
        putData("3","data3",hrw);
        putData("4","data4",hrw);
        putData("5","data5",hrw);
        putData("6","data6",hrw);
        putData("7","data7",hrw);
        putData("8","data8",hrw);
        putData("9","data9",hrw);
        putData("10","data10",hrw);



        server1.displayAllData();
        server2.displayAllData();
        server3.displayAllData();

        System.out.printf("-------------------------------------------------------\n");

        MyNode server;

        server = getNode("1", hrw);
        System.out.println("server : " +server.name+" data :" + server.readData("1"));

        server = getNode("2", hrw);
        System.out.println("server : " +server.name+" data :" + server.readData("2"));

        server = getNode("3", hrw);
        System.out.println("server : " +server.name+" data :" + server.readData("3"));

        server = getNode("4", hrw);
        System.out.println("server : " +server.name+" data :" + server.readData("4"));

        server = getNode("5", hrw);
        System.out.println("server : " +server.name+" data :" + server.readData("5"));



    }

    /**
     * This method returns values according to keys passed
     * @param key
     * @param hrw
     * @return
     */
    private static MyNode getNode(String key, HRWHash<MyNode> hrw) {
        return hrw.getCache(key);
    }

    /**
     * This method adds data to the cache.
     * @param key
     * @param value
     * @param hrwHash
     */
    private static void putData(String key,String value,HRWHash<MyNode> hrwHash) {

        MyNode server = hrwHash.getCache(key);
        server.addData(key,value);

    }
}
