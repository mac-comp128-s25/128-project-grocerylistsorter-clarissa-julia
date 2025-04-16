public class Item {

    private String name;
    private int quantity;
    private double individualPrice;
    private double bulkPrice;
    private String category; //unused for now

    public Item(String name, int quantity, String category){
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        individualPrice = //get from provider
        bulkPrice = individualPrice * quantity;
    }

    private String getName(){ //primarily for clarification
        return name;
    } 

    public double getPrice(){ //for total calculator
        return bulkPrice;
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
