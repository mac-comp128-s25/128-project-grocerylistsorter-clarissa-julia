import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public class APITest {
    public static void main(String[] args) throws Exception {
        HttpResponse<JsonNode> response = Unirest.get("https://api-to-find-grocery-prices.p.rapidapi.com/amazon?query=milk")
            .header("x-rapidapi-key", "52616f87aamsh0800cd10f770123p1199acjsnba1b79044cb2")
            .header("x-rapidapi-host", "api-to-find-grocery-prices.p.rapidapi.com")
            .asJson();
            
        // extract json string and make it look nice
        System.out.println(response.getBody().toPrettyString());
    }
}