package rest;

import com.casumo.rest.client.BaseRestClient;
import com.casumo.rest.client.ResponseParameters;
import com.casumo.rest.client.RestAssuredClientManager;
import org.testng.annotations.DataProvider;

import java.util.*;

import static com.casumo.utils.PropertiesReader.getBaseUrl;

public class BaseTest {

    protected BaseRestClient restClient;
    protected Map<String, String> headers = new HashMap<>();
    protected Map<String, String> endpoint = new LinkedHashMap<>();

    public BaseTest() {
        restClient = new RestAssuredClientManager();
        headers.put("Connection", "keep-alive");
    }

    protected ResponseParameters getResponse(String endpointValue) {
        endpoint.put("endpoint", endpointValue);
        return restClient.get(getBaseUrl(),
                endpoint, null, null, null, null);
    }

    protected String generate128CharString() {
        StringBuilder s= new StringBuilder("a");
        for (int i = 0; i < 128; i++) {
            s.append("a");
        }

        return s.toString();
    }

    /**
     * TODO:
     * store test data in different file
     */

    @DataProvider(name = "dataProvider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"input"}, {"EEEinpEutAAA"}, {":@input123"}, {"кристина"}, {"u"}, {"u u"},
                {generate128CharString()}};
    }
}
