package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

public class RegistrationFormTest extends TestBase {

    RegistrationFormPage registrationFormPage;

    @Test
    public void successfulFillRegistrationForm() {
        registrationFormPage = new RegistrationFormPage();

        registrationFormPage.openPage()
                .fillForm()
                .checkData();
    }

}

