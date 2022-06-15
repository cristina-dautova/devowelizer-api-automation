package rest.input.get;

import com.casumo.rest.client.ResponseParameters;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import rest.BaseTest;

import static org.testng.Assert.assertEquals;

@Feature("Casumo test")
public class GetInputTest extends BaseTest {

    @Test(dataProvider = "dataProvider")
    public void getStringWithoutVowels(String pathParameter) {
        ResponseParameters response = getResponse(pathParameter);

        assertEquals(response.getBody(), removeVowelsFromString(pathParameter));
        assertEquals(response.getStatusCode().intValue(), 200);
    }

    private String removeVowelsFromString(String word) {
        return word.replaceAll("[aeiouAEIOUаиоуыэуеёюя]", "");
    }


}
