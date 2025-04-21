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

    private void addToList(Item item){
        String category = item.getCategory();
        getList(category).add(item);
    }

    private void remove(Item item){
        String category = item.getCategory();
        getList(category).remove(item);
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

    private List<Item> getList(String category){
        if (category=="produce"){
            return produceList;
        }
        else if (category=="meatSeafood"){
            return meatSeafoodList;
        }
        else if (category=="dairyEggsList"){
            return dairyEggsList;
        }
        else if(category=="bakery"){
            return bakeryList;
        }
        else if(category=="pantryStaples"){
            return pantryStaplesList;
        }
        else if(category=="frozenFood"){
            return frozenFoodList;
        }
        else if(category=="snacksBeverages"){
            return snacksBeveragesList;
        }
        else if(category=="householdGoods"){
            return householdGoodsList;
        }
        else if(category=="personalCare"){
            return personalCareList;
        }
        else{
            throw new InvalidParameterException("Invalid category");
        }

    }
    
}
