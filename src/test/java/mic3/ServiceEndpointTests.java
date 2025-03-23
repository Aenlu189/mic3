package mic3;

import com.fasterxml.jackson.databind.ObjectMapper;
import mic3.part4.ProjectNumber;

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

        RegisterRequest request = new RegisterRequest();
        request.setId(1);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        Response response = client.execute(uri, jsonString);
        assertEquals(0, response.getCode());
    }

    void registerExistingArchitectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        RegisterRequest request = new RegisterRequest();
        request.setId(1);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        Response response = client.execute(uri, jsonString);
        assertEquals(4, response.getCode());
    }

    void assignNullPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        Response response = client.execute(uri, null);
        assertEquals(-1, response.getCode());
    }

    void assignNonExistingFilePostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        AssignRequest request = new AssignRequest();
        request.setId(1);
        request.setProjectNumber(ProjectNumber.P_1);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        Response response = client.execute(uri, jsonString);
        assertEquals(5, response.getCode());
    }

    void assignNotExistingProjectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        AssignRequest request = new AssignRequest();
        request.setId(1);
        request.setProjectNumber(ProjectNumber.P_1);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        Response response = client.execute(uri, jsonString);
        assertEquals(0, response.getCode());
    }

    void assignNotExistingArchitectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        AssignRequest request = new AssignRequest();
        request.setId(100);
        request.setProjectNumber(ProjectNumber.P_1);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        Response response = client.execute(uri, jsonString);
        assertEquals(6, response.getCode());
    }

    void assignExistingProjectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        AssignRequest request = new AssignRequest();
        request.setId(1);
        request.setProjectNumber(ProjectNumber.P_1);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        Response response = client.execute(uri, jsonString);
        assertEquals(7, response.getCode());
    }

    void checkNotExistingProjectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        CheckRequest request = new CheckRequest();
        request.setProjectNumber(ProjectNumber.P_5);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        Response response = client.execute(uri, jsonString);
        assertEquals(8, response.getCode());
    }

    void checkExistingProjectPostRequest(String uri) throws Exception {
        HttpPostWrapper client = new HttpPostWrapper();

        CheckRequest request = new CheckRequest();
        request.setProjectNumber(ProjectNumber.P_1);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        Response response = client.execute(uri, jsonString);
        assertEquals(0, response.getCode());
    }
}
