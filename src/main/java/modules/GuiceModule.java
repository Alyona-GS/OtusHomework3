package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import models.Pet;

public class GuiceModule extends AbstractModule {
    public GuiceModule() {}

    @Singleton
    @Provides
    public Pet getPet() {
        return new Pet();
    }
}
