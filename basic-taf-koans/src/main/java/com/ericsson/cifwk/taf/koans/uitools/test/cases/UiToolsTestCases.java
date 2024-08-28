/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.cifwk.taf.koans.uitools.test.cases;

import static com.sandwich.util.Assert.assertEquals;


import com.ericsson.cifwk.taf.annotations.DataDriven;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.execution.TestExecutionEvent;
import com.ericsson.cifwk.taf.koans.uitools.test.helper.UiKoanHelper;
import com.ericsson.cifwk.taf.koans.uitools.test.operators.UiToolsOperatorImpl;
import com.ericsson.cifwk.taf.ui.UI;
import com.sandwich.koan.Koan;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * In this koans you will need to have a selenium binary for chrome download the latest release
 * from here: https://chromedriver.storage.googleapis.com/index.html
 * when running Ui tests locally you must use
 * the command -Dwebdriver.chrome.driver="Path/to/binary.exe" to specify the path to the selenium binary
 * in the VM options: when you edit configurations.
 */
public class UiToolsTestCases extends UiKoanHelper  {

    @Inject
    UiToolsOperatorImpl webOperatorImpl;

    @BeforeSuite
    public void setUp() {
        UI.closeWindow(TestExecutionEvent.ON_SUITE_FINISH);
        webOperatorImpl.setUpBrowser();
    }

    /**
     * In this koan you will get a generic view model and get mapping with a web page element
     * (HINT: Update the getButtonText() method in UIToolsOperatorImpl.java class)
     */
    @Koan
    @TestId(id = "CIP-6980_Func_1", title = "Get Text From Button")
    @Test()
    public void getTextFromButton() {
        assertEquals("Sample Button", webOperatorImpl.getButtonText());
    }

    /**
     * In this koan you will get a UI component using CSS
     * (HINT: Update the getLabelText() method in UIToolsOperatorImpl.java class)
     */
    @Koan
    @TestId(id = "CIP-6980_Func_2", title = "Get Label Text")
    @Test()
    public void getTextOfLabel() {
        assertEquals("This is a span for Koan 2", webOperatorImpl.getLabelText());
    }

    /**
     * In this koan you will create a UI button element and click on the button element
     * (HINT: Update the getTextWhenButtonPressed() method in UIToolsOperatorImpl.java class)
     */
    @Koan
    @TestId(id = "CIP-6980_Func_3", title = "Get Text When Button Pressed")
    @Test()
    public void getTextWhenButtonPressed() {
        assertEquals("You have pressed the button", webOperatorImpl.getTextWhenButtonPressed());
    }

    /**
     * In this koan you will retrieve a UI textbox element and fill the text into the textbox
     * (HINT: Update the enterNameAndSignum() method in UIToolsOperatorImpl.java class)
     */
    @Koan
    @TestId(id = "CIP-6980_Func_4", title = "Enter name and signum")
    @Test()
    @DataDriven(name = "uiKoans")
    public void getNameAndSignum(@Input("name") String name, @Input("signum") String signum) {
        String result = name + " " + signum;
        assertEquals(result, webOperatorImpl.enterNameAndSignum(name, signum));
    }

    /**
     * In this koan you will load a specific view model by BrowserTab instance
     * (HINT: Update the enterNameKoan5() method in UIToolsOperatorImpl.java class)
     */
    @Koan
    @TestId(id = "CIP-6980_Func_5", title = "Enter name")
    @Test()
    @DataDriven(name = "uiKoans")
    public void gGetNameKoan5(@Input("name") String name) {
        assertEquals("John Doe", webOperatorImpl.enterNameKoan5(name));
    }

    /**
     * In this koan you will map web page elements in the UI module
     * (HINT: Update the SearchViewModel.java class to find the appropriate UI element mappings)
     */
    @Koan
    @TestId(id = "CIP-6980_Func_6", title = "Press button and enter name")
    @Test()
    @DataDriven(name = "uiKoans")
    public void pressButtonAndEnterName(@Input("name") String name) {
        assertEquals("John Doe", webOperatorImpl.clickButtonAndEnterName(name));
    }

}
