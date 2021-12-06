package model;


import javafx.scene.paint.Color;

public abstract class BoardObject {
    private final Color color;

    public BoardObject(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
