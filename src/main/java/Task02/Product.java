package Task02;

import Task02.Enum.ProductName;
import Task02.Enum.Units;
import Task02.Exception.AnInsufficientAmount;

public class Product {

    private ProductName nameProduct;
    private Units units;
    private Integer quantity;
    private Integer price;

    public Product(ProductName nameProduct, Units units, Integer quantity, Integer price) {
        this.nameProduct = nameProduct;
        this.units = units;
        this.quantity = quantity;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct.getName();
    }

    public ProductName getProductName() {
        return nameProduct;
    }

    public void setNameProduct(ProductName nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNameUnits() {
        return units.getNameUnits();
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void addQuantity(Integer value) {
        quantity += value;
    }

    public void takeAway(Integer quantity) throws AnInsufficientAmount {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
        } else {
            throw new AnInsufficientAmount();
        }

    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
