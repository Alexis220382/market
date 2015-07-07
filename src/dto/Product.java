package dto;

public class Product {

    private int id;
    private String description;
    private float rate;
    private int quantity;
    private int numberProduct;

    public Product(int id, int numberProduct, String description, float rate, int quantity) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.quantity = quantity;
        this.numberProduct = numberProduct;
    }
    
    public Product(int numberProduct, String description, float rate, int quantity) {
        this.description = description;
        this.rate = rate;
        this.quantity = quantity;
        this.numberProduct = numberProduct;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getNumberProduct(){
        return numberProduct;
    }
    
    public void setNumberProduct(int numberProduct){
        this.numberProduct = numberProduct;
    }
}
