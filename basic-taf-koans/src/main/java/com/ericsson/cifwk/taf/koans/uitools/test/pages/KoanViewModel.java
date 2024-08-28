package com.ericsson.cifwk.taf.koans.uitools.test.pages;

import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.*;

public class KoanViewModel extends GenericViewModel {

    @UiComponentMapping("#fullname")
    private TextBox fullName;

    public void setFullName(String fullnameText) {
        fullName.setText(fullnameText);
    }

    public String getFullName() {
        return fullName.getText();
    }

    public void __(String name) {

    }

}
