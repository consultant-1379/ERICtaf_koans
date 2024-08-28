package com.ericsson.cifwk.taf.ui.workshop.uisdk;

import com.ericsson.cds.uisdk.compositecomponents.table.lib.HeadCell;
import com.ericsson.cds.uisdk.compositecomponents.table.lib.SortDirection;
import com.ericsson.cds.uisdk.compositecomponents.table.lib.Table;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.SelectorType;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.google.common.base.Function;
import org.junit.Test;

import java.util.List;

import static com.ericsson.cds.uisdk.compositecomponents.table.lib.SortDirection.ASC;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class UiSdk4TableExample1 extends BaseWorkshopTest {

    // TODO: open this page in browser and observe DOM structure of the component (using dev tools of your browser)
    public static final String PAGE = "http://cifwk-oss.lmera.ericsson.se:8081/nexus/content/sites/tor/tablelib/latest/examples/#example1";

    // TODO: fix mapping
    public static final String TABLE_CONTAINER = "//div[contains(text(),'Example 1')]/..";

    /**
     * This test demonstrates basic operations of work with Table via custom View Model
     */
    @Test
    public void tableBasicOperations() {
        BrowserTab browserTab = browser.open(PAGE);

        // Using Custom View of the page
        // TODO: please complete tasks inside MyViewModel class
        MyViewModel tableSection = browserTab.getView(MyViewModel.class);

        // checking column names
        List<String> columnNames = tableSection.getColumnNames();
        assertThat(columnNames).containsExactly("First Name", "Last Name", "Occupation");

        // sorting the table
        tableSection.sort("First Name");
        assertThat(tableSection.getSortDirection("First Name")).isEqualTo(ASC);

        // reversing sorting order
        tableSection.sort("first name");
        // TODO: fix the test
        assertThat(tableSection.getSortDirection("first name")).isEqualTo(null);
    }

    public static class MyViewModel extends GenericViewModel {

        @UiComponentMapping(selectorType = SelectorType.XPATH, selector = TABLE_CONTAINER)
        private Table myTable;

        private List<String> columnNamesCached;

        // caching slow UI operations
        public List<String> getColumnNames() {
            // TODO: please implement this method
            return null;
        }

        private List<String> getColumnNamesSlowly() {
            return newArrayList(transform(myTable.getHeadRow().getCells(), new Function<HeadCell, String>() {
                @Override
                public String apply(HeadCell cell) {
                    return cell.getText();
                }
            }));
        }

        public void sort(String columnLabel) {
            myTable.sortByColumn(getColumnIndex(columnLabel));
        }

        public SortDirection getSortDirection(String columnLabel) {
            // TODO: please implement it
            return null;
        }

        private int getColumnIndex(String columnLabel) {
            List<String> columnNames = getColumnNames();
            for (int index = 0; index < columnNames.size(); index++) {
                if (columnNames.get(index).equalsIgnoreCase(columnLabel)) {
                    return index;
                }
            }
            throw new RuntimeException(String.format("Column '%s' is not found within columns: %s", columnLabel, columnNames));
        }

    }

}
