package th.mfu;

public class Product {
    private long id;
    private String nameProduct ;
    private String discription;
    private long price;

    //Conductor 
    public Product(long id, String nameProduct, String discription, long price) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.discription = discription;
        this.price = price;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public String getDiscription() {
        return discription;
    }
    public void setDiscription(String discription) {
        this.discription = discription;
    }
    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }
}
