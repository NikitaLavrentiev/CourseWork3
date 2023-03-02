package com.example.coursework.Model.Socks;

import com.example.coursework.Model.Socks.Socks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksBatch {
    private int quantity;
    private Socks socks;
}
