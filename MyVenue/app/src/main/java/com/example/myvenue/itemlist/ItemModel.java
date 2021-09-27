package com.example.myvenue.itemlist;

public class ItemModel {
    private String name;
    private String address;
    private String productID;
    private String mobileNo;
    private boolean availability;
    private String type;


    public ItemModel(String name, String address, String productID, String mobileNo, boolean availability, String type) {
        this.name = name;
        this.address = address;
        this.productID = productID;
        this.mobileNo = mobileNo;
        this.availability = availability;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
