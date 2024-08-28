package com.ericsson.cifwk.taf.ui.workshop.uisdk;

import com.ericsson.cds.uisdk.compositecomponents.tree.Tree;
import com.ericsson.cds.uisdk.compositecomponents.tree.TreeItem;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import org.junit.Test;

import static com.ericsson.cifwk.taf.ui.core.SelectorType.XPATH;
import static org.assertj.core.api.Assertions.assertThat;

public class UiSdk3Tree extends BaseWorkshopTest {

    // TODO: open this page in browser and observe DOM structure of the component (using dev tools of your browser)
    public static final String PAGE = "https://arm1s11-eiffel004.eiffel.gic.ericsson.se:8443/nexus/content/sites/tor/widgets/latest/showcase/index.html#ui-showcase/layout-components/tree";

    public static final String TREE_CONTAINER = "//div[contains(text(),'Tree widget which allows')]/..";

    /**
     * This test demonstrates how to work with Tree via custom View Model
     */
    @Test
    public void driveTreeViaViewModel() {
        BrowserTab browserTab = browser.open(PAGE);

        // Using Custom View of the page
        // TODO: please complete tasks inside MyViewModel class
        MyViewModel treeSection = browserTab.getView(MyViewModel.class);

        TreeItem item1 = treeSection.getItem1();

        // TODO: assert that item 1 is partially checked
        assertThat(false).isEqualTo(true);

        TreeItem item2 = treeSection.getItem2();
        treeSection.select(item2);

        // asserting that item2 is selected, but is NOT checked
        assertThat(item2.isSelected()).isEqualTo(true);
        // TODO: fix the test
        assertThat(item2.isChecked()).isEqualTo(true);

        // checking child item makes parent checked as well
        assertThat(item1.isChecked()).isFalse();
        treeSection.check("Item 1.1.2");
        // TODO: fix the test
        assertThat(item1.isChecked()).isFalse();
    }

    public static class MyViewModel extends GenericViewModel {

        private static final String ITEM_1_1 = "Item 1.1";

        private static final String ITEM_1_2 = "Item 1.2";

        @UiComponentMapping(selectorType = XPATH, selector = TREE_CONTAINER)
        private Tree myTree;

        public TreeItem getItem1() {
            // TODO: return "Item 1.1" (via Tree API)
            return null;
        }

        public TreeItem getItem2() {
            return myTree.findItem(ITEM_1_2);
        }

        public void check(String itemLabel) {
            // TODO: please implement it
        }

        public boolean isChecked(String itemLabel) {
            return getItem(itemLabel).isChecked();
        }

        public void select(TreeItem item) {
            // TODO: implement item selection (via Tree Item API or via regular click)
        }

        private TreeItem getItem(String itemLabel) {
            return myTree.findItem(itemLabel);
        }

    }

}
