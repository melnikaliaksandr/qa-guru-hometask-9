package pages;

import config.DriverConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.DataHelper.*;

public class RegistrationFormPage {

    static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class, System.getProperties());

    public String
            firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            email = getRandomEmail(),
            mobileNumber = getRandomMobileNumber(),
            year = getRandomYear(),
            month = getRandomMonth(),
            currentAddress = getRandomCurrentAddress(),
            gender, day, subjects, hobbies, state, city;

    @Step("Open students registration form")
    public RegistrationFormPage openPage() {
        open(driverConfig.getUrl());
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Fill students registration form")
    public RegistrationFormPage fillForm() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        gender = selectRandomGenderAndReturnName();
        $("#userNumber").setValue(mobileNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        day = selectRandomDayAndReturnNumber();
        subjects = selectRandomSubjectAndReturnList();
        hobbies = selectRandomHobbiesAndReturnName();
        $("#uploadPicture").uploadFromClasspath("image.jpg");
        $("#currentAddress").scrollTo().setValue(currentAddress);
        state = selectRandomStateAndReturnName();
        city = selectRandomCityAndReturnName(state);
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    @Step("Verify successful form submit")
    public RegistrationFormPage checkData() {
        $(".table").shouldHave(
                text(firstName),
                text(lastName),
                text(email),
                text(gender),
                text(mobileNumber),
                text(day),
                text(month),
                text(year),
                text(subjects),
                text(hobbies),
                text("image.jpg"),
                text(currentAddress),
                text(state),
                text(city));
        return this;
    }

}
