package mic3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class Mic3ApplicationTests {

    @Test
    void contextLoads() throws Exception {

        // Deleting the json file to ease testing
        Path filePath = Paths.get("src/main/resources/Architects.json");

        try {
            boolean deleted = Files.deleteIfExists(filePath);
            System.out.println("Architects.json deleted: " + deleted);
        } catch (IOException e) {
            System.out.println("Failed to delete Architects.json: " + e.getMessage());
        }

        ServiceEndpointTests tests = new ServiceEndpointTests();

        String baseUri = "http://localhost:8080/architects";

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
