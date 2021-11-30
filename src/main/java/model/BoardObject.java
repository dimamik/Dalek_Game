package model;

import java.awt.*;

public abstract class BoardObject {

    private Color color;

    public BoardObject(Color color) {
        this.color = color;
    }

    public abstract void handleCollision();
}
