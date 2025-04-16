import java.util.List;
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

    private void addToList(Item item, String category){

    }

    private double totalCalculator(){
        double total = 0;
        for (List<Item> list : listOrganizer){
            for (Item item : list){
                total += item.getPrice();
            }
        }
        return total;
    }
    
}
