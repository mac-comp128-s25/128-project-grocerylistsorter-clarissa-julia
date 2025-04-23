import java.security.InvalidParameterException;
import java.util.List;

public class Item {
    //possible reference to canvas to update GUI when quantity updated

    private String name;
    private int quantity;
    private double individualPrice;
    private double bulkPrice;
    private String category;

    public Item(String name, String quantity, double individualPrice, String category){
        try{
            this.quantity = Integer.parseInt(quantity);
        }catch (NumberFormatException ex) {
            this.quantity = 1;
        }
        this.name = name;
        this.category = category;
        this. individualPrice = individualPrice;
        bulkPrice = individualPrice * this.quantity;
    }

    private String getName(){ //primarily for clarification
        return name;
    } 

    public double getPrice(){ //for total calculator
        return bulkPrice;
    }

    public String getCategory(){
        return category;
    }

    private void setQuantity(int newQuantity){
        quantity = newQuantity;
        bulkPrice = individualPrice*quantity;
        //update ui somehow
    }

    

    private String getTitle(){
        return name + " x" + quantity + "\n$" + bulkPrice;
    }
    
}
