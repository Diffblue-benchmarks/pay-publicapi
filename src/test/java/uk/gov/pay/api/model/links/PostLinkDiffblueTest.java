package uk.gov.pay.api.model.links;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PostLinkDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostLink#PostLink(String, String)}
   *   <li>{@link PostLink#toString()}
   *   <li>{@link PostLink#getParams()}
   *   <li>{@link PostLink#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostLink.<init>(String, String)",
    "void PostLink.<init>(String, String, String, Map)",
    "Map PostLink.getParams()",
    "String PostLink.getType()",
    "String PostLink.toString()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    PostLink actualPostLink = new PostLink("Href", "Method");
    String actualToStringResult = actualPostLink.toString();
    Map<String, Object> actualParams = actualPostLink.getParams();
    String actualType = actualPostLink.getType();

    // Assert
    assertEquals("Href", actualPostLink.getHref());
    assertEquals(
        "Link{href='Href', method='Method', type='null', params=null}", actualToStringResult);
    assertNull(actualType);
    assertNull(actualParams);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return toString is {@code Link{href='Href', method='Method', type='Type',
   *       params={}}}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostLink#PostLink(String, String, String, Map)}
   *   <li>{@link PostLink#toString()}
   *   <li>{@link PostLink#getParams()}
   *   <li>{@link PostLink#getType()}
   * </ul>
   */
  @Test
  @DisplayName(
      "Test getters and setters; then return toString is 'Link{href='Href', method='Method', type='Type', params={}}'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostLink.<init>(String, String)",
    "void PostLink.<init>(String, String, String, Map)",
    "Map PostLink.getParams()",
    "String PostLink.getType()",
    "String PostLink.toString()"
  })
  void testGettersAndSetters_thenReturnToStringIsLinkHrefHrefMethodMethodTypeTypeParams() {
    // Arrange
    HashMap<String, Object> params = new HashMap<>();

    // Act
    PostLink actualPostLink = new PostLink("Href", "Method", "Type", params);
    String actualToStringResult = actualPostLink.toString();
    Map<String, Object> actualParams = actualPostLink.getParams();
    String actualType = actualPostLink.getType();

    // Assert
    assertEquals("Href", actualPostLink.getHref());
    assertEquals(
        "Link{href='Href', method='Method', type='Type', params={}}", actualToStringResult);
    assertEquals("Type", actualType);
    assertTrue(actualParams.isEmpty());
    assertSame(params, actualParams);
  }

  /**
   * Test {@link PostLink#getMethod()}.
   *
   * <p>Method under test: {@link PostLink#getMethod()}
   */
  @Test
  @DisplayName("Test getMethod()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostLink.getMethod()"})
  void testGetMethod() {
    // Arrange, Act and Assert
    assertEquals("Method", new PostLink("Href", "Method").getMethod());
  }

  /**
   * Test {@link PostLink#equals(Object)}, and {@link PostLink#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostLink#equals(Object)}
   *   <li>{@link PostLink#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    PostLink postLink = new PostLink("Href", "Method");
    PostLink postLink2 = new PostLink("Href", "Method");

    // Act and Assert
    assertEquals(postLink, postLink2);
    assertEquals(postLink.hashCode(), postLink2.hashCode());
  }

  /**
   * Test {@link PostLink#equals(Object)}, and {@link PostLink#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostLink#equals(Object)}
   *   <li>{@link PostLink#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    PostLink postLink = new PostLink("Href", "Method");

    // Act and Assert
    assertEquals(postLink, postLink);
    int expectedHashCodeResult = postLink.hashCode();
    assertEquals(expectedHashCodeResult, postLink.hashCode());
  }

  /**
   * Test {@link PostLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PostLink#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    PostLink postLink = new PostLink(null, "Method");

    // Act and Assert
    assertNotEquals(postLink, new PostLink("Href", "Method"));
  }

  /**
   * Test {@link PostLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PostLink#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    PostLink postLink = new PostLink("Href", "Method", "Type", new HashMap<>());

    // Act and Assert
    assertNotEquals(postLink, new PostLink("Href", "Method"));
  }

  /**
   * Test {@link PostLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PostLink#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    PostLink postLink = new PostLink("Href", "Method", null, new HashMap<>());

    // Act and Assert
    assertNotEquals(postLink, new PostLink("Href", "Method"));
  }

  /**
   * Test {@link PostLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PostLink#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new PostLink("Href", "Method"), null);
  }

  /**
   * Test {@link PostLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PostLink#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new PostLink("Href", "Method"), "Different type to PostLink");
  }
}
