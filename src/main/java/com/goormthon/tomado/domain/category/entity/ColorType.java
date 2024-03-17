package com.goormthon.tomado.domain.category.entity;

public enum ColorType {
    RED("FFE4D9"),
    BLUE("E5F7FF"),
    GREEN("D9FCD1"),
    YELLOW("FFF2B1")
    ;

    private String colorCode;

    ColorType(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

}
