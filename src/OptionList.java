import java.util.HashMap;
import java.util.Map;
import org.json.*;
import kong.unirest.Unirest;
import kong.unirest.GetRequest;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

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

    private static HttpRequest<GetRequest> header(String string, String string2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'header'");
    }


}
