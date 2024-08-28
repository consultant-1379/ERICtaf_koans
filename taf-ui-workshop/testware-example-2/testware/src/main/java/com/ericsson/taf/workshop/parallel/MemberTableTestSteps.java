package com.ericsson.taf.workshop.parallel;

import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.assertions.TafAsserts;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.taf.workshop.operators.SampleWebAppUiOperator;
import com.google.common.truth.Truth;

import javax.inject.Inject;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Kirill Shepitko kirill.shepitko@ericsson.com
 *         Date: 19/05/2016
 */
public class MemberTableTestSteps extends CommonTestSteps {

    public static final String DELETE_SOME_TEAM_MEMBERS = "Delete some team members";
    public static final String NAVIGATE_USING_PAGE_BUTTONS = "Navigate using page buttons";
    public static final String REFRESH_PAGE = "Refresh page";
    public static final String MANAGE_MESSAGE_BOX = "Manage message box";
    public static final String MANAGE_DIALOG_BOX = "Manage dialog box";

    @Inject
    private SampleWebAppUiOperator operator;

    @TestStep(id = DELETE_SOME_TEAM_MEMBERS)
    public void deleteSomeTeamMembers() {
        operator.showNumberOfRecords(20);
        assertThat(operator.numberOfRecords()).isEqualTo(15);
        operator.deleteMemberRows("Ovchinnikov", "Nikolaenko");
        operator.deleteMember();
        operator.dismissMessageBox(".ebBtn.ebBtn_color_green");
        assertThat(operator.numberOfRecords()).isEqualTo( 13);
    }

    @TestStep(id = NAVIGATE_USING_PAGE_BUTTONS)
    public void navigateUsingButtons() {
        operator.waitUntilMainPageLoaded();
        assertThat(operator.getCurrentUrl()).isEqualTo(TestDataProvider.getFullMainPageUrl());
        operator.navigateBack("#Container");
        assertThat(operator.getCurrentUrl()).contains(TestDataProvider.getRelativeLoginUrl());
        operator.navigateForward(".eaMembersApp-Links");
        assertThat(operator.getCurrentUrl()).isEqualTo(TestDataProvider.getFullMainPageUrl());
    }

    @TestStep(id = REFRESH_PAGE)
    public void refreshPage() {
        operator.enterMemberDetails();
        operator.refresh(".ebInput.eaMembersApp-MemberForm-input.eaMembersApp-MemberForm-name");
        Truth.assertThat(operator.fieldsReset()).isTrue();
    }

    @TestStep(id = MANAGE_MESSAGE_BOX)
    public void manageMessageBox() {
        operator.deleteMember();
        Truth.assertThat(operator.isMessageBoxDisplayed()).isTrue();
        operator.dismissMessageBox(".ebBtn.ebBtn_color_blue");
        Truth.assertThat(operator.isMessageBoxDisplayed()).isFalse();
    }

    @TestStep(id = MANAGE_DIALOG_BOX)
    public void manageDialogBox() {
        operator.deleteMemberRows("Ovchinnikov");
        operator.deleteMember();
        operator.dismissMessageBox(".ebBtn.ebBtn_color_green");
    }

}
