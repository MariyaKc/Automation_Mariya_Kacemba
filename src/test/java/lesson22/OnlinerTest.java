package lesson22;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.onliner.CatalogPage;
import pageObjects.onliner.ProductPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class OnlinerTest extends BaseTest {
    List<String> prices = new ArrayList<>();
    HashMap mobiles;

    @BeforeClass
    public void preconditions() {
        baseURI = properties.getProperty("api");
        mobiles = (HashMap) given().header("Content-Type", "application/json")
                .get("/catalog.api/search/mobile")
                .body().jsonPath()
                .getList("products").get(0);
        given().get("/shop.api/products/" + mobiles.get("key") + "/positions")
                .body().jsonPath()
                .getList("positions.primary").forEach(el -> {
                    HashMap data = (HashMap) el;
                    HashMap position_price = (HashMap) data.get("position_price");
                    prices.add(position_price.get("amount").toString());
                });
    }

    @Test
    public void searchMobile_Test() {
        String mobileName = mobiles.get("full_name").toString();

        get(CatalogPage.class)
                .open()
                .search(mobileName)
                .clickOnProductSearch(mobileName);

        Double pagePrice = Double.parseDouble(get(ProductPage.class).getProductPrice().split(" ")[0].replace(",", "."));
        Double apiMinPrice = prices.stream().map(Double::parseDouble).min(Double::compare).get();
        Assert.assertEquals(pagePrice, apiMinPrice);
    }
}
