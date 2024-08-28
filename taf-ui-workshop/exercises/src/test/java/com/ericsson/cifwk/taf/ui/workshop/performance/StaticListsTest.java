package com.ericsson.cifwk.taf.ui.workshop.performance;

import com.ericsson.cifwk.taf.ui.core.GenericPredicate;
import com.ericsson.cifwk.taf.ui.core.StaticList;
import com.ericsson.cifwk.taf.ui.core.UiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.core.UiComponentPredicates;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Label;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import com.ericsson.cifwk.taf.ui.workshop.basic.BasicComponentsTest;
import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Mihails Volkovs mihails.volkovs@ericsson.com
 *         Date: 23.09.2016
 */
public class StaticListsTest extends BasicComponentsTest {

    private static final Logger LOG = LoggerFactory.getLogger(StaticListsTest.class);

    // TODO: please open the page in your browser and review its DOM structure (using dev tools of your browser)
    public static final String PAGE = "static-lists.html";

    @Override
    public String getPage() {
        return PAGE;
    }

    /**
     * This test demonstrates that static list may be x10+ faster than regular list (even on your local machine, on Selenium grid the difference could be even greater taking into account network extra round trips).
     */
    @Test
    public void staticListPerformance() {

        // initializing views
        waitForItemsLoaded(view);
        // TODO: please go inside RegularListViewModel and StaticListViewModel to complete exercise
        HasItems regularView = browserTab.getView(RegularListViewModel.class);
        HasItems staticView = browserTab.getView(StaticListViewModel.class);

        // collecting test results
        List<Long> millis = Lists.newArrayList();
        millis.add(test(staticView));
        millis.add(test(regularView));
        millis.add(test(staticView));
        millis.add(test(regularView));

        // printing test results
        int index = 0;
        LOG.info("Static (millis): " + millis.get(index++));
        LOG.info("Regular (millis): " + millis.get(index++));
        LOG.info("Static (millis): " + millis.get(index++));
        LOG.info("Regular (millis): " + millis.get(index++));
    }

    /**
     * This test demonstrates the differences between regular component lists (dynamic lists) and static lists.
     */
    @Test
    public void staticVsDynamicList() {

        // initializing views
        HasItems regularView = browserTab.getView(RegularListViewModel.class);
        HasItems staticView = browserTab.getView(StaticListViewModel.class);
        assertThat(regularView.itemsCount()).isEqualByComparingTo(0);
        // TODO: please correct expected value
        Integer expected = null;
        assertThat(staticView.itemsCount()).isEqualByComparingTo(expected);

        // regular lists get updated automatically
        waitForItemsLoaded(view);
        assertThat(regularView.itemsCount()).isEqualByComparingTo(10);
        assertThat(staticView.itemsCount()).isEqualByComparingTo(0);

        // for static list to get updated you have to reinitialize model manually
        staticView = browserTab.getView(StaticListViewModel.class);
        // TODO: please correct expected value
        assertThat(staticView.itemsCount()).isEqualByComparingTo(null);
    }

    /**
     * This test demonstrates how waiting for initialization if static list can be encapsulated into view model.
     */
    @Test
    public void transparentInitOfStaticList() {

        // initializing views
        // TODO: please go inside model to complete tasks
        InitializedStaticListViewModel page = browserTab.getView(InitializedStaticListViewModel.class);

        Integer expected = 10;
        assertThat(page.itemsCount()).isEqualByComparingTo(expected);

        // Static lists was initialized with 10 items.
        // If we try to update the LIST SIZE - it WON'T BE REFLECTED in the model:
        page.updateList();
        assertThat(page.itemsCount()).isEqualByComparingTo(10);

        // reinitializing the view to see the actual list size
        page = browserTab.getView(InitializedStaticListViewModel.class);
        // TODO: please correct expected value
        assertThat(page.itemsCount()).isEqualByComparingTo(null);

        // But static lists contains UI components which are still dynamic.
        // So if you just update the LIST ITEM - it WILL BE REFLECTED in the model:
        Assertions.assertThat(page.getFirstItem()).isEqualTo("item 1");
        page.updateListItem();
        // TODO: please correct expected value
        Assertions.assertThat(page.getFirstItem()).isEqualTo(null);

    }

    private long test(HasItems itemsContainer) {
        long now = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            itemsContainer.itemsCount();
        }
        return (System.nanoTime() - now) / 1000000;
    }

    public static class RegularListViewModel extends GenericViewModel implements HasItems {

        // TODO: please map table items
        private List<Label> items;

        @Override
        public int itemsCount() {
            return items.size();
        }

    }

    public static class StaticListViewModel extends GenericViewModel implements HasItems {

        // TODO: make this list static (hint: please do not use global setting overriding type of every list in your testware, use static list annotation instead)
        @UiComponentMapping(".item")
        private List<Label> items;

        @Override
        public int itemsCount() {
            return items.size();
        }

    }

    public static class InitializedStaticListViewModel extends GenericViewModel implements HasItems {

        @UiComponentMapping(".item")
        // this mapping is used for static list initialization only
        private List<Label> dynamicItems;

        @StaticList
        @UiComponentMapping(".item")
        private List<Label> items;

        @UiComponentMapping("#updateListButton")
        private Button updateListButton;

        @UiComponentMapping("#updateListItemButton")
        private Button updateListItemButton;

        @Override
        public int itemsCount() {
            waitForItemsInitialization();

            // on first usage of static list its content gets fixed
            return items.size();
        }

        private void waitForItemsInitialization() {
            waitUntil(new GenericPredicate(){
                @Override
                public boolean apply() {
                    // TODO: implement waiting until list is not empty
                    return true;
                }
            });
        }

        public void updateList() {
            updateListButton.click();
        }

        public void updateListItem() {
            updateListItemButton.click();
        }

        public String getFirstItem() {

            // any method returning list content requires list initialization
            waitForItemsInitialization();

            // items list is not empty - ensured by the method above
            return items.iterator().next().getText();

        }

    }

    public interface HasItems {

        int itemsCount();

    }

    private static void waitForItemsLoaded(ViewModel view) {
        UiComponent loadingIndicator = view.getViewComponent("#initialPageLoading");
        view.waitUntil(loadingIndicator, UiComponentPredicates.HIDDEN);
    }

}
