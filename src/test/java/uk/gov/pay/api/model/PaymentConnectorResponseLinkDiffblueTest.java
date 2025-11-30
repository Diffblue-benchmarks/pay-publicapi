package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PaymentConnectorResponseLinkDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentConnectorResponseLink#PaymentConnectorResponseLink(String, String, String,
   *       String, Map)}
   *   <li>{@link PaymentConnectorResponseLink#getHref()}
   *   <li>{@link PaymentConnectorResponseLink#getMethod()}
   *   <li>{@link PaymentConnectorResponseLink#getParams()}
   *   <li>{@link PaymentConnectorResponseLink#getRel()}
   *   <li>{@link PaymentConnectorResponseLink#getType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentConnectorResponseLink.<init>(String, String, String, String, Map)",
    "String PaymentConnectorResponseLink.getHref()",
    "String PaymentConnectorResponseLink.getMethod()",
    "Map PaymentConnectorResponseLink.getParams()",
    "String PaymentConnectorResponseLink.getRel()",
    "String PaymentConnectorResponseLink.getType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HashMap<String, Object> params = new HashMap<>();

    // Act
    PaymentConnectorResponseLink actualPaymentConnectorResponseLink =
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", params);
    String actualHref = actualPaymentConnectorResponseLink.getHref();
    String actualMethod = actualPaymentConnectorResponseLink.getMethod();
    Map<String, Object> actualParams = actualPaymentConnectorResponseLink.getParams();
    String actualRel = actualPaymentConnectorResponseLink.getRel();

    // Assert
    assertEquals("Href", actualHref);
    assertEquals("Method", actualMethod);
    assertEquals("Rel", actualRel);
    assertEquals("Type", actualPaymentConnectorResponseLink.getType());
    assertTrue(actualParams.isEmpty());
    assertSame(params, actualParams);
  }

  /**
   * Test {@link PaymentConnectorResponseLink#equals(Object)}, and {@link
   * PaymentConnectorResponseLink#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentConnectorResponseLink#equals(Object)}
   *   <li>{@link PaymentConnectorResponseLink#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentConnectorResponseLink.equals(Object)",
    "int PaymentConnectorResponseLink.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>());
    PaymentConnectorResponseLink paymentConnectorResponseLink2 =
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>());

    // Act and Assert
    assertEquals(paymentConnectorResponseLink, paymentConnectorResponseLink2);
    assertEquals(paymentConnectorResponseLink.hashCode(), paymentConnectorResponseLink2.hashCode());
  }

  /**
   * Test {@link PaymentConnectorResponseLink#equals(Object)}, and {@link
   * PaymentConnectorResponseLink#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentConnectorResponseLink#equals(Object)}
   *   <li>{@link PaymentConnectorResponseLink#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentConnectorResponseLink.equals(Object)",
    "int PaymentConnectorResponseLink.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>());

    // Act and Assert
    assertEquals(paymentConnectorResponseLink, paymentConnectorResponseLink);
    int expectedHashCodeResult = paymentConnectorResponseLink.hashCode();
    assertEquals(expectedHashCodeResult, paymentConnectorResponseLink.hashCode());
  }

  /**
   * Test {@link PaymentConnectorResponseLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentConnectorResponseLink#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentConnectorResponseLink.equals(Object)",
    "int PaymentConnectorResponseLink.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink(null, "Href", "Method", "Type", new HashMap<>());

    // Act and Assert
    assertNotEquals(
        paymentConnectorResponseLink,
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>()));
  }

  /**
   * Test {@link PaymentConnectorResponseLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentConnectorResponseLink#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentConnectorResponseLink.equals(Object)",
    "int PaymentConnectorResponseLink.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("Rel", null, "Method", "Type", new HashMap<>());

    // Act and Assert
    assertNotEquals(
        paymentConnectorResponseLink,
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>()));
  }

  /**
   * Test {@link PaymentConnectorResponseLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentConnectorResponseLink#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentConnectorResponseLink.equals(Object)",
    "int PaymentConnectorResponseLink.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("Rel", "Href", null, "Type", new HashMap<>());

    // Act and Assert
    assertNotEquals(
        paymentConnectorResponseLink,
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>()));
  }

  /**
   * Test {@link PaymentConnectorResponseLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentConnectorResponseLink#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentConnectorResponseLink.equals(Object)",
    "int PaymentConnectorResponseLink.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("Rel", "Href", "Method", null, new HashMap<>());

    // Act and Assert
    assertNotEquals(
        paymentConnectorResponseLink,
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>()));
  }

  /**
   * Test {@link PaymentConnectorResponseLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentConnectorResponseLink#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentConnectorResponseLink.equals(Object)",
    "int PaymentConnectorResponseLink.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    HashMap<String, Object> params = new HashMap<>();
    params.put("Key", "Value");
    PaymentConnectorResponseLink paymentConnectorResponseLink =
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", params);

    // Act and Assert
    assertNotEquals(
        paymentConnectorResponseLink,
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>()));
  }

  /**
   * Test {@link PaymentConnectorResponseLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentConnectorResponseLink#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentConnectorResponseLink.equals(Object)",
    "int PaymentConnectorResponseLink.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>()), null);
  }

  /**
   * Test {@link PaymentConnectorResponseLink#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentConnectorResponseLink#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PaymentConnectorResponseLink.equals(Object)",
    "int PaymentConnectorResponseLink.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new PaymentConnectorResponseLink("Rel", "Href", "Method", "Type", new HashMap<>()),
        "Different type to PaymentConnectorResponseLink");
  }
}
