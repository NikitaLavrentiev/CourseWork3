package com.example.coursework.Model.Socks;

/**
 * Список цветов для носок
 */
public enum Color {
    WHITE("Белый"),
    RED("Красный"),
    BLUE("Синий"),
    YELLOW("Желтый"),
    BLACK("Чёрный");

    String color;

    Color(String color) {
        this.color = color;
    }
}
