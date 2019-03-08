import Task02.Enum.CompanyStorage;
import Task02.Exception.AnInsufficientAmount;
import Task02.Exception.ProductNotFound;
import Task02.Exception.StorageNotFound;
import Task02.Product;
import Task02.Enum.ProductName;
import Task02.Enum.Units;
import Task02.Storage;
import Task02.StorageManager;
import org.junit.Assert;
import org.junit.Test;

public class SorageServiceTest {


    @Test
    public void StoragePrintAllProductTest() throws Exception {

        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);
        Product Prod02 = new Product(ProductName.CUPBOARD, Units.PIECES, 3, 7000);
        Product Prod03 = new Product(ProductName.TELEVISION, Units.PIECES, 1, 4000);
        Product Prod04 = new Product(ProductName.WINDOW, Units.PIECES, 40, 230);
        Product Prod05 = new Product(ProductName.WINDOW, Units.PIECES, 30, 230);

        Storage storage = new Storage(CompanyStorage.STORAGE01);
        storage.addProduct(Prod01);
        storage.addProduct(Prod02);
        storage.addProduct(Prod03);
        storage.addProduct(Prod04);
        storage.addProduct(Prod05);

        storage.printAllProduct();

        System.out.println();
    }

    @Test
    public void StorageAddTest1() throws Exception {
        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);

        Storage storage = new Storage(CompanyStorage.STORAGE01);
        storage.addProduct(Prod01);

        Assert.assertNotNull(storage.getProduct(ProductName.PEN, 50));
    }

    @Test
    public void StorageAddTest2() throws Exception {
        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);
        Product Prod02 = new Product(ProductName.PEN, Units.PIECES, 20, 50);

        Storage storage = new Storage(CompanyStorage.STORAGE01);
        storage.addProduct(Prod01);
        storage.addProduct(Prod02);

        Assert.assertNotNull(storage.getProduct(ProductName.PEN, 70));
    }

    @Test(expected = ProductNotFound.class)
    public void StorageFailureGetProductTest1() throws Exception {

        Storage storage = new Storage(CompanyStorage.STORAGE01);

        Assert.assertNotNull(storage.getProduct(ProductName.PEN, 50));
    }

    @Test(expected = AnInsufficientAmount.class)
    public void StorageFailureGetProductTest2() throws Exception {
        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);

        Storage storage = new Storage(CompanyStorage.STORAGE01);
        storage.addProduct(Prod01);

        storage.getProduct(ProductName.PEN, 60);
    }

    @Test
    public void StorageGetProductTest() throws Exception {
        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);

        Storage storage = new Storage(CompanyStorage.STORAGE01);
        storage.addProduct(Prod01);

        Assert.assertNotNull(storage.getProduct(ProductName.PEN, 40));

        Assert.assertEquals(10, (int) storage.getProductMap().get(ProductName.PEN.getName()).getQuantity());
    }

    @Test
    public void checkProductPositive() throws Exception {
        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);

        Storage storage = new Storage(CompanyStorage.STORAGE01);
        storage.addProduct(Prod01);

        Assert.assertTrue(storage.searchProduct(ProductName.PEN));
    }

    @Test
    public void checkProductNegative() throws Exception {

        Storage storage = new Storage(CompanyStorage.STORAGE01);

        Assert.assertFalse(storage.searchProduct(ProductName.PEN));
    }

    @Test
    public void testStorageManager() throws Exception {
        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);
        Product Prod02 = new Product(ProductName.CUPBOARD, Units.PIECES, 3, 7000);
        Product Prod03 = new Product(ProductName.TELEVISION, Units.PIECES, 1, 4000);
        Product Prod04 = new Product(ProductName.WINDOW, Units.PIECES, 40, 230);
        Product Prod05 = new Product(ProductName.WINDOW, Units.PIECES, 30, 230);

        Storage storage1 = new Storage(CompanyStorage.STORAGE01);
        Storage storage2 = new Storage(CompanyStorage.STORAGE02);
        storage1.addProduct(Prod01);
        storage1.addProduct(Prod02);
        storage1.addProduct(Prod03);
        storage1.addProduct(Prod04);
        storage1.addProduct(Prod05);

        storage2.addProduct(Prod03);
        storage2.addProduct(Prod05);

        StorageManager storageManager = new StorageManager();

        storageManager.addStorage(storage1);
        storageManager.addStorage(storage2);

        Assert.assertEquals(2, storageManager.getStorageMap().size());
        Assert.assertEquals(storageManager.getStorageMap().get(CompanyStorage.STORAGE01.getNameStorage()).getNameStorage(), CompanyStorage.STORAGE01.getNameStorage());
        Assert.assertEquals(storageManager.getStorageMap().get(CompanyStorage.STORAGE02.getNameStorage()).getNameStorage(), CompanyStorage.STORAGE02.getNameStorage());

    }

    @Test
    public void testAddStorageOnStorageManager() throws Exception {
        Storage storage1 = new Storage(CompanyStorage.STORAGE01);
        Storage storage2 = new Storage(CompanyStorage.STORAGE02);

        StorageManager storageManager = new StorageManager();

        storageManager.addStorage(storage1);
        storageManager.addStorage(storage2);

        Assert.assertEquals(2, storageManager.getStorageMap().size());
        Assert.assertEquals(storageManager.getStorageMap().get(CompanyStorage.STORAGE01.getNameStorage()).getNameStorage(), CompanyStorage.STORAGE01.getNameStorage());
        Assert.assertEquals(storageManager.getStorageMap().get(CompanyStorage.STORAGE02.getNameStorage()).getNameStorage(), CompanyStorage.STORAGE02.getNameStorage());
    }

    @Test
    public void testPrintExternalSuppliers() throws Exception {
        Storage storage1 = new Storage(CompanyStorage.STORAGE01);
        Storage storage2 = new Storage(CompanyStorage.STORAGE02);
        Storage storage3 = new Storage("Склад внешней компании");

        StorageManager storageManager = new StorageManager();

        storageManager.addStorage(storage1);
        storageManager.addStorage(storage2);
        storageManager.addStorage(storage3);

        storageManager.printExternalSuppliers();
    }

    @Test
    public void testCheckProductOnCompany() throws Exception {

        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);
        Product Prod05 = new Product(ProductName.WINDOW, Units.PIECES, 30, 230);

        Storage storage1 = new Storage(CompanyStorage.STORAGE01);
        Storage storage2 = new Storage(CompanyStorage.STORAGE02);
        storage1.addProduct(Prod01);

        storage2.addProduct(Prod05);

        StorageManager storageManager = new StorageManager();

        storageManager.addStorage(storage1);
        storageManager.addStorage(storage2);

        Assert.assertTrue(storageManager.checkProductOnCompany(CompanyStorage.STORAGE01, ProductName.PEN));
        Assert.assertTrue(storageManager.checkProductOnCompany(CompanyStorage.STORAGE02, ProductName.WINDOW));
    }

    @Test
    public void testCheckProductOnCompanyNegative() throws Exception {
        Storage storage1 = new Storage(CompanyStorage.STORAGE01);
        Storage storage2 = new Storage(CompanyStorage.STORAGE02);

        StorageManager storageManager = new StorageManager();

        storageManager.addStorage(storage1);
        storageManager.addStorage(storage2);

        Assert.assertFalse(storageManager.checkProductOnCompany(CompanyStorage.STORAGE01, ProductName.PEN));
    }

    @Test
    public void testMovingProductOnStorage() throws Exception {
        Storage storage1 = new Storage(CompanyStorage.STORAGE01);
        Storage storage2 = new Storage(CompanyStorage.STORAGE02);

        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);
        storage1.addProduct(Prod01);

        StorageManager storageManager = new StorageManager();

        storageManager.addStorage(storage1);
        storageManager.addStorage(storage2);

        storageManager.movingProductOnStorage(CompanyStorage.STORAGE01.getNameStorage(), CompanyStorage.STORAGE02.getNameStorage(), ProductName.PEN, 15);

        Assert.assertEquals((int) storage1.getProductMap().get(ProductName.PEN.getName()).getQuantity(), 35);
        Assert.assertEquals((int) storage2.getProductMap().get(ProductName.PEN.getName()).getQuantity(), 15);
    }

    @Test(expected = StorageNotFound.class)
    public void testMovingProductOnStorageNotFoundStorage() throws Exception {
        Storage storage1 = new Storage(CompanyStorage.STORAGE01);

        Product Prod01 = new Product(ProductName.PEN, Units.PIECES, 50, 50);
        storage1.addProduct(Prod01);

        StorageManager storageManager = new StorageManager();

        storageManager.addStorage(storage1);

        storageManager.movingProductOnStorage(CompanyStorage.STORAGE01.getNameStorage(), "Склад", ProductName.PEN, 15);

    }

}
