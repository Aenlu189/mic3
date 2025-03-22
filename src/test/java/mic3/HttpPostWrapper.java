package mic3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpPostWrapper {
    Response execute(String uri, String requestBody) {
        Response response = new Response();
        response.setCode(-1);

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(uri);
            
            // Only set entity if requestBody is not null
            if (requestBody != null) {
                StringEntity input = new StringEntity(requestBody);
                input.setContentType("application/json");
                post.setEntity(input);
            }
            
            // Add content type header regardless of body
            post.setHeader("Content-Type", "application/json");
            
            System.out.println("Sending request to: " + uri);
            System.out.println("Request body: " + requestBody);
            
            CloseableHttpResponse httpResponse = client.execute(post);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            
            System.out.println("HTTP status code: " + statusCode);

            if (statusCode != 200) {
                System.out.println("Non-200 status code received: " + statusCode);
                return response; // this will return -1
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuilder responseStr = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                responseStr.append(line);
            }
            
            String responseBody = responseStr.toString();
            System.out.println("Response body: " + responseBody);

            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(responseBody, Response.class);
            System.out.println("Parsed response code: " + response.getCode());

            rd.close();
            httpResponse.close();
            client.close();

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            response.setCode(-1);
        }
        return response;
    }
}
