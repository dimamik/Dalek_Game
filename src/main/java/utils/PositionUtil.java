package utils;

import model.Board;
import model.Vector2D;

public class PositionUtil {
    private Board board;
    private CollisionHandler collisionHandler;

    public PositionUtil(Board board) {
        this.board = board;
        //TODO Wstrzykiwanie
        this.collisionHandler = new CollisionHandler(board);
    }

    public void changePosition(Vector2D destination) {
        //TODO
        //move boardObject from boardCell with position <?>
        //to boardCell with position <destination>
    }
}
