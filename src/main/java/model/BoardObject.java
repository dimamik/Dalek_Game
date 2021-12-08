package model;


import enums.ObjectType;
import javafx.scene.paint.Color;

public abstract class BoardObject {
    private Color color;

    protected boolean isMovable;

    public BoardObject(Color color) {
        this.color = color;
        this.isMovable = true;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) { this.color = color; }

    public abstract ObjectType getType();


}
