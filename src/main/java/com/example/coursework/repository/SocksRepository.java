package com.example.coursework.repository;

import com.example.coursework.Model.Socks.Socks;
import com.example.coursework.Model.Socks.SocksBatch;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface SocksRepository {
    void save(SocksBatch socksBatch);

    int remove(SocksBatch socksBatch);

    Map<Socks, Integer> getAll();
}
