package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

import com.ericsson.cifwk.taf.ui.core.AbstractUiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;

/**
 * This is composite UI component.
 *
 * Please notice that we don't extend GenericViewModel any more, but AbstractUiComponent instead.
 *
 * Fields to be mapped are searched not everywhere on the page
 * (like in case of regular View Models),
 * but in page sub-region which this composite component is mapped to.
 *
 * There is no other differences with view model.
 */
public class AddressForm extends AbstractUiComponent {

    @UiComponentMapping(".city")
    private TextBox city;

    @UiComponentMapping(".street")
    private TextBox street;

    @UiComponentMapping(".zip")
    private TextBox zip;

    public void setAddress(AddressPojo address) {
        city.setText(address.getCity());
        street.setText(address.getStreet());

        // in some forms zip element is optional
        // TODO: please make this method work all the time (without try-catching), e.g. when there is no (optional) zip field on the page
        zip.setText(address.getZip());
    }

}
