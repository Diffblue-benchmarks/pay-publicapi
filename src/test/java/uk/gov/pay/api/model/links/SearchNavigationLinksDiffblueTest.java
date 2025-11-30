package uk.gov.pay.api.model.links;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SearchNavigationLinksDiffblueTest {
  /**
   * Test {@link SearchNavigationLinks#withSelfLink(String)}.
   *
   * <p>Method under test: {@link SearchNavigationLinks#withSelfLink(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchNavigationLinks SearchNavigationLinks.withSelfLink(String)"})
  public void testWithSelfLink() {
    // Arrange
    SearchNavigationLinks searchNavigationLinks = new SearchNavigationLinks();

    // Act
    SearchNavigationLinks actualWithSelfLinkResult = searchNavigationLinks.withSelfLink("Href");

    // Assert
    assertSame(searchNavigationLinks, actualWithSelfLinkResult);
  }

  /**
   * Test {@link SearchNavigationLinks#withPrevLink(String)}.
   *
   * <p>Method under test: {@link SearchNavigationLinks#withPrevLink(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchNavigationLinks SearchNavigationLinks.withPrevLink(String)"})
  public void testWithPrevLink() {
    // Arrange
    SearchNavigationLinks searchNavigationLinks = new SearchNavigationLinks();

    // Act
    SearchNavigationLinks actualWithPrevLinkResult = searchNavigationLinks.withPrevLink("Href");

    // Assert
    assertSame(searchNavigationLinks, actualWithPrevLinkResult);
  }

  /**
   * Test {@link SearchNavigationLinks#withNextLink(String)}.
   *
   * <p>Method under test: {@link SearchNavigationLinks#withNextLink(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchNavigationLinks SearchNavigationLinks.withNextLink(String)"})
  public void testWithNextLink() {
    // Arrange
    SearchNavigationLinks searchNavigationLinks = new SearchNavigationLinks();

    // Act
    SearchNavigationLinks actualWithNextLinkResult = searchNavigationLinks.withNextLink("Href");

    // Assert
    assertSame(searchNavigationLinks, actualWithNextLinkResult);
  }

  /**
   * Test {@link SearchNavigationLinks#withFirstLink(String)}.
   *
   * <p>Method under test: {@link SearchNavigationLinks#withFirstLink(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchNavigationLinks SearchNavigationLinks.withFirstLink(String)"})
  public void testWithFirstLink() {
    // Arrange
    SearchNavigationLinks searchNavigationLinks = new SearchNavigationLinks();

    // Act
    SearchNavigationLinks actualWithFirstLinkResult = searchNavigationLinks.withFirstLink("Href");

    // Assert
    assertSame(searchNavigationLinks, actualWithFirstLinkResult);
  }

  /**
   * Test {@link SearchNavigationLinks#withLastLink(String)}.
   *
   * <p>Method under test: {@link SearchNavigationLinks#withLastLink(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SearchNavigationLinks SearchNavigationLinks.withLastLink(String)"})
  public void testWithLastLink() {
    // Arrange
    SearchNavigationLinks searchNavigationLinks = new SearchNavigationLinks();

    // Act
    SearchNavigationLinks actualWithLastLinkResult = searchNavigationLinks.withLastLink("Href");

    // Assert
    assertSame(searchNavigationLinks, actualWithLastLinkResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SearchNavigationLinks}
   *   <li>{@link SearchNavigationLinks#getFirstPage()}
   *   <li>{@link SearchNavigationLinks#getLastPage()}
   *   <li>{@link SearchNavigationLinks#getNextPage()}
   *   <li>{@link SearchNavigationLinks#getPrevPage()}
   *   <li>{@link SearchNavigationLinks#getSelf()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SearchNavigationLinks.<init>()",
    "Link SearchNavigationLinks.getFirstPage()",
    "Link SearchNavigationLinks.getLastPage()",
    "Link SearchNavigationLinks.getNextPage()",
    "Link SearchNavigationLinks.getPrevPage()",
    "Link SearchNavigationLinks.getSelf()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SearchNavigationLinks actualSearchNavigationLinks = new SearchNavigationLinks();
    Link actualFirstPage = actualSearchNavigationLinks.getFirstPage();
    Link actualLastPage = actualSearchNavigationLinks.getLastPage();
    Link actualNextPage = actualSearchNavigationLinks.getNextPage();
    Link actualPrevPage = actualSearchNavigationLinks.getPrevPage();

    // Assert
    assertNull(actualFirstPage);
    assertNull(actualLastPage);
    assertNull(actualNextPage);
    assertNull(actualPrevPage);
    assertNull(actualSearchNavigationLinks.getSelf());
  }
}
