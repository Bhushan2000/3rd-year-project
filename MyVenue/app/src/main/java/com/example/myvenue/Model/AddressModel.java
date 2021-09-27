package com.example.myvenue.Model;

public class AddressModel {
    private String FullName;
    private String mobileNo;
    private String Address;
    private String PinCode;
    private boolean selected;


    public AddressModel(String fullName, String address, String pinCode, boolean selected, String mobileNo) {
        FullName = fullName;
        Address = address;
        PinCode = pinCode;
        this.selected = selected;
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

}
