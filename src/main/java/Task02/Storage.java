package Task02;

import Task02.Enum.CompanyStorage;
import Task02.Enum.ProductName;
import Task02.Exception.AnInsufficientAmount;
import Task02.Exception.ProductNotFound;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private String nameStorage;
    private boolean chekCompanyStorage;
    private Map<String, Product> productMap = new HashMap<String, Product>();

    public Storage(String nameStorage) {
        this.nameStorage = nameStorage;
        chekCompanyStorage = false;
    }

    public Storage(CompanyStorage Storage) {
        this.nameStorage = Storage.getNameStorage();
        chekCompanyStorage = true;
    }

    /**
     * @return Имя склада
     */
    public String getNameStorage() {
        return nameStorage;
    }


    /**
     * @return Map продуктов на складе
     */
    public Map<String, Product> getProductMap() {
        return productMap;
    }

    /**
     * @return true Когда склад принадлежит компании
     * false в противном случаи
     */
    public boolean isChekCompanyStorage() {
        return chekCompanyStorage;
    }

    /**
     * Добавляет продукт на склад
     *
     * @param product
     */
    public void addProduct(Product product) {
        if (productMap.get(product.getNameProduct()) == null) {
            productMap.put(product.getNameProduct(), product);
        } else {
            productMap.get(product.getNameProduct()).addQuantity(product.getQuantity());
        }

    }

    /**
     * Выводит содержимое всего склада
     */
    public void printAllProduct() {
        Product[] allProdict = productMap.values().toArray(new Product[productMap.size()]);

        System.out.println("Список всех товаров на складе:");
        System.out.println("№  | Наименование | Ед.изм. | Колличество      | Цена    | Сумма   |");
        for (int i = 0; i < allProdict.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            String str = "";
            str = str + (i + 1);
            stringBuilder.append(str);
            stringBuilder.append(generatePierced(4 - str.length()));

            str = allProdict[i].getNameProduct();
            stringBuilder.append(str);
            stringBuilder.append(generatePierced(14 - str.length()));

            str = allProdict[i].getNameUnits();
            stringBuilder.append(str);
            stringBuilder.append(generatePierced(9 - str.length()));

            str = allProdict[i].getQuantity().toString();
            stringBuilder.append(str);
            stringBuilder.append(generatePierced(18 - str.length()));

            str = allProdict[i].getPrice().toString();
            stringBuilder.append(str);
            stringBuilder.append(generatePierced(9 - str.length()));
            Integer isd = allProdict[i].getPrice() * allProdict[i].getQuantity();
            stringBuilder.append(isd);
            stringBuilder.append(generatePierced(9 - isd.toString().length()));

            System.out.println(stringBuilder);
        }
    }

    private String generatePierced(int i) {
        String str = "";
        for (int j = 0; j < i; j++) {
            str += " ";
        }
        str += "|";
        return str;
    }

    /**
     * Забирает продукст со склада
     *
     * @param productName
     * @param quantity
     * @return Product
     * @throws AnInsufficientAmount
     * @throws ProductNotFound
     */
    public Product getProduct(ProductName productName, Integer quantity) throws AnInsufficientAmount, ProductNotFound {
        Product productOut = productMap.get(productName.getName());
        if (productOut == null) {
            throw new ProductNotFound();
        }
        productOut.takeAway(quantity);
        if (productOut.getQuantity() == 0) {
            productMap.remove(productName.getName());
        }
        return new Product(productOut.getProductName(), productOut.getUnits(), quantity, productOut.getPrice());
    }

    /**
     *
     * @param productName
     * @return true если товар найден на складе
     *         false если товар нет
     */
    public boolean searchProduct(ProductName productName){
        if (productMap.get(productName.getName()) == null){
            return false;
        }else {
            return true;
        }
    }
}
