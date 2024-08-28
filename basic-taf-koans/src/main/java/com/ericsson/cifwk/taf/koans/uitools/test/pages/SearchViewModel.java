package com.ericsson.cifwk.taf.koans.uitools.test.pages;

import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.*;

public class SearchViewModel extends GenericViewModel {

    /*
     * Update the UIComponentMappings of each element to match that of the HTML page under Koan 6.
     * (hint - this can be done by using inspect element on the HTML page (src/main/resources/Koans.html) and finding the correct element mappings) 
     */
    @UiComponentMapping("#Koan6Name")
    private TextBox name;
    
    @UiComponentMapping("#koanButton4") //update with the correct ID for the Koan 6 button
    private Button koan6Button;

    @UiComponentMapping(".koan2") //update with the correct ID for the span in Koan 6 that will display the koan text
    private Label nameLabel;

    public void clickKoan6Button() {
        koan6Button.click();
    }

    public void setName(String nameText) {
        name.setText(nameText);
    }

    public String getName() {
        return name.getText();
    }

    public String getNameLabel() {
        String result = nameLabel.getText();
        String[] arr = result.split(" ", 2);
        String test = arr[1];
        return test;
    }

}
