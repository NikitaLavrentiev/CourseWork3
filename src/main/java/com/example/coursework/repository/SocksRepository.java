package com.example.coursework.repository;

import com.example.coursework.Model.Socks.Socks;
import com.example.coursework.Model.Socks.SocksBatch;

import java.util.List;
import java.util.Map;
public interface SocksRepository {
    void save(SocksBatch socksBatch);

    int remove(SocksBatch socksBatch);

    List<SocksBatch> getList();

    Map<Socks, Integer> getAll();

    void replace(List<SocksBatch> socksBatchList);
}
