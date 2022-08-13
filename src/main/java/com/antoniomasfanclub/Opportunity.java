package com.antoniomasfanclub;

public class Opportunity {

private int id;

private int quantity;

private Product product;

private Status status;

private Contact contact;

    public Opportunity(int id, int quantity, Product product, Status status, Contact contact) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.status = status;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}


