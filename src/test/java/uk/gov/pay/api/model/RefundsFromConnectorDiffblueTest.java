package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.RefundsFromConnector.Embedded;

class RefundsFromConnectorDiffblueTest {
  /**
   * Test Embedded getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Embedded#Embedded(RefundsFromConnector)}
   *   <li>{@link Embedded#toString()}
   *   <li>{@link Embedded#getRefunds()}
   * </ul>
   */
  @Test
  @DisplayName("Test Embedded getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Embedded.<init>(RefundsFromConnector)",
    "java.util.List Embedded.getRefunds()",
    "String Embedded.toString()"
  })
  void testEmbeddedGettersAndSetters() {
    // Arrange and Act
    Embedded actualEmbedded = new RefundsFromConnector().new Embedded();
    String actualToStringResult = actualEmbedded.toString();

    // Assert
    assertEquals("Embedded{refunds=null}", actualToStringResult);
    assertNull(actualEmbedded.getRefunds());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link RefundsFromConnector}
   *   <li>{@link RefundsFromConnector#toString()}
   *   <li>{@link RefundsFromConnector#getEmbedded()}
   *   <li>{@link RefundsFromConnector#getPaymentId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundsFromConnector.<init>()",
    "Embedded RefundsFromConnector.getEmbedded()",
    "String RefundsFromConnector.getPaymentId()",
    "String RefundsFromConnector.toString()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    RefundsFromConnector actualRefundsFromConnector = new RefundsFromConnector();
    String actualToStringResult = actualRefundsFromConnector.toString();
    Embedded actualEmbedded = actualRefundsFromConnector.getEmbedded();

    // Assert
    assertEquals("RefundsFromConnector{paymentId='null', null}", actualToStringResult);
    assertNull(actualRefundsFromConnector.getPaymentId());
    assertNull(actualEmbedded);
  }
}
