package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AddressDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Address#Address(String, String, String, String, String)}
   *   <li>{@link Address#getCity()}
   *   <li>{@link Address#getCountry()}
   *   <li>{@link Address#getLine1()}
   *   <li>{@link Address#getLine2()}
   *   <li>{@link Address#getPostcode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Address.<init>(String, String, String, String, String)",
    "String Address.getCity()",
    "String Address.getCountry()",
    "String Address.getLine1()",
    "String Address.getLine2()",
    "String Address.getPostcode()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    Address actualAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    String actualCity = actualAddress.getCity();
    String actualCountry = actualAddress.getCountry();
    String actualLine1 = actualAddress.getLine1();
    String actualLine2 = actualAddress.getLine2();

    // Assert
    assertEquals("GB", actualCountry);
    assertEquals("Line1", actualLine1);
    assertEquals("Line2", actualLine2);
    assertEquals("OX1 1PT", actualAddress.getPostcode());
    assertEquals("Oxford", actualCity);
  }

  /**
   * Test {@link Address#equals(Object)}, and {@link Address#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Address#equals(Object)}
   *   <li>{@link Address#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Address.equals(Object)", "int Address.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Address address = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    Address address2 = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");

    // Act and Assert
    assertEquals(address, address2);
    assertEquals(address.hashCode(), address2.hashCode());
  }

  /**
   * Test {@link Address#equals(Object)}, and {@link Address#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Address#equals(Object)}
   *   <li>{@link Address#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Address.equals(Object)", "int Address.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Address address = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");

    // Act and Assert
    assertEquals(address, address);
    int expectedHashCodeResult = address.hashCode();
    assertEquals(expectedHashCodeResult, address.hashCode());
  }

  /**
   * Test {@link Address#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Address#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Address.equals(Object)", "int Address.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Address address = new Address(null, "Line2", "OX1 1PT", "Oxford", "GB");

    // Act and Assert
    assertNotEquals(address, new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB"));
  }

  /**
   * Test {@link Address#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Address#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Address.equals(Object)", "int Address.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Address address = new Address("Line1", null, "OX1 1PT", "Oxford", "GB");

    // Act and Assert
    assertNotEquals(address, new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB"));
  }

  /**
   * Test {@link Address#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Address#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Address.equals(Object)", "int Address.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Address address = new Address("Line1", "Line2", "Postcode", "Oxford", "GB");

    // Act and Assert
    assertNotEquals(address, new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB"));
  }

  /**
   * Test {@link Address#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Address#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Address.equals(Object)", "int Address.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Address address = new Address("Line1", "Line2", "OX1 1PT", "London", "GB");

    // Act and Assert
    assertNotEquals(address, new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB"));
  }

  /**
   * Test {@link Address#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Address#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Address.equals(Object)", "int Address.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Address address = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GBR");

    // Act and Assert
    assertNotEquals(address, new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB"));
  }

  /**
   * Test {@link Address#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Address#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Address.equals(Object)", "int Address.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB"), null);
  }

  /**
   * Test {@link Address#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Address#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Address.equals(Object)", "int Address.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB"), "Different type to Address");
  }
}
