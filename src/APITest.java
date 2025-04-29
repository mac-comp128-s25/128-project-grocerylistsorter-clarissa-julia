import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import java.util.Map;


public class APITest {
    public static void main(String[] args) throws Exception {
        HttpResponse<JsonNode> response = Unirest.get("https://api-to-find-grocery-prices.p.rapidapi.com/amazon?query=milk")
            .header("x-rapidapi-key", "52616f87aamsh0800cd10f770123p1199acjsnba1b79044cb2")
            .header("x-rapidapi-host", "api-to-find-grocery-prices.p.rapidapi.com")
            .asJson();
            
        // extract json string and make it look nice
        String itemList = response.getBody().toPrettyString();

        // Map<String, Double> optionList = OptionList.getOptionList(itemList);
        // for (Map.Entry<String, Double> entry : optionList.entrySet()){
        //     System.out.print(entry.getKey());
        //     System.out.println(" $" + entry.getValue());
            
        // }

        Item testItem = new Item("Eggs", 6, 8.75, "Dairy & Eggs");
        Item testItem2 = new Item("Bread", 2, 4, "Bakery");
        Item testItem3 = new Item("Shampoo", 1, 6, "Personal Care Items");

        ListOrganizer listOrganizer = new ListOrganizer();
        listOrganizer.addToList(testItem);
        listOrganizer.addToList(testItem2);
        listOrganizer.addToList(testItem3);
        System.out.println(listOrganizer.fullListString());
    
    }
}