package uk.gov.pay.api.model.telephone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PaymentOutcomeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Status is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentOutcome#PaymentOutcome()}
   *   <li>{@link PaymentOutcome#getStatus()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentOutcome.<init>()",
    "void PaymentOutcome.<init>(String)",
    "void PaymentOutcome.<init>(String, String, Supplemental)",
    "String PaymentOutcome.getStatus()"
  })
  public void testGettersAndSetters_thenReturnStatusIsNull() {
    // Arrange, Act and Assert
    assertNull(new PaymentOutcome().getStatus());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Code}.
   *   <li>Then return {@code Status}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentOutcome#PaymentOutcome(String, String, Supplemental)}
   *   <li>{@link PaymentOutcome#getStatus()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentOutcome.<init>()",
    "void PaymentOutcome.<init>(String)",
    "void PaymentOutcome.<init>(String, String, Supplemental)",
    "String PaymentOutcome.getStatus()"
  })
  public void testGettersAndSetters_whenCode_thenReturnStatus() {
    // Arrange and Act
    PaymentOutcome actualPaymentOutcome =
        new PaymentOutcome(
            "Status", "Code", new Supplemental("An error occurred", "An error occurred"));

    // Assert
    assertEquals("Status", actualPaymentOutcome.getStatus());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Status}.
   *   <li>Then return {@code Status}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentOutcome#PaymentOutcome(String)}
   *   <li>{@link PaymentOutcome#getStatus()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentOutcome.<init>()",
    "void PaymentOutcome.<init>(String)",
    "void PaymentOutcome.<init>(String, String, Supplemental)",
    "String PaymentOutcome.getStatus()"
  })
  public void testGettersAndSetters_whenStatus_thenReturnStatus() {
    // Arrange, Act and Assert
    assertEquals("Status", new PaymentOutcome("Status").getStatus());
  }

  /**
   * Test {@link PaymentOutcome#getCode()}.
   *
   * <p>Method under test: {@link PaymentOutcome#getCode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Optional PaymentOutcome.getCode()"})
  public void testGetCode() {
    // Arrange, Act and Assert
    assertFalse(new PaymentOutcome("Status").getCode().isPresent());
  }

  /**
   * Test {@link PaymentOutcome#getSupplemental()}.
   *
   * <p>Method under test: {@link PaymentOutcome#getSupplemental()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Optional PaymentOutcome.getSupplemental()"})
  public void testGetSupplemental() {
    // Arrange, Act and Assert
    assertFalse(new PaymentOutcome("Status").getSupplemental().isPresent());
  }
}
