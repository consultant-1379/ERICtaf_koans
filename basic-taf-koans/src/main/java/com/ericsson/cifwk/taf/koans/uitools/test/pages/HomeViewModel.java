package com.ericsson.cifwk.taf.koans.uitools.test.pages;

import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.*;

public class HomeViewModel extends GenericViewModel {

    @UiComponentMapping("body > div > div.qa-part-q-list > div > div:nth-child(2) > div.koans-main > span")
    private Label spanKoan2;

    @UiComponentMapping("#koanButton3")
    private Button koan3Button;

    @UiComponentMapping("#koanText3 > h3:nth-child(2)")
    private Label koan3Text;

    @UiComponentMapping("#name")
    private TextBox name;

    @UiComponentMapping("#signum")
    private TextBox signum;

    @UiComponentMapping("#koanButton4")
    private Button koan4Button;

    public String getTextOfLabel() {
        return spanKoan2.getText();
    }

    public void clickKoan3Button() {
        koan3Button.click();
    }

    public String getTextAfterKoan3ButtonIsPressed() {
        return koan3Text.getText();
    }

    public void setNameAndSignum(String fullname, String fullsignum) {
        name.setText(fullname);
        signum.setText(fullsignum);
    }

    public String getNameText() {
        return name.getText();
    }

    public String getSignumText() {
        return signum.getText();
    }

    public void clickKoan4Button() {
        koan4Button.click();
    }

    public String __() {
        return "";
    }

    public void __(String name2, String signum2) {

    }

}
