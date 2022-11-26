package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slotegrator.enums.EndPoints;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class RestAssuredExtension extends BasicClassTest implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static final Lock LOCK = new ReentrantLock();
    private static volatile boolean started = false;

    @Override
    public void beforeAll(ExtensionContext context) {
        LOCK.lock();
        try {
            if (!started) {
                started = true;
                RestAssured.baseURI = EndPoints.BASE_URI.getUrl();
                Specifications.installSpecification(Specifications.requestSpec(RestAssured.baseURI));
                AllureRestAssured allureRestAssured = new AllureRestAssured();
                allureRestAssured.setRequestTemplate("http-request.ftl");
                allureRestAssured.setResponseTemplate("http-response.ftl");
                RestAssured.replaceFiltersWith(
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter(),
                        allureRestAssured);
                context.getRoot().getStore(GLOBAL).put("any unique name", this);
            }
        } finally {
            LOCK.unlock();
        }
    }

    @Override
    public void close() {

    }
}
