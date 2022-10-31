package pageObjects.rw_Selenide;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage {

    private final ElementsCollection searchResults = $$("[class=sch-table__body-wrap] [class=sch-table__row]");

    public SearchResultPage checkResultCount(Integer size){
        searchResults.should(CollectionCondition.size(size));
        return this;
    }
}
