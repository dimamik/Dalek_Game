package model;

import enums.ObjectType;
import javafx.scene.paint.Color;

public abstract class BoardObject {
    protected boolean isMovable;
    private Color color;

    public BoardObject(Color color) {
        this.color = color;
        this.isMovable = true;
    }

    public BoardObject(Color color, boolean isMovable) {
        this.color = color;
        this.isMovable = isMovable;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract ObjectType getType();
}
