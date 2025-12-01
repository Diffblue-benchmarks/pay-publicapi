package uk.gov.pay.api.model.links;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LinkDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return toString is {@code Link{href='null', method='null'}}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Link#Link()}
   *   <li>{@link Link#toString()}
   *   <li>{@link Link#getHref()}
   *   <li>{@link Link#getMethod()}
   * </ul>
   */
  @Test
  @DisplayName(
      "Test getters and setters; then return toString is 'Link{href='null', method='null'}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Link.<init>()",
    "void Link.<init>(String)",
    "void Link.<init>(String, String)",
    "String Link.getHref()",
    "String Link.getMethod()",
    "String Link.toString()"
  })
  void testGettersAndSetters_thenReturnToStringIsLinkHrefNullMethodNull() {
    // Arrange and Act
    Link actualLink = new Link();
    String actualToStringResult = actualLink.toString();
    String actualHref = actualLink.getHref();

    // Assert
    assertEquals("Link{href='null', method='null'}", actualToStringResult);
    assertNull(actualHref);
    assertNull(actualLink.getMethod());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Href}.
   *   <li>Then return toString is {@code Link{href='Href', method='null'}}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Link#Link(String)}
   *   <li>{@link Link#toString()}
   *   <li>{@link Link#getHref()}
   *   <li>{@link Link#getMethod()}
   * </ul>
   */
  @Test
  @DisplayName(
      "Test getters and setters; when 'Href'; then return toString is 'Link{href='Href', method='null'}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Link.<init>()",
    "void Link.<init>(String)",
    "void Link.<init>(String, String)",
    "String Link.getHref()",
    "String Link.getMethod()",
    "String Link.toString()"
  })
  void testGettersAndSetters_whenHref_thenReturnToStringIsLinkHrefHrefMethodNull() {
    // Arrange and Act
    Link actualLink = new Link("Href");
    String actualToStringResult = actualLink.toString();
    String actualHref = actualLink.getHref();

    // Assert
    assertEquals("Href", actualHref);
    assertEquals("Link{href='Href', method='null'}", actualToStringResult);
    assertNull(actualLink.getMethod());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Method}.
   *   <li>Then return toString is {@code Link{href='Href', method='Method'}}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Link#Link(String, String)}
   *   <li>{@link Link#toString()}
   *   <li>{@link Link#getHref()}
   *   <li>{@link Link#getMethod()}
   * </ul>
   */
  @Test
  @DisplayName(
      "Test getters and setters; when 'Method'; then return toString is 'Link{href='Href', method='Method'}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Link.<init>()",
    "void Link.<init>(String)",
    "void Link.<init>(String, String)",
    "String Link.getHref()",
    "String Link.getMethod()",
    "String Link.toString()"
  })
  void testGettersAndSetters_whenMethod_thenReturnToStringIsLinkHrefHrefMethodMethod() {
    // Arrange and Act
    Link actualLink = new Link("Href", "Method");
    String actualToStringResult = actualLink.toString();
    String actualHref = actualLink.getHref();

    // Assert
    assertEquals("Href", actualHref);
    assertEquals("Link{href='Href', method='Method'}", actualToStringResult);
    assertEquals("Method", actualLink.getMethod());
  }

  /**
   * Test {@link Link#equals(Object)}, and {@link Link#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Link#equals(Object)}
   *   <li>{@link Link#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Link.equals(Object)", "int Link.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Link link = new Link("Href");
    Link link2 = new Link("Href");

    // Act and Assert
    assertEquals(link, link2);
    assertEquals(link.hashCode(), link2.hashCode());
  }

  /**
   * Test {@link Link#equals(Object)}, and {@link Link#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Link#equals(Object)}
   *   <li>{@link Link#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Link.equals(Object)", "int Link.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Link link = new Link("Href");

    // Act and Assert
    assertEquals(link, link);
    int expectedHashCodeResult = link.hashCode();
    assertEquals(expectedHashCodeResult, link.hashCode());
  }

  /**
   * Test {@link Link#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Link#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Link.equals(Object)", "int Link.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Link link = new Link(null);

    // Act and Assert
    assertNotEquals(link, new Link("Href"));
  }

  /**
   * Test {@link Link#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Link#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Link.equals(Object)", "int Link.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Link link = new Link("Href", "Method");

    // Act and Assert
    assertNotEquals(link, new Link("Href"));
  }

  /**
   * Test {@link Link#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Link#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Link.equals(Object)", "int Link.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Link("Href"), null);
  }

  /**
   * Test {@link Link#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Link#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Link.equals(Object)", "int Link.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Link("Href"), "Different type to Link");
  }
}
