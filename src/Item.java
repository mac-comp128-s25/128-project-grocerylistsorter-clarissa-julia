public class Item {
    //possible reference to canvas to update GUI when quantity updated

    private String name;
    private int quantity;
    private double individualPrice;
    private double bulkPrice;
    private String category;

    public Item(String name, int quantity, double individualPrice, String category){
        if (quantity == 0){
            quantity = 1;}
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this. individualPrice = individualPrice;
        bulkPrice = individualPrice * this.quantity;
    }

    public String getName(){ //primarily for clarification
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

    public int getQuantity() {
        return quantity;
    }    

    public String getTitle(){
        return name + " x" + quantity + "\n$" + bulkPrice;
    }
    
}
