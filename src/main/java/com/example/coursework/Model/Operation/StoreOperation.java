package com.example.coursework.Model.Operation;

import com.example.coursework.Model.Socks.SocksBatch;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
public class StoreOperation {

    private final LocalDateTime dateTime = LocalDateTime.now();
    private OperationType type;
    private SocksBatch socksBatch;
}