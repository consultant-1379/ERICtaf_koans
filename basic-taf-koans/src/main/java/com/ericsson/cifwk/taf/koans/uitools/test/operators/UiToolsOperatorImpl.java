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
package com.ericsson.cifwk.taf.koans.uitools.test.operators;

import javax.inject.Singleton;

import com.ericsson.cifwk.taf.annotations.Operator;
import com.ericsson.cifwk.taf.koans.uitools.test.helper.UiKoanHelper.GenericViewModel;
import com.ericsson.cifwk.taf.koans.uitools.test.pages.KoanViewModel;
import com.ericsson.cifwk.taf.koans.uitools.test.pages.SearchViewModel;
import com.ericsson.cifwk.taf.ui.*;
import com.ericsson.cifwk.taf.ui.sdk.*;

@Operator
@Singleton
public class UiToolsOperatorImpl {

    private Browser browser;
    private BrowserTab browserTab;
    private GenericViewModel genericView = new GenericViewModel();
    private KoanViewModel koanViewModel = new KoanViewModel();
    private ViewModel genericViewModel;
    private String testPage;

    public void setUpBrowser() {
        testPage = "http://atclvm1170.athtem.eei.ericsson.se:8080/Koans.html";
        this.browser = UI.newBrowser(BrowserType.CHROME);
        this.browserTab = browser.open(testPage);
    }

    /*
     * Update the code with the correct methods for getting a generic view model and a UI Button component
     */
    public String getButtonText() {
        //genericViewModel = browserTab.__();
        //Button button = genericViewModel.__(SelectorType.XPATH, "/html/body/div/div[3]/div/div[1]/div[1]/button");
        String buttonText = "";
        //buttonText = button.getText();
        return buttonText;
    }

    /*
     * Update the code with the correct method to get a UI Label component.
     * You will also need to find the CSS value of the Label under Koan 2 from the HTML page and replace it with the CSS that
     * is already entered. The Koans.html file can be found in the src/main/resources folder
     */
    public String getLabelText() {
        genericView = browserTab.getView(GenericViewModel.class);
        //Label label = genericView.__(SelectorType.CSS, "");
        String labelText = "";
        //labelText = label.getText();
        return labelText;
    }

    /*
     * Update the code with the correct method to click a UI Button
     */
    public String getTextWhenButtonPressed() {
        genericViewModel = browserTab.getGenericView();
        Button button = genericViewModel.getButton("#koanButton3");
        //button.__();
        Label label = genericViewModel.getLabel("#koanText3");
        String koan3Text = "";
        //koan3Text = label.getText();
        return koan3Text;
    }

    /*
     * Update the code with the correct methods to fill in text into the text boxes and click on a button.
     */
    public String enterNameAndSignum(String name, String signum) {
        genericView = browserTab.getView(GenericViewModel.class);
        TextBox fullName = genericView.getTextBox("#name");
        //fullName.__(name);
        TextBox signumBox = genericView.getTextBox("#signum");
        //signumBox.__(signum);
        Button submit = genericView.getButton("#koanButton4");
        submit.click();
        String result = fullName.getText() + " " + signumBox.getText();
        return result;
    }

    /*
     * Update the code with the correct methods to get a view model and set the text box with the "name" variable
     */
    public String enterNameKoan5(String name) {
        //koanViewModel = browserTab.__(KoanViewModel.class);
        koanViewModel.__(name);
        String result = "";
        //result = koanViewModel.getFullName();
        return result;
    }

    /*
     * In this method you will have to update the SearchViewModel.java class to find the
     * appropriate UI element mappings
     */
    public String clickButtonAndEnterName(String name) {
        SearchViewModel searchViewModel = browserTab.getView(SearchViewModel.class);
        searchViewModel.setName(name);
        searchViewModel.clickKoan6Button();
        String result = searchViewModel.getNameLabel();
        return result;
    }
}
