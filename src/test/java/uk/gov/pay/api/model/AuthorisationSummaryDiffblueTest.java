package uk.gov.pay.api.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AuthorisationSummaryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return ThreeDSecure is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AuthorisationSummary#AuthorisationSummary()}
   *   <li>{@link AuthorisationSummary#getThreeDSecure()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthorisationSummary.<init>()",
    "void AuthorisationSummary.<init>(ThreeDSecure)",
    "ThreeDSecure AuthorisationSummary.getThreeDSecure()"
  })
  public void testGettersAndSetters_thenReturnThreeDSecureIsNull() {
    // Arrange, Act and Assert
    assertNull(new AuthorisationSummary().getThreeDSecure());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return ThreeDSecure Required.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AuthorisationSummary#AuthorisationSummary(ThreeDSecure)}
   *   <li>{@link AuthorisationSummary#getThreeDSecure()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthorisationSummary.<init>()",
    "void AuthorisationSummary.<init>(ThreeDSecure)",
    "ThreeDSecure AuthorisationSummary.getThreeDSecure()"
  })
  public void testGettersAndSetters_thenReturnThreeDSecureRequired() {
    // Arrange
    ThreeDSecure threeDSecure = new ThreeDSecure(true);

    // Act
    ThreeDSecure actualThreeDSecure = new AuthorisationSummary(threeDSecure).getThreeDSecure();

    // Assert
    assertTrue(actualThreeDSecure.isRequired());
    assertSame(threeDSecure, actualThreeDSecure);
  }
}
