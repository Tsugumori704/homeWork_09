package Task02;

import Task02.Enum.CompanyStorage;
import Task02.Enum.ProductName;
import Task02.Exception.AnInsufficientAmount;
import Task02.Exception.ProductNotFound;
import Task02.Exception.StorageNotFound;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StorageManager {

    private Map<String, Storage> storageMap = new HashMap<String, Storage>();

    /**
     * Добавление склада
     *
     * @param storage Объект Storage
     */
    public void addStorage(Storage storage) {
        storageMap.put(storage.getNameStorage(), storage);
    }


    public Map<String, Storage> getStorageMap() {
        return storageMap;
    }

    /**
     * Вывод всех товаров на складах
     */
    public void printAllProduct() {
        Storage[] allProdict = storageMap.values().toArray(new Storage[storageMap.size()]);

        for (int i = 0; i < allProdict.length; i++) {
            System.out.println("Название склада: " + allProdict[i].getNameStorage());
            allProdict[i].printAllProduct();
            System.out.println();
        }
    }

    /**
     * Вывод всех внешних поставщиков
     */
    public void printExternalSuppliers() {
        Storage[] allProdict = storageMap.values().toArray(new Storage[storageMap.size()]);
        System.out.println("Список внешних поставщиков: ");
        for (int i = 0; i < allProdict.length; i++) {
            if (!allProdict[i].isChekCompanyStorage()) {
                System.out.println(allProdict[i].getNameStorage());
            }
        }
    }

    /**
     * @param productName
     * @return true если товар найден на складе
     * false если товар нет
     */

    /**
     * Поиск товара на складе компании
     *
     * @param companyStorageName enum CompanyStorage
     * @param productName        enum ProductName
     * @return true если товар найден на складе
     * false если товар нет
     */
    public boolean checkProductOnCompany(CompanyStorage companyStorageName, ProductName productName) {
        return storageMap.get(companyStorageName.getNameStorage()).searchProduct(productName);
    }

    /**
     * Перемещение товара между складами
     *
     * @param storageOutName Название отдающего склада
     * @param storageInName  Название принимающего склада
     * @param productName    Какой продукт переместить
     * @param value          Количество перемещенного продукта
     * @throws StorageNotFound
     * @throws ProductNotFound
     * @throws AnInsufficientAmount
     */
    public void movingProductOnStorage(String storageOutName, String storageInName, ProductName productName, Integer value) throws StorageNotFound, ProductNotFound, AnInsufficientAmount {
        Storage storageOut;
        Storage storageIn;
        if (storageMap.get(storageOutName) != null) {
            storageOut = storageMap.get(storageOutName);
        } else {
            throw new StorageNotFound();
        }
        if (storageMap.get(storageInName) != null) {
            storageIn = storageMap.get(storageInName);
        } else {
            throw new StorageNotFound();
        }
        storageIn.addProduct(storageOut.getProduct(productName, value));


        PrintInvoice(storageOutName, storageInName, productName, value);

    }

    private void PrintInvoice(String storageOutName, String storageInName, ProductName productName, Integer value) {
        Storage storageIn = storageMap.get(storageInName);
        Product productIn = storageIn.getProductMap().get(productName.getName());
        Storage storage = new Storage(storageInName);
        storage.addProduct(productIn);

        System.out.println("Накладная");

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println(formatForDateNow.format(dateNow));
        System.out.println("От кого: " + storageOutName);
        System.out.println("Кому: " + storageInName);

        System.out.println("Основание: ");
        System.out.println("Доверенность_______________от_" + formatForDateNow.format(dateNow));
        System.out.println();
        storage.printAllProduct();
        System.out.println();
        System.out.print("Отпустил: " + storageOutName);
        System.out.print("       ");
        System.out.println("Получил: " + storageInName);
        System.out.println();

    }

}
