import value.ExpValue;

import java.util.HashMap;
import java.util.Map;

public class Conf {

    private Map<String, ExpValue<?>> map = new HashMap<>();

    public boolean contains(String id) {
        return map.containsKey(id);
    }

    public ExpValue<?> get(String id) {
        return map.get(id);
    }

    public void update(String id, ExpValue<?> v) {
        map.put(id, v);
    }
    public String toString(){
        return map.toString();
    }

    public void clear(){ map.clear();}

    public void putAll(Map map){
        map.putAll(map);
    }

    public Map<String, ExpValue<?>> getMap() {
        return map;
    }
}
