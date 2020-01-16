package xyz.kida.magiccardseeker.utils;

public enum ColorEnum {
    BLACK("Black"),
    GREEN("Green"),
    RED("Red"),
    BLUE("Blue"),
    WHITE("White");

    private String color;

    ColorEnum(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
