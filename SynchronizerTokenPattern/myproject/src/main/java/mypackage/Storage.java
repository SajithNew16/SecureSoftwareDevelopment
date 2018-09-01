package mypackage;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private static Storage storageObj = new Storage();
    private Map<String, String> storageMap = new HashMap<String, String>();

    private Storage() {
    }

    public static Storage getInstance() {
        return storageObj;
    }

    public void addItem(String sessionId, String token) {
        storageMap.put(sessionId, token);
    }

    public String getItem(String sessionId) {
        for (Map.Entry<String, String> entry : storageMap.entrySet()) {
            if (sessionId.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void print() {
        for (Map.Entry<String, String> entry : storageMap.entrySet()) {
            System.out.println("entry.getKey() " + entry.getKey() + " entry.getValue() " + entry.getValue());
        }
    }

}