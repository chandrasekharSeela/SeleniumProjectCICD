package Util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;
/*import java.util.Arrays;*/
import java.util.List;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class DataCaptureFromJsonFiles {

    private static final String JSON_FILE_PATH = "//src//TestData//testData1.json";

    @DataProvider(name = "cartItemsProvider")
    public static Object[][] provideTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<cartItemWrapper> jsonData= objectMapper.readValue(
                    new File(System.getProperty("user.dir") + JSON_FILE_PATH), new TypeReference<>() {});
            Object[][] data = new Object[jsonData.size()][1];
            IntStream.range(0, jsonData.size()).forEach(i -> data[i][0] = convertListToObject(jsonData.get(i).getCartItems()));
            return data;
        } catch (IOException ignored) {
            return null;
        }
    }

    public static Object[] convertListToObject(List<String> list) {
        Object[] obj = new Object[list.size()];
        IntStream.range(0, list.size())
                .boxed()
                .forEach(i -> obj[i] = list.get(i));
        return obj;
    }

}

@SuppressWarnings("unused")
class cartItemWrapper {
    private List<String> cartItems;
    public List<String> getCartItems() {
        return cartItems;
    }


}
