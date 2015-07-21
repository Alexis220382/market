package dto;


public class Resulting {
    
    private int idResult;
    private int idResultSeller;
    private int idResultProducts;
    private String dateResult;
    private float rateResult;
    private int quantityResult;

    public Resulting(int idResult, int idResultSeller, int idResultProducts, String dateResult, float rateResult, int quantityResult) {
        this.idResult = idResult;
        this.idResultSeller = idResultSeller;
        this.idResultProducts = idResultProducts;
        this.dateResult = dateResult;
        this.rateResult = rateResult;
        this.quantityResult = quantityResult;
    }

    public Resulting(int idResultSeller, int idResultProducts, float rateResult, int quantityResult) {
        this.idResultSeller = idResultSeller;
        this.idResultProducts = idResultProducts;
        this.rateResult = rateResult;
        this.quantityResult = quantityResult;
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
}
