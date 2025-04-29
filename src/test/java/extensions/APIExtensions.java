package extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import modules.GuiceModule;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class APIExtensions implements BeforeEachCallback, BeforeAllCallback {
    private Injector injector;

    @Override
    public void beforeEach(ExtensionContext context) {
        injector = Guice.createInjector(new GuiceModule());
        injector.injectMembers(context.getTestInstance().get());
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .build();
    }
}
