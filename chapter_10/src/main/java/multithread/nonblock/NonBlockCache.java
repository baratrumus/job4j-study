package multithread.nonblock;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockCache {
    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public Base add(Base model) throws OptimisticException {
        return cache.put(model.id, model);
    }

    public Base update(Base model) throws OptimisticException {
        return cache.computeIfPresent(model.id, (k, v) -> {
            if (v.version != model.version) {
                throw new OptimisticException("version changed");
            }
            model.version++;
            return model;
        });
    }

    public Base delete(Base model) throws OptimisticException {
        return cache.remove(model.id);
    }
}
