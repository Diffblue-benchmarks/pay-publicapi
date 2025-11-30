package uk.gov.pay.api.model.links;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostLinkDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostLink.<init>(String, String)",
    "void PostLink.<init>(String, String, String, Map)",
    "Map PostLink.getParams()",
    "String PostLink.getType()",
    "String PostLink.toString()"
  })
  public void testGettersAndSetters() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostLink.<init>(String, String)",
    "void PostLink.<init>(String, String, String, Map)",
    "Map PostLink.getParams()",
    "String PostLink.getType()",
    "String PostLink.toString()"
  })
  public void testGettersAndSetters_thenReturnToStringIsLinkHrefHrefMethodMethodTypeTypeParams() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostLink.getMethod()"})
  public void testGetMethod() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostLink.equals(Object)", "int PostLink.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new PostLink("Href", "Method"), "Different type to PostLink");
  }
}
