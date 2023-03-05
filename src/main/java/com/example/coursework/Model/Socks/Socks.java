package com.example.coursework.Model.Socks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    private Color color;
    private Size size;
    private int cottonPart;

}
