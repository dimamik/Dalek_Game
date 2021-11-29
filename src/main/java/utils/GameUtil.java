package utils;

import model.Board;

public class GameUtil {
    private Board board;
    private PositionUtil positionUtil;

    public GameUtil() {
        //TODO Wstrzykiwanie
        this.board = new Board(1, 2 );
        this.positionUtil = new PositionUtil(board);
        this.setUpGame();
    }

    private void setUpGame(){

    }

    private void playRound(){

    }

    public void runGame(){
//        uses playRound and game logic
    }

}
