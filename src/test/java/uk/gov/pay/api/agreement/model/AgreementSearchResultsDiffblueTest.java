package uk.gov.pay.api.agreement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.links.SearchNavigationLinks;

class AgreementSearchResultsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AgreementSearchResults#AgreementSearchResults(int, int, int, List,
   *       SearchNavigationLinks)}
   *   <li>{@link AgreementSearchResults#getCount()}
   *   <li>{@link AgreementSearchResults#getLinks()}
   *   <li>{@link AgreementSearchResults#getPage()}
   *   <li>{@link AgreementSearchResults#getResults()}
   *   <li>{@link AgreementSearchResults#getTotal()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchResults.<init>(int, int, int, List, SearchNavigationLinks)",
    "int AgreementSearchResults.getCount()",
    "SearchNavigationLinks AgreementSearchResults.getLinks()",
    "int AgreementSearchResults.getPage()",
    "List AgreementSearchResults.getResults()",
    "int AgreementSearchResults.getTotal()"
  })
  void testGettersAndSetters() {
    // Arrange
    ArrayList<Agreement> results = new ArrayList<>();
    SearchNavigationLinks links = new SearchNavigationLinks();

    // Act
    AgreementSearchResults actualAgreementSearchResults =
        new AgreementSearchResults(1, 3, 1, results, links);
    int actualCount = actualAgreementSearchResults.getCount();
    SearchNavigationLinks actualLinks = actualAgreementSearchResults.getLinks();
    int actualPage = actualAgreementSearchResults.getPage();
    List<Agreement> actualResults = actualAgreementSearchResults.getResults();

    // Assert
    assertEquals(1, actualPage);
    assertEquals(1, actualAgreementSearchResults.getTotal());
    assertEquals(3, actualCount);
    assertTrue(actualResults.isEmpty());
    assertSame(results, actualResults);
    assertSame(links, actualLinks);
  }
}
