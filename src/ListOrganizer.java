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
    
    /**
     * Creates a ListOrganizer object that stores the nine category lists
     */
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

    /**
     * Adds the item to the corresponding category list
     * @param item
     */
    public void addToList(Item item){
        String category = item.getCategory();
        getList(category).add(item);
    }

    /**
     * Removes the item to the corresponding category list
     * @param item
     */
    public void remove(Item item){
        String category = item.getCategory();
        getList(category).remove(item);
    }

    /**
     * Calculates the total of all items in all lists
     * @return double total
     */
    public double totalCalculator(){
        double total = 0;
        for (List<Item> list : listOrganizer){
            for (Item item : list){
                total += item.getPrice();
            }
        }
        return (total*100)/100.0;
    }

    /**
     * Retrieves the list of the corresponding category
     * @param category
     * @return List of corresponding category
     */
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

    /**
     * Creates a formatted String of all items and the total
     * @return String of all items and total
     */
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
        addSection(output, "Total");
        return output.toString();
    }
    
    /**
     * Helper method for fullListString to create section headers and put on new lines
     * @param output
     * @param header
     * @param items
     */
    private void addSection(StringBuilder output, String header, List<Item> items) {
        if (!items.isEmpty()) {
            output.append("== ").append(header).append(" ==\n");
            for (Item item : items) {
                output.append(item.getTitle()).append("\n");
            }
            output.append("\n");
        }
    }

    //overloaded method
    private void addSection(StringBuilder output, String header) {
        output.append("== ").append(header).append(" ==\n");
        output.append("$"+totalCalculator()).append("\n");
        output.append("\n");
    }
    
    /**
     * Clears all lists
     */
    public void clearAll() {
        for (List<Item> list : listOrganizer) {
            list.clear();
        }
    }
    
    /**
     * Removes a specific item from display and list
     * @param itemName
     * @param price
     * @param category
     */
    public void removeItem(String itemName, double price, String category) {
        List<Item> list = getList(category);
        for (Item item : list) {
            if (item.getName() == itemName && item.getPrice() == price){
                list.remove(item);
                break;
            }  
        }
    }    

    /**
     * Gets a list of all items in ListOrganizer
     * @return List of all items
     */
    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();
        for (List<Item> list : listOrganizer) {
            allItems.addAll(list);
        }
        return allItems;
    }
    
    /**
     * Helper method to update the visual of the table
     * @return String[][] of table data
     */
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
