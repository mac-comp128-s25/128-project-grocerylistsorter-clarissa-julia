public class Item {
    //possible reference to canvas to update GUI when quantity updated

    private String name;
    private int quantity;
    private double bulkPrice;
    private String category;

    /**
     * Creates Item object to be added to ListOrganizer
     * @param name
     * @param quantity
     * @param individualPrice
     * @param category
     */
    public Item(String name, int quantity, double individualPrice, String category){
        if (quantity == 0){
            quantity = 1;}
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        bulkPrice = individualPrice * this.quantity;
    }

    /**
     * @return String name
     */
    public String getName(){
        return name;
    } 

    /**
     * @return double bulkPrice
     */
    public double getPrice(){ //for total calculator
        return bulkPrice;
    }

    /**
     * @return String category
     */
    public String getCategory(){
        return category;
    }

    /**
     * @return int quantity
     */
    public int getQuantity() {
        return quantity;
    }    

    /**
     * @return String title
     */
    public String getTitle(){
        return name + " x" + quantity + "\n$" + bulkPrice;
    }
    
}
