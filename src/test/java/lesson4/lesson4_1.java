package lesson4;

import org.testng.Assert;
import org.testng.annotations.Test;

public class lesson4_1 {

    /**
     * на вход дается число
     * Если число кратно 3 - возвращается T,
     * если число кратно 5, то М, если 15, то S
     */
    @Test // метка метода
    public void Test1() {
        Assert.assertEquals(getData(3), "T", "Values is different");
    }

    @Test // метка метода
    public void Test2() {
        Assert.assertEquals(getData(5), "M", "Values is different");
    }

    @Test // метка метода
    public void Test3() {
        Assert.assertEquals(getData(15), "S", "Values is different");
    }

    @Test // метка метода
    public void Test4() {
        Assert.assertTrue(getData(2).isEmpty(), "Values is different"); //isEmpty()  возвращает true если строка пустая
    }

    private String getData(int value) {
        if (value % 3 == 0 && value % 15 != 0) { //не учитываем кратность 15
            return "T";
        } else if (value % 5 == 0 && value % 15 != 0) {
            return "M";
        } else if (value % 15 == 0) {
            return "S";
        }
        return "";
    }
}
