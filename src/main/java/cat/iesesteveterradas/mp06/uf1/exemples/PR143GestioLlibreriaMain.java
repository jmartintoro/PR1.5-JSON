package cat.iesesteveterradas.mp06.uf1.exemples;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.Map;

public class PR143GestioLlibreriaMain {

    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<Map<String, Object>> llistaDeLlibres = objectMapper.readValue(new File("data/llibres_input.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

            for (Map<String, Object> map : llistaDeLlibres ) {
                System.out.println(map.get("títol"));
            }

        } catch (Exception e) { e.printStackTrace(); }
        

    }

}
