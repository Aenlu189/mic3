package mic3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Mic3ApplicationTests {

    @Test
    void contextLoads() throws Exception {
        ServiceEndpointTests tests = new ServiceEndpointTests();

        String baseUri = "http://localhost:8083/architect";

        tests.assignNonExistingFilePostRequest(baseUri + "/assign");

        tests.registerNotExistingArchitectPostRequest(baseUri + "/register");
        tests.registerNullPostRequest(baseUri + "/register");
        tests.registerExistingArchitectPostRequest(baseUri + "/register");

        tests.assignNullPostRequest(baseUri + "/assign");
        tests.assignNotExistingProjectPostRequest(baseUri + "/assign");
        tests.assignNotExistingArchitectPostRequest(baseUri + "/assign");
        tests.assignExistingProjectPostRequest(baseUri + "/assign");
        
        // Add the new check tests after assign tests are completed
        tests.checkNotExistingProjectPostRequest(baseUri + "/check");
        tests.checkExistingProjectPostRequest(baseUri + "/check");
    }

}
