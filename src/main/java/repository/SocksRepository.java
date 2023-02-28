package repository;

import Model.Socks;
import Model.SocksBatch;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface SocksRepository {
    void save(SocksBatch socksBatch);

    int remove(SocksBatch socksBatch);

    Map<Socks, Integer> getAll();
}
