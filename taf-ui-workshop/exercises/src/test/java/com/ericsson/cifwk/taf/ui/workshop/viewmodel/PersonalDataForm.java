package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

import com.ericsson.cifwk.taf.ui.core.AbstractUiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;

/**
 * @author Mihails Volkovs mihails.volkovs@ericsson.com
 *         Date: 03.06.2016
 */
public class PersonalDataForm extends AbstractUiComponent {

    @UiComponentMapping(".firstName")
    private TextBox firstNameField;

    @UiComponentMapping(".lastName")
    private TextBox lastNameField;

    public void setUserNames(String firstName, String lastName) {
        firstNameField.setText(firstName);
        lastNameField.setText(lastName);
    }

}
