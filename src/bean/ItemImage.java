package bean;

public class ItemImage {
    private int id;
    private Item item;
    private String type;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
