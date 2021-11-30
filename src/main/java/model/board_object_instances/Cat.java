package model.board_object_instances;

import model.BoardObject;

import java.awt.*;

public class Cat extends BoardObject {

    private int[] horizontalMoves;
    private int[] verticalMoves;

    public Cat(Color color) {
        super(color);
    }


    @Override
    public void handleCollision() {

    }
}
