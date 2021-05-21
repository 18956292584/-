package com.springboot.entity;

public class Address {
    int	a_id;
    String	a_phone;
    String	a_address;
    String a_name;
    String	a_user;
    String a_gwc;
    boolean	a_state;

    public String getA_gwc() {
        return a_gwc;
    }

    public void setA_gwc(String a_gwc) {
        this.a_gwc = a_gwc;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_phone() {
        return a_phone;
    }

    public void setA_phone(String a_phone) {
        this.a_phone = a_phone;
    }

    public String getA_address() {
        return a_address;
    }

    public void setA_address(String a_address) {
        this.a_address = a_address;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_user() {
        return a_user;
    }

    public void setA_user(String a_user) {
        this.a_user = a_user;
    }

    public boolean isA_state() {
        return a_state;
    }

    public void setA_state(boolean a_state) {
        this.a_state = a_state;
    }
}
