package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import javax.inject.Named;

public class GameModule extends AbstractModule {


    @Provides
    @Named("colsNo")
    private int provideColsNo() {
        return 20;
    }

    @Provides
    @Named("rowsNo")
    private int provideRowsNo() {
        return 20;
    }

    @Provides
    @Named("cellSize")
    private int provideCellSizePx() {
        return 50;
    }
}
