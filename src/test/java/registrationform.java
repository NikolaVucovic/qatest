import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class registrationform

    {
        @BeforeAll
        static void setUp() {
            Configuration.holdBrowserOpen = true;
            Configuration.baseUrl = "https://demoqa.com";
            Configuration.browserPosition = "0x0";
            Configuration.browserSize = "1920x1080";
        }

        @Test
        void reistrationform() {

            open("/automation-practice-form");
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");

            //Input parameters
            String firstName = "Arnold";
            String lastName = "Kreps";
            String email = "mail@gmail.com";
            String gender = "Male";
            String phone = "8800800800";
            String subject = "Arts";
            String hobbies = "Reading";
            String imagePath = "IMG_20220215_015508_705.jpg";
            String address = "Street, house, apart";
            String state = "Uttar Pradesh";
            String city = "Lucknow";

            $("[id=firstName]").setValue(firstName);
            $("[id=lastName]").setValue(lastName);
            $("[id=userEmail]").setValue(email);
            $("[id=genterWrapper]").$(byText(gender)).click();
            $("[id=userNumber]").setValue(phone);
            $("[id=dateOfBirthInput]").click();
            $(".react-datepicker__month-select").selectOptionContainingText("December");
            $(".react-datepicker__year-select").selectOptionContainingText("1985");
            $(".react-datepicker__day--002").click();
            $("#subjectsInput").setValue(subject).pressEnter();
            $("#hobbiesWrapper").$(byText(hobbies)).click();
            $("#uploadPicture").uploadFromClasspath(imagePath);
            $("#currentAddress").setValue(address);
            $("#state").scrollTo();
            $("#state").click();
            $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Lucknow")).click();
            $("#submit").click();

            //Form validation
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(
                    text("Label Value"),
                    text("Student Name " + firstName + " " + lastName),
                    text("Student Email " + email),
                    text("Gender " + gender),
                    text("Mobile " + phone),
                    text("Date of Birth 02 December,1985"),
                    text("Subjects " + subject),
                    text("Hobbies " + hobbies),
                    text("IMG_20220215_015508_705.jpg"),
                    text("Address " + address),
                    text("State and City " + state + " " + city));

            $("#closeLargeModal").click();
        }
    }
