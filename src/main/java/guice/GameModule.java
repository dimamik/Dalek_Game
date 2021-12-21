package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import model.Board;
import service.FxmlLoaderService;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FxmlLoaderService.class).asEagerSingleton();
        bind(Board.class).in(Singleton.class);
    }

    @Provides
    @Named("cols")
    private int provideColsNo() {
        return 20;
    }

    @Provides
    @Named("rows")
    private int provideRowsNo() {
        return 20;
    }

    @Provides
    @Named("cellSize")
    private int provideCellSizePx() {
        return 40;
    }

    @Provides
    @Named("daleksNo")
    private int provideDaleksNo() {
        return 20;
    }
}
