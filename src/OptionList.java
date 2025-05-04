import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.json.*;

public class OptionList {
    /**
     * Returns a Map of the search result name and price to be converted to Vector
     * @param prettyString
     * @return Map optionList
     */
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

    /**
     * Converts the itemList Map to a Vector to be used by the GUI
     * @param optionList
     * @return Vector optionVector
     */
    public static Vector<String> getOptionVector(Map<String, Double> optionList){
        Vector<String> optionVector = new Vector<>();
        for (String itemName : optionList.keySet()){
            optionVector.add(itemName);
        }
        return optionVector;
    }
}
