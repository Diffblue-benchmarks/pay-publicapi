package uk.gov.pay.api.agreement.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.links.SearchNavigationLinks;

public class AgreementSearchResultsDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchResults.<init>(int, int, int, List, SearchNavigationLinks)",
    "int AgreementSearchResults.getCount()",
    "SearchNavigationLinks AgreementSearchResults.getLinks()",
    "int AgreementSearchResults.getPage()",
    "List AgreementSearchResults.getResults()",
    "int AgreementSearchResults.getTotal()"
  })
  public void testGettersAndSetters() {
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
