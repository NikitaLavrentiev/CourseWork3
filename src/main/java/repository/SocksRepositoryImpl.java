package repository;

import Model.Socks;
import Model.SocksBatch;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class SocksRepositoryImpl implements SocksRepository {
    private Map<Socks, Integer> socksMap;
    @Override

    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getQuantity());
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
        }
    }

    @Override
    public int remove(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            int quantity = socksBatch.getQuantity();
            if (quantity > socksBatch.getQuantity()) {
                socksMap.replace((socks), socksMap.get(socks) - socksBatch.getQuantity());
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                return quantity;
            }
        }
        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }
}
