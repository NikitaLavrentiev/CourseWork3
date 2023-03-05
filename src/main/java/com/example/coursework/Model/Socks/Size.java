package com.example.coursework.Model.Socks;
/**
 * Список размеров для носок
 */
public enum Size {
    THIRTY(30),
    THIRTY_ONE(31),
    THIRTY_TWO(32),
    THIRTY_THREE(33),
    THIRTY_FOUR(34),
    THIRTY_FIVE(35),
    THIRTY_SIX(36),
    THIRTY_SEVEN(37),
    THIRTY_EIGHT(38),
    THIRTY_NINE(39),
    FORTY(40),
    FORTY_ONE(41),
    FORTY_TWO(42),
    FORTY_THREE(43),
    FORTY_FOUR(44);

    int size;
    Size(int size) {
        this.size = size;
    }
}
