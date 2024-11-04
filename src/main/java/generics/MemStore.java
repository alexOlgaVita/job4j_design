package generics;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (findById(model.getId()) == null) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        if (findById(id) != null) {
            storage.replace(id, model);
            result = true;
        }
        return result;
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
