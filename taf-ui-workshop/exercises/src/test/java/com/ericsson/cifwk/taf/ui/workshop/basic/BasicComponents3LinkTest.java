package com.ericsson.cifwk.taf.ui.workshop.basic;

import com.ericsson.cifwk.taf.ui.core.UiComponentPredicates;
import com.ericsson.cifwk.taf.ui.sdk.Link;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BasicComponents3LinkTest extends BasicComponentsTest {

    // TODO: please open the page in your browser and review its DOM structure (using dev tools of your browser)
    public static final String PAGE = "basic-ui-components.html";

    /**
     * This test demonstrates how anchor link URLs could be retrieved and navigated.
     */
    @Test
    public void anchorLink() {

        // TODO: initialize anchor link (from Links panel) by content
        Link link = null;

        // please DO NOT MODIFY existing code if there is no to-do comments right above the line
        Assertions.assertThat(link.getText()).isEqualTo("Anchor link");

        // testing link reference
        Assertions.assertThat(link.getUrl()).isEqualTo("http://www.ericsson.com/");

        // TODO: navigate the link

        // waiting for Ericsson logo to appear on the page
        // async operations will be demonstrated in more details later
        view.waitUntil(link, UiComponentPredicates.HIDDEN);
        view.waitUntilComponentIsDisplayed("#eHeaderLogo");

        Assertions.assertThat(browserTab.getCurrentUrl()).isEqualTo("https://www.ericsson.com/");
    }

    /**
     * This test demonstrates how meta link URLs could be retrieved.
     */
    @Test
    public void metaLink() {

        // initializing meta link element (from HTML head tag)
        Link link = view.getLink("#metaLink");

        // TODO: check link URL in HTML (be aware that link URLs might be normalized into absolute ones)
        // for simplicity you can make it work on your machine ONLY
        String linkUrl = null;
        Assertions.assertThat(link.getUrl()).isEqualTo(linkUrl);

    }

}
