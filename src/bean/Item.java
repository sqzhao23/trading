package bean;


public class Item {
    private int id;
    private String name;
    private float originalPrice;
    private float salePrice;
    private String description;
    private String status;
    private User user;
    private Category category;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }
    public float getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }


}
