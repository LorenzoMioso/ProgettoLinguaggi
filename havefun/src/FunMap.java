import value.ExpValue;
import value.FunValue;

import java.util.HashMap;
import java.util.Map;

public class FunMap {

    private final Map<String, FunValue> map = new HashMap<>();

    public boolean contains(String id) {
        return map.containsKey(id);
    }

    public FunValue get(String id) {
        return map.get(id);
    }

    public void update(String id, FunValue v) {
        map.put(id, v);
    }
}
