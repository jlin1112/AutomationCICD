package data;

import org.apache.commons.io.FileUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class dataReader {

    public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
        //json to string
        String jsonConent =  FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        //string to hashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonConent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }


}
