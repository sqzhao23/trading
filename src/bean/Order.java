package bean;


import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private String orderCode;
    private String address;
    private String receiver;
    private String mobile;
    private Date createDate;//订单生成日期
    private String status;//订单状态
    private User user;//卖家

    private List<OrderItem> orderItems;
    private float totalPrice;//订单总价
    private int totalNumber;//订单物品数量

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public int getTotalNumber() {
        return totalNumber;
    }
    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public String getOrderCode() {
        return orderCode;
    }
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
