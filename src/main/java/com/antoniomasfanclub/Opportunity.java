package com.antoniomasfanclub;

public class Opportunity {

    private final int id;
    private int quantity;
    private Product product;
    private Status status;
    private Contact contact;
    private static int generatedOpportunities = 0;

    public Opportunity() {
        this.id = generateId();
    }

    public Opportunity(int quantity, Product product, Status status, Contact contact) {
        this.id = generateId();
        this.quantity = quantity;
        this.product = product;
        this.status = status;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1)
            throw new IllegalArgumentException("Product quantity should be " + Colours.YELLOW + "equal or bigger than 1" + Colours.RESET + ".");
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product can only be "
                + CLI.colourString(Colours.YELLOW, Product.BOX.name()) + ","
                + CLI.colourString(Colours.GREEN, Product.FLATBED.name()) + ","
                + " or " + CLI.colourString(Colours.CYAN, Product.HYBRID.name()) + ".");

        this.product = product;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("Status can only be "
                + CLI.colourString(Colours.YELLOW, Status.OPEN.name()) + ","
                + CLI.colourString(Colours.GREEN, Status.CLOSED_WON.name()) + ","
                + " or " + CLI.colourString(Colours.RED, Status.CLOSED_LOST.name()) + ".");
        this.status = status;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        if (contact == null) throw new IllegalArgumentException("Invalid contact assigned");
        this.contact = contact;
    }

    public static int generateId() {
        return ++generatedOpportunities;
    }

    @Override
    public String toString() {
        return CLI.colourString(Colours.BACKGROUND_CYAN, " 🆔 " + this.getId() +" ") + " #️⃣ " + this.getQuantity() + " 🚛 " + product +
                " 👤" + contact.getName() + " from " + contact.getCompanyName() + "; 🚦 status: " + status;
    }
}
