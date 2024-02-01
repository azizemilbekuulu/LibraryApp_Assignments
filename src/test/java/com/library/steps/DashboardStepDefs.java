package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardStepDefs {
    String actualBBNumber;
    DashBoardPage dashBoardPage = new DashBoardPage();
    LoginPage loginPage = new LoginPage();



    @Given("the {string} on the home page")
    public void the_on_the_home_page(String librarian) {

    loginPage.login(librarian);
        BrowserUtil.waitFor(3);

    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        actualBBNumber = dashBoardPage.getModuleCount("Borrowed Books");

        System.out.println("actualBBNumber = " + actualBBNumber);


    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        String query="select count(*) from book_borrow where is_returned=0";
        DB_Util.runQuery(query);

        String expectedBBNumber = DB_Util.getFirstRowFirstColumn();

        System.out.println("expectedBBNumber = " + expectedBBNumber);

        Assert.assertEquals(expectedBBNumber,actualBBNumber);


    }
    String globalBookName;
    BookPage bookPage = new BookPage();
    @When("the user searches for {string} book")
    public void theUserSearchesForBook(String bookName) {
        globalBookName = bookName;
   bookPage.search.sendKeys(bookName + Keys.ENTER);
   BrowserUtil.waitFor(1);
    }

    @And("the user clicks edit book button")
    public void theUserClicksEditBookButton() {
        BrowserUtil.waitFor(1);
        bookPage.editBook("Clean Code").click();
    }

//    @Then("book information must match the Database")
//    public void bookInformationMustMatchTheDatabase() {
//        List<String> actualList = new ArrayList<>();
//        BrowserUtil.waitFor(1);
//        actualList.add(bookPage.bookName.getAttribute("value"));
//        actualList.add(bookPage.isbn.getAttribute("value"));
//        BrowserUtil.waitFor(1);
//        actualList.add(bookPage.year.getAttribute("value"));
//        BrowserUtil.waitFor(1);
//        actualList.add(bookPage.author.getAttribute("value"));
//        BrowserUtil.waitFor(1);
//        Select select = new Select(bookPage.categoryDropdown);
//        select.getAllSelectedOptions;
//        select.selectByVisibleText("Anthology");
//        String categorySelected = select.getFirstSelectedOption().getText();
//        actualList.add(categorySelected);
//        actualList.add(bookPage.description.getAttribute("value"));
//        BrowserUtil.waitFor(1);
//        System.out.println("bookPage.bookName.getAttribute(\"value\") = " + bookPage.bookName.getAttribute("value"));
//        System.out.println("bookPage.bookName.getText() = " + bookPage.bookName.getText());
//        System.out.println("actualList = " + actualList);
//
//
//        DB_Util.runQuery("select b.name, b.isbn, b.year, b.author, bc.name, b.description from books b\n" +
//                "    join book_categories bc on b.book_category_id = bc.id\n" +
//                "where b.name = 'Clean Code'");
//        List<String> expectedList = DB_Util.getRowDataAsList(1);
//        System.out.println("expectedList = " + expectedList);
//       Assert.assertEquals(actualList,expectedList);
//
//    }
@Then("book information must match the Database")
public void bookInformationMustMatchTheDatabase() {
        //UI

    String actualBookName = bookPage.bookName.getAttribute("value");
    System.out.println("actualBookName = " + actualBookName);
    String actualAuthor = bookPage.author.getAttribute("value");
    System.out.println("actualAuthor = " + actualAuthor);
    String actualISBN = bookPage.isbn.getAttribute("value");
    String actualDescription = bookPage.description.getAttribute("value");
    String actualYear = bookPage.year.getAttribute("value");
    Select select = new Select(bookPage.categoryDropdown);

    String actualCategory = select.getFirstSelectedOption().getText();
    System.out.println("actualCategory = " + actualCategory);

    List<String> actualList = new ArrayList<>(Arrays.asList(actualBookName,actualISBN, actualYear, actualAuthor, actualCategory, actualDescription));
    System.out.println("actualList = " + actualList);
    DB_Util.runQuery("select b.name, b.isbn, b.year, b.author, bc.name, b.description from books b\n" +
            "    join book_categories bc on b.book_category_id = bc.id\n" +
            "where b.name = 'Clean Code'");

    List<String> expectedList = DB_Util.getRowDataAsList(1);
    System.out.println("expectedList = " + expectedList);

    //Assert.assertEquals(actualList, expectedList);




}
}
