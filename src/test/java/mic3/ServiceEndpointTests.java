package mic3;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceEndpointTests {
    void registerNullPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();
        String requestBody = null;

        Response response = client.execute(uri, requestBody);
        assertEquals(-1, response.getCode());
    }

    void registerNotExistingArchitectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();
        String requestBody = "{\"id\": 1}";

        Response response = client.execute(uri, requestBody);
        assertEquals(0, response.getCode());
    }

    void registerExistingArchitectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        // First registration
        String requestBody = "{\"id\": 1}";

        // Second registration with same ID
        Response response = client.execute(uri, requestBody);
        assertEquals(4, response.getCode());
    }

    void assignNullPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();
        String requestBody = null;

        Response response = client.execute(uri, requestBody);
        assertEquals(-1, response.getCode());
    }

    void assignNonExistingFilePostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();
        String requestBody = "{\"id\": 1, \"projectNumber\": \"P_1\"}";

        Response response = client.execute(uri, requestBody);
        assertEquals(5, response.getCode());
    }

    void assignNotExistingProjectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        String assignBody = "{\"id\": 1, \"projectNumber\": \"P_1\"}";
        Response response = client.execute(uri, assignBody);
        assertEquals(0, response.getCode());
    }

    void assignNotExistingArchitectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();
        String requestBody = "{\"id\": 40, \"projectNumber\": \"P_1\"}";

        Response response = client.execute(uri, requestBody);
        assertEquals(6, response.getCode());
    }

    void assignExistingProjectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        String assignBody = "{\"id\": 1, \"projectNumber\": \"P_1\"}";

        Response response = client.execute(uri, assignBody);
        assertEquals(7, response.getCode());
    }

    void checkNotExistingProjectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();
        String requestBody = "{\"projectNumber\": \"P_5\"}";

        Response response = client.execute(uri, requestBody);
        assertEquals(8, response.getCode());
    }


    void checkExistingProjectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();
        String requestBody = "{\"projectNumber\": \"P_1\"}";

        Response response = client.execute(uri, requestBody);
        assertEquals(0, response.getCode());
    }
}
