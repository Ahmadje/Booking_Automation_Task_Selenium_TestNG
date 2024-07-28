package utils;

import io.restassured.path.json.JsonPath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JSONUtils {
    private final String jsonFilePath;
    private static final ThreadLocal<FileReader> reader = new ThreadLocal();

    public JSONUtils(String jsonFilePath) throws FileNotFoundException {
        this.jsonFilePath = jsonFilePath;
        this.initializeReader();
        List<List<Object>> attachments = new ArrayList();
        List testDataFileAttachment = null;

        try {
            testDataFileAttachment = Arrays.asList("Test Data", "JSON", new FileInputStream(jsonFilePath));
        } catch (FileNotFoundException var5) {
        }

        attachments.add(testDataFileAttachment);
    }

    private String cleanJsonPath(String jsonPath) {
        return jsonPath.startsWith("x.") ? jsonPath.replace("x.", "") : jsonPath;
    }

    public String getTestData(String jsonPath) throws FileNotFoundException {
        Object testData = this.getTestData(this.cleanJsonPath(jsonPath), DataType.STRING);
        return testData != null ? String.valueOf(testData) : null;
    }

    public List<?> getTestDataAsList(String jsonPath) throws FileNotFoundException {
        Object testData = this.getTestData(this.cleanJsonPath(jsonPath), DataType.LIST);
        return testData != null ? (List) testData : null;
    }

    public Map<?, ?> getTestDataAsMap(String jsonPath) throws FileNotFoundException {
        Object testData = this.getTestData(this.cleanJsonPath(jsonPath), DataType.MAP);
        return testData != null ? (Map) testData : null;
    }

    private Object getTestData(String jsonPath, DataType dataType) throws FileNotFoundException {
        Object testData = null;
        this.initializeReader();
        switch (dataType) {
            case STRING:
                testData = JsonPath.from(reader.get()).getString(jsonPath);
                break;
            case LIST:
                testData = JsonPath.from(reader.get()).getList(jsonPath);
                break;
            case MAP:
                testData = JsonPath.from(reader.get()).getMap(jsonPath);
        }

        return testData;
    }

    private void initializeReader() throws FileNotFoundException {
        reader.set(new FileReader(this.jsonFilePath));
    }

    public static enum DataType {
        STRING,
        LIST,
        MAP;

        private DataType() {
        }
    }
}
