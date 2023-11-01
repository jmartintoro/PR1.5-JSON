package cat.iesesteveterradas.mp06.uf1.exemples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PR143GestioLlibreriaMain {

    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            File inputFile = new File("data/llibres_input.json");
            List<Map<String, Object>> llistaDeLlibres = objectMapper.readValue(inputFile, objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

            System.out.println(" - Original - ");
            printJson(llistaDeLlibres);

            System.out.println("\n - Modificacio - ");
            int id = 1;
            String keyModicar = "any";
            int nouValor = 1995;
            modificar(llistaDeLlibres, id, keyModicar, nouValor, inputFile);
                        
            printJson(llistaDeLlibres);

            System.out.println("\n - Afegir - ");
            int nouId = 4;
            String nouTitol = "Històries de la ciutat";
            String nouAutor = "Miquel Soler";
            int nouAny = 2022;
            afegir(llistaDeLlibres, nouId, nouTitol, nouAutor, nouAny, inputFile);
            printJson(llistaDeLlibres);

            System.out.println("\n - Eliminar - ");
            id = 2;
            eliminar(llistaDeLlibres, id, inputFile);
            printJson(llistaDeLlibres);

            System.out.println("\n - Guardar - ");
            File outputFile = new File("data/llibres_output.json");
            guardarJson(llistaDeLlibres, outputFile);
            
            
        } catch (Exception e) { e.printStackTrace(); }
        

    }

    private static void printJson(List<Map<String, Object>> llistaDeLlibres) {
        for (Map<String, Object> book : llistaDeLlibres ) {
                System.out.println(book);
            }
    }

    private static void modificar(List<Map<String, Object>> llistaDeLlibres, int id, String keyModicar, int nouValor, File inputFile) {
        for (Map<String, Object> book : llistaDeLlibres ) {
            if (book.get("id").equals(id)) {
                book.put(keyModicar, nouValor);
            }
        }
    }

    private static void afegir(List<Map<String, Object>> llistaDeLlibres, int nouId, String nouTitol, String nouAutor, int nouAny, File inputFile) {
        Map<String, Object> nouLlibre = new HashMap<String, Object>();
    
        nouLlibre.put("id", nouId);
        nouLlibre.put("títol", nouTitol);
        nouLlibre.put("autor", nouAutor);
        nouLlibre.put("any", nouAny);
    
        llistaDeLlibres.add(nouLlibre);
    }
    
    private static void eliminar(List<Map<String, Object>> llistaDeLlibres, int id, File inputFile) {
        Iterator<Map<String, Object>> iterator = llistaDeLlibres.iterator();
    
        while (iterator.hasNext()) {
            Map<String, Object> libro = iterator.next();
            if (libro.get("id").equals(id)) {
                iterator.remove();
            }
        }
    }
    
    private static void guardarJson(List<Map<String, Object>> llistaDeLlibres, File outputFile){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

            objectMapper.writeValue(outputFile, llistaDeLlibres);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
