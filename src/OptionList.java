import java.util.HashMap;
import java.util.Map;
import org.json.*;

public class OptionList {

    public static Map<String, Double> getOptionList(String prettyString){
        Map<String, Double> optionList = new HashMap<>();
        JSONObject parser = new JSONObject(prettyString);
        JSONArray productsArray = parser.getJSONArray("products");

        for (Object product : productsArray){
            String name = ((JSONObject) product).getString("name");
            String stringPrice = ((JSONObject) product).getString("price");
            stringPrice = stringPrice.substring(1);
            Double price = Double.parseDouble(stringPrice);
            optionList.put(name, price);
        }
        return optionList;


       

        



    }
    
    
}
