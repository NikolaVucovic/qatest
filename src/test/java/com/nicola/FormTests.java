package com.nicola;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTests {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }
    @AfterAll
    static void close() {
      closeWindow();
    }

    @Test
    void regFormTest() {

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        String FirstName = "Arnold";
        String LastName = "Kreps";
        String Email = "mail@gmail.com";
        String Gender = "Male";
        String Phone = "8800800800";
        String Subject = "Arts";
        String Hobbies = "Reading";
        String ImagePath = "IMG_20220215_015508_705.jpg";
        String Address = "Street, house, apart";
        String State = "Uttar Pradesh";
        String City = "Lucknow";

        $("[id=firstName]").setValue(FirstName);
        $("[id=lastName]").setValue(LastName);
        $("[id=userEmail]").setValue(Email);
        $("[id=genterWrapper]").$(byText(Gender)).click();
        $("[id=userNumber]").setValue(Phone);
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1985");
        $(".react-datepicker__month").$(byText("2")).click();
        $("#subjectsInput").setValue(Subject).pressEnter();
        $("#hobbiesWrapper").$(byText(Hobbies)).click();
        $("#uploadPicture").uploadFromClasspath(ImagePath);
        $("#currentAddress").setValue(Address);
        $("#state").scrollTo();
        $("#state").click();
        $("#stateCity-wrapper").$(byText(State)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(City)).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Label Value"),
                text("Student Name " + FirstName + " " + LastName),
                text("Student Email " + Email),
                text("Gender " + Gender),
                text("Mobile " + Phone),
                text("Date of Birth 02 December,1985"),
                text("Subjects " + Subject),
                text("Hobbies " + Hobbies),
                text(ImagePath),
                text("Address " + Address),
                text("State and City " + State + " " + City));
    }
}
