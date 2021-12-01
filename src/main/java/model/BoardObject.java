package model;


import javafx.scene.paint.Color;

public abstract class BoardObject {

    private Color color;

    public BoardObject(Color color) {
        this.color = color;
    }

    public abstract void handleCollision();

    public Color getColor() {
        return color;
    }
}
