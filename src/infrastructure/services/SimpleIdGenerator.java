package infrastructure.services;

import usecases.ports.IdGenerator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimpleIdGenerator implements IdGenerator {
    private final Map<String, Integer> counters = new HashMap<>();
    
    private final Set<String> validPrefixes = Set.of("CLI", "PROD", "PED", "RES");

    @Override
    public String nextId(String prefix) {
        if (!validPrefixes.contains(prefix)) {
            throw new IllegalArgumentException("Prefijo no válido: " + prefix);
        }
        int current = counters.getOrDefault(prefix, 0);
        current++;
        counters.put(prefix, current);
        return prefix + "-" + String.format("%03d", current);
    }
}
