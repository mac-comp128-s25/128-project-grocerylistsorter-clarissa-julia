import java.util.List;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class ListOrganizer {

    private List<Item> produceList;
    private List<Item> meatSeafoodList;
    private List<Item> dairyEggsList;
    private List<Item> bakeryList;
    private List<Item> pantryStaplesList;
    private List<Item> frozenFoodList;
    private List<Item> snacksBeveragesList;
    private List<Item> householdGoodsList;
    private List<Item> personalCareList;
    private List<List<Item>> listOrganizer;

    //how best to do undo button - maybe list that is just strings


    public ListOrganizer(){
        produceList = new ArrayList<>();
        meatSeafoodList = new ArrayList<>();
        dairyEggsList = new ArrayList<>();
        bakeryList = new ArrayList<>();
        pantryStaplesList = new ArrayList<>();
        frozenFoodList = new ArrayList<>();
        snacksBeveragesList = new ArrayList<>();
        householdGoodsList = new ArrayList<>();
        personalCareList = new ArrayList<>();

        listOrganizer = Arrays.asList(produceList, meatSeafoodList, 
        dairyEggsList, bakeryList, pantryStaplesList, frozenFoodList, 
        snacksBeveragesList, householdGoodsList, personalCareList);   

    }

    public void addToList(Item item){
        String category = item.getCategory();
        getList(category).add(item);
    }

    public void remove(Item item){
        String category = item.getCategory();
        getList(category).remove(item);
    }

    public double totalCalculator(){
        double total = 0;
        for (List<Item> list : listOrganizer){
            for (Item item : list){
                total += item.getPrice();
            }
        }
        return total;
    }

    public List<Item> getList(String category){
        if (category=="Produce"){
            return produceList;
        }
        else if (category=="Meat & Seafood"){
            return meatSeafoodList;
        }
        else if (category=="Dairy & Eggs"){
            return dairyEggsList;
        }
        else if(category=="Bakery"){
            return bakeryList;
        }
        else if(category=="Pantry Staples"){
            return pantryStaplesList;
        }
        else if(category=="Frozen Food"){
            return frozenFoodList;
        }
        else if(category=="Snacks & Beverages"){
            return snacksBeveragesList;
        }
        else if(category=="Household Goods"){
            return householdGoodsList;
        }
        else if(category=="Personal Care Items"){
            return personalCareList;
        }
        else{
            throw new InvalidParameterException("Invalid category");
        }

    }

    // creates a string of the enture section
    public String fullListString() {
        StringBuilder output = new StringBuilder();
    
        addSection(output, "Produce", produceList);
        addSection(output, "Dairy & Eggs", dairyEggsList);
        addSection(output, "Bakery", bakeryList);
        addSection(output, "Pantry Staples", pantryStaplesList);
        addSection(output, "Frozen Food", frozenFoodList);
        addSection(output, "Snacks & Beverages", snacksBeveragesList);
        addSection(output, "Household Goods", householdGoodsList);
        addSection(output, "Personal Care Items", personalCareList);
    
        return output.toString();
    }
    
    // helper method for fullListString to create section headers and put on new lines
    private void addSection(StringBuilder output, String header, List<Item> items) {
        if (!items.isEmpty()) {
            output.append("== ").append(header).append(" ==\n");
            for (Item item : items) {
                output.append(item.getTitle()).append("\n");
            }
            output.append("\n");
        }
    }
    
    public void clearAll() {
        for (List<Item> list : listOrganizer) {
            list.clear();
        }
    }
    
    public void removeItem(String itemName) {
        for (List<Item> list : listOrganizer) {
            list.removeIf(item -> item.getName().equals(itemName));
        }
    }    

    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();
        for (List<Item> list : listOrganizer) {
            allItems.addAll(list);
        }
        return allItems;
    }
    

    // helps to update the visual of the table
    public String[][] toTableData() {
        List<Item> items = getAllItems();
        String[][] tableData = new String[items.size()][4]; 
    
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            tableData[i][0] = item.getName();
            tableData[i][1] = String.valueOf(item.getQuantity());
            tableData[i][2] = item.getCategory();
            tableData[i][3] = String.format("$%.2f", item.getPrice());
        }
    
        return tableData;
    }
    
}
