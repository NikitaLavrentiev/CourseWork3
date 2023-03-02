package com.example.coursework.Model.Operation;

import com.example.coursework.Model.Socks.SocksBatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreOperation {

    private final LocalDateTime dateTime = LocalDateTime.now();
    private OperationType type;
    private SocksBatch socksBatch;
}