package com.ericsson.taf.workshop.operators;

import com.ericsson.cifwk.taf.annotations.Operator;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.SelectorType;
import com.ericsson.cifwk.taf.ui.core.UiComponent;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import com.ericsson.cifwk.taf.ui.spi.UiActions;
import com.ericsson.taf.workshop.pages.MemberForm;
import com.ericsson.taf.workshop.pages.SearchTableViewModel;
import com.ericsson.taf.workshop.utils.BrowserDataHolder;
import org.openqa.selenium.Keys;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@Operator
public class SampleWebAppUiOperator {

    @Inject
    private BrowserDataHolder browserDataHolder;

    private BrowserTab getBrowserTab() {
        return browserDataHolder.getCurrentBrowserTab();
    }

    private void navigateToAddMemberScreen() {
        SearchTableViewModel searchTableViewModel = getSearchTableViewModel();
        searchTableViewModel.navigateToAddMemberScreen();
    }

    private SearchTableViewModel getSearchTableViewModel() {
        BrowserTab browserTab = getBrowserTab();
        return browserTab.getView(SearchTableViewModel.class);
    }

    private void setMemberValues() {
        MemberForm addMemberForm = getAddMemberForm();
        addMemberForm.setName("john", "Smith");
        addMemberForm.setEmail("john.smith@ericsson.com");
        addMemberForm.setDescription("Admiral of New England");
        addMemberForm.setRole("Scrum Master");
        addMemberForm.setTeams("ENM", "Cinema");
        addMemberForm.setGender("Male");
        addMemberForm.setHasLaptop(true);
        addMemberForm.setHasAccess(true);
    }

    private MemberForm getAddMemberForm() {
        BrowserTab browserTab = getBrowserTab();
        return browserTab.getView(MemberForm.class);
    }

    public void deleteMemberRows(String... surnames) {
        BrowserTab browserTab = getBrowserTab();
        browserTab.waitUntilComponentIsDisplayed(SelectorType.CSS, ".elTablelib-CheckboxCell", 3000);
        SearchTableViewModel searchTableViewModel = getSearchTableViewModel();
        UiComponent memberTable = searchTableViewModel.getMemberTable();
        List<UiComponent> members = memberTable.getDescendantsBySelector(".ebTableRow");
        UiActions deleteMultipleRows = browserTab
                .newActionChain().keyDown(Keys.CONTROL).click(members.get(0)).click(members.get(1)).keyUp(Keys.CONTROL);
        deleteMultipleRows.perform();
    }

    public void navigateBack(String elementToWaitFor) {
        getBrowserTab().back();
    }

    public void navigateForward(String elementToWaitFor) {
        getBrowserTab().forward();
    }

    public void enterMemberDetails() {
        navigateToAddMemberScreen();
        setMemberValues();
    }

    public void refresh(String selectorOfElementToWaitFor) {
        BrowserTab browserTab = getBrowserTab();
        browserTab.refreshPage();
        browserTab.waitUntilComponentIsDisplayed(selectorOfElementToWaitFor);
    }

    public String getCurrentUrl() {
        return getBrowserTab().getCurrentUrl();
    }

    public boolean fieldsReset() {
        MemberForm addMemberForm = getAddMemberForm();
        TextBox firstName = addMemberForm.getFirstName();
        return firstName.getText().isEmpty();
    }

    public void deleteMember() {
        SearchTableViewModel searchTableViewModel = getSearchTableViewModel();
        searchTableViewModel.getDeleteMember().click();
    }

    public void dismissMessageBox(String buttonSelector) {
        SearchTableViewModel searchTableViewModel = getSearchTableViewModel();
        UiComponent messageBox = searchTableViewModel.getMessageBox();
        List<UiComponent> childElements = messageBox.getDescendantsBySelector(SelectorType.CSS, buttonSelector);
        childElements.get(0).click();
    }

    public boolean isMessageBoxDisplayed() {
        SearchTableViewModel searchTableViewModel = getSearchTableViewModel();
        return (searchTableViewModel.getMessageBox().isDisplayed());
    }

    public int numberOfRecords() {
        SearchTableViewModel searchTableViewModel = getSearchTableViewModel();
        UiComponent memberTable = searchTableViewModel.getMemberTable();
        List<UiComponent> members = memberTable.getDescendantsBySelector(".ebTableRow");
        return members.size();
    }

    public void showNumberOfRecords(int numberOfRecords) {
        SearchTableViewModel searchTableViewModel = getSearchTableViewModel();
        searchTableViewModel.getNumberOfRecordsDropdown().click();
        List<UiComponent> options = searchTableViewModel.getNumberOfRecordsOptions();
        options.get(2).click();
    }

    public void waitUntilMainPageLoaded() {
        BrowserTab browserTab = getBrowserTab();
        ViewModel view = browserTab.getGenericView();
        assertThat(view.getViewComponent(".eaLogoutButton-link").getText(), containsString("Log Out"));
    }
}
