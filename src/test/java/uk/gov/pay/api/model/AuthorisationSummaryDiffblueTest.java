package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AuthorisationSummaryDiffblueTest {
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
  @DisplayName("Test getters and setters; then return ThreeDSecure is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthorisationSummary.<init>()",
    "void AuthorisationSummary.<init>(ThreeDSecure)",
    "ThreeDSecure AuthorisationSummary.getThreeDSecure()"
  })
  void testGettersAndSetters_thenReturnThreeDSecureIsNull() {
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
  @DisplayName("Test getters and setters; then return ThreeDSecure Required")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthorisationSummary.<init>()",
    "void AuthorisationSummary.<init>(ThreeDSecure)",
    "ThreeDSecure AuthorisationSummary.getThreeDSecure()"
  })
  void testGettersAndSetters_thenReturnThreeDSecureRequired() {
    // Arrange
    ThreeDSecure threeDSecure = new ThreeDSecure(true);

    // Act
    ThreeDSecure actualThreeDSecure = new AuthorisationSummary(threeDSecure).getThreeDSecure();

    // Assert
    assertTrue(actualThreeDSecure.isRequired());
    assertSame(threeDSecure, actualThreeDSecure);
  }
}
