package guice;

import com.google.inject.AbstractModule;
import views.BoardView;

public class StateModule extends AbstractModule {

    BoardView boardView;

    public StateModule(BoardView boardView) {
        super();
        this.boardView = boardView;
    }
}
