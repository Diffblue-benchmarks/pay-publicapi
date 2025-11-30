package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PrefilledCardholderDetailsDiffblueTest {
  /**
   * Test {@link PrefilledCardholderDetails#getBillingAddress()}.
   *
   * <p>Method under test: {@link PrefilledCardholderDetails#getBillingAddress()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional PrefilledCardholderDetails.getBillingAddress()"})
  public void testGetBillingAddress() {
    // Arrange, Act and Assert
    assertFalse(new PrefilledCardholderDetails().getBillingAddress().isPresent());
  }

  /**
   * Test {@link PrefilledCardholderDetails#getCardholderName()}.
   *
   * <p>Method under test: {@link PrefilledCardholderDetails#getCardholderName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional PrefilledCardholderDetails.getCardholderName()"})
  public void testGetCardholderName() {
    // Arrange, Act and Assert
    assertFalse(new PrefilledCardholderDetails().getCardholderName().isPresent());
  }

  /**
   * Test {@link PrefilledCardholderDetails#setAddress(String, String, String, String, String)}.
   *
   * <p>Method under test: {@link PrefilledCardholderDetails#setAddress(String, String, String,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefilledCardholderDetails.setAddress(String, String, String, String, String)"
  })
  public void testSetAddress() {
    // Arrange
    PrefilledCardholderDetails prefilledCardholderDetails = new PrefilledCardholderDetails();

    // Act
    prefilledCardholderDetails.setAddress("42 Main St", "42 Main St", "OX1 1PT", "Oxford", "GB");

    // Assert
    Optional<Address> billingAddress = prefilledCardholderDetails.getBillingAddress();
    Address getResult = billingAddress.get();
    assertEquals("42 Main St", getResult.getLine1());
    assertEquals("42 Main St", getResult.getLine2());
    assertEquals("GB", getResult.getCountry());
    assertEquals("OX1 1PT", getResult.getPostcode());
    assertEquals("Oxford", getResult.getCity());
    assertFalse(prefilledCardholderDetails.getCardholderName().isPresent());
    assertTrue(billingAddress.isPresent());
  }

  /**
   * Test {@link PrefilledCardholderDetails#equals(Object)}, and {@link
   * PrefilledCardholderDetails#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PrefilledCardholderDetails#equals(Object)}
   *   <li>{@link PrefilledCardholderDetails#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PrefilledCardholderDetails.equals(Object)",
    "int PrefilledCardholderDetails.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    PrefilledCardholderDetails prefilledCardholderDetails = new PrefilledCardholderDetails();
    prefilledCardholderDetails.setAddress("42 Main St", "42 Main St", "OX1 1PT", "Oxford", "GB");
    prefilledCardholderDetails.setCardholderName("Cardholder Name");

    PrefilledCardholderDetails prefilledCardholderDetails2 = new PrefilledCardholderDetails();
    prefilledCardholderDetails2.setAddress("42 Main St", "42 Main St", "OX1 1PT", "Oxford", "GB");
    prefilledCardholderDetails2.setCardholderName("Cardholder Name");

    // Act and Assert
    assertEquals(prefilledCardholderDetails, prefilledCardholderDetails2);
    assertEquals(prefilledCardholderDetails.hashCode(), prefilledCardholderDetails2.hashCode());
  }

  /**
   * Test {@link PrefilledCardholderDetails#equals(Object)}, and {@link
   * PrefilledCardholderDetails#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PrefilledCardholderDetails#equals(Object)}
   *   <li>{@link PrefilledCardholderDetails#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PrefilledCardholderDetails.equals(Object)",
    "int PrefilledCardholderDetails.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    PrefilledCardholderDetails prefilledCardholderDetails = new PrefilledCardholderDetails();
    prefilledCardholderDetails.setAddress("42 Main St", "42 Main St", "OX1 1PT", "Oxford", "GB");
    prefilledCardholderDetails.setCardholderName("Cardholder Name");

    // Act and Assert
    assertEquals(prefilledCardholderDetails, prefilledCardholderDetails);
    int expectedHashCodeResult = prefilledCardholderDetails.hashCode();
    assertEquals(expectedHashCodeResult, prefilledCardholderDetails.hashCode());
  }

  /**
   * Test {@link PrefilledCardholderDetails#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PrefilledCardholderDetails#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PrefilledCardholderDetails.equals(Object)",
    "int PrefilledCardholderDetails.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    PrefilledCardholderDetails prefilledCardholderDetails = new PrefilledCardholderDetails();
    prefilledCardholderDetails.setAddress("42 Main St", "42 Main St", "OX1 1PT", "Oxford", "GB");
    prefilledCardholderDetails.setCardholderName("uk.gov.pay.api.model.PrefilledCardholderDetails");

    PrefilledCardholderDetails prefilledCardholderDetails2 = new PrefilledCardholderDetails();
    prefilledCardholderDetails2.setAddress("42 Main St", "42 Main St", "OX1 1PT", "Oxford", "GB");
    prefilledCardholderDetails2.setCardholderName("Cardholder Name");

    // Act and Assert
    assertNotEquals(prefilledCardholderDetails, prefilledCardholderDetails2);
  }

  /**
   * Test {@link PrefilledCardholderDetails#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PrefilledCardholderDetails#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PrefilledCardholderDetails.equals(Object)",
    "int PrefilledCardholderDetails.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    PrefilledCardholderDetails prefilledCardholderDetails = new PrefilledCardholderDetails();
    prefilledCardholderDetails.setAddress("17 High St", "42 Main St", "OX1 1PT", "Oxford", "GB");
    prefilledCardholderDetails.setCardholderName("Cardholder Name");

    PrefilledCardholderDetails prefilledCardholderDetails2 = new PrefilledCardholderDetails();
    prefilledCardholderDetails2.setAddress("42 Main St", "42 Main St", "OX1 1PT", "Oxford", "GB");
    prefilledCardholderDetails2.setCardholderName("Cardholder Name");

    // Act and Assert
    assertNotEquals(prefilledCardholderDetails, prefilledCardholderDetails2);
  }

  /**
   * Test {@link PrefilledCardholderDetails#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PrefilledCardholderDetails#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PrefilledCardholderDetails.equals(Object)",
    "int PrefilledCardholderDetails.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    PrefilledCardholderDetails prefilledCardholderDetails = new PrefilledCardholderDetails();
    prefilledCardholderDetails.setAddress("42 Main St", "42 Main St", "OX1 1PT", "Oxford", "GB");
    prefilledCardholderDetails.setCardholderName("Cardholder Name");

    // Act and Assert
    assertNotEquals(prefilledCardholderDetails, null);
  }

  /**
   * Test {@link PrefilledCardholderDetails#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PrefilledCardholderDetails#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PrefilledCardholderDetails.equals(Object)",
    "int PrefilledCardholderDetails.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    PrefilledCardholderDetails prefilledCardholderDetails = new PrefilledCardholderDetails();
    prefilledCardholderDetails.setAddress("42 Main St", "42 Main St", "OX1 1PT", "Oxford", "GB");
    prefilledCardholderDetails.setCardholderName("Cardholder Name");

    // Act and Assert
    assertNotEquals(prefilledCardholderDetails, "Different type to PrefilledCardholderDetails");
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link PrefilledCardholderDetails}
   *   <li>{@link PrefilledCardholderDetails#setCardholderName(String)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefilledCardholderDetails.<init>()",
    "void PrefilledCardholderDetails.setCardholderName(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    PrefilledCardholderDetails actualPrefilledCardholderDetails = new PrefilledCardholderDetails();
    actualPrefilledCardholderDetails.setCardholderName("Cardholder Name");

    // Assert
    Optional<String> cardholderName = actualPrefilledCardholderDetails.getCardholderName();
    assertEquals("Cardholder Name", cardholderName.get());
    assertFalse(actualPrefilledCardholderDetails.getBillingAddress().isPresent());
    assertTrue(cardholderName.isPresent());
  }
}
