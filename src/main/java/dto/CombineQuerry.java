package dto;


public class CombineQuerry {
    
    private int id;
    private String description;
    private float rate;
    private int quantity;
    private int numberProduct;
    private int idResult;
    private int idResultSeller;
    private int idResultProducts;
    private String dateResult;
    private float rateResult;
    private int quantityResult;
    private int idSeller;
    private String lastName;
    private String firstName;
    private String secondName;
    
    private String dateFrom;
    private String dateTill;
    private String lastNameQue; 

    public CombineQuerry(String lastName, String firstName, String secondName, String description, float rateResult, int quantityResult, String dateResult) {
        this.description = description;
        this.dateResult = dateResult;
        this.rateResult = rateResult;
        this.quantityResult = quantityResult;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
    }
    
    public CombineQuerry(String lastName, String firstName, String secondName, String description, float rateResult, int quantityResult, String dateResult, String lastNameQue, String dateFrom, String dateTill) {
        this.description = description;
        this.dateResult = dateResult;
        this.rateResult = rateResult;
        this.quantityResult = quantityResult;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNumberProduct(int numberProduct) {
        this.numberProduct = numberProduct;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }

    public void setIdResultSeller(int idResultSeller) {
        this.idResultSeller = idResultSeller;
    }

    public void setIdResultProducts(int idResultProducts) {
        this.idResultProducts = idResultProducts;
    }

    public void setDateResult(String dateResult) {
        this.dateResult = dateResult;
    }

    public void setRateResult(float rateResult) {
        this.rateResult = rateResult;
    }

    public void setQuantityResult(int quantityResult) {
        this.quantityResult = quantityResult;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public float getRate() {
        return rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public int getIdResult() {
        return idResult;
    }

    public int getIdResultSeller() {
        return idResultSeller;
    }

    public int getIdResultProducts() {
        return idResultProducts;
    }

    public String getDateResult() {
        return dateResult;
    }

    public float getRateResult() {
        return rateResult;
    }

    public int getQuantityResult() {
        return quantityResult;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTill() {
        return dateTill;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTill(String dateTill) {
        this.dateTill = dateTill;
    }

    public String getLastNameQue() {
        return lastNameQue;
    }

    public void setLastNameQue(String lastNameQue) {
        this.lastNameQue = lastNameQue;
    }
    
}
