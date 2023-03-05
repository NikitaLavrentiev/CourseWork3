package com.example.coursework.Service;

import com.example.coursework.Model.Socks.Color;
import com.example.coursework.Model.Socks.Size;
import com.example.coursework.Model.Socks.SocksBatch;

public interface ValidationService {
    public boolean validate(SocksBatch socksBatch);

    boolean validate(Color color, Size size, int cottonMin, int cottonMax);

}
