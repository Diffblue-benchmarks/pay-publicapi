package uk.gov.pay.api.utils;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.ThreeDSecure;

class AuthorisationSummaryHelperDiffblueTest {
  /**
   * Test {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}.
   *
   * <p>Method under test: {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}
   */
  @Test
  @DisplayName("Test includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthorisationSummary AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)"
  })
  void testIncludeAuthorisationSummaryWhen3dsRequired() {
    // Arrange, Act and Assert
    assertNull(
        AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(
            new AuthorisationSummary(new ThreeDSecure(false))));
  }

  /**
   * Test {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}.
   *
   * <ul>
   *   <li>Then return ThreeDSecure Required.
   * </ul>
   *
   * <p>Method under test: {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}
   */
  @Test
  @DisplayName(
      "Test includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary); then return ThreeDSecure Required")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthorisationSummary AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)"
  })
  void testIncludeAuthorisationSummaryWhen3dsRequired_thenReturnThreeDSecureRequired() {
    // Arrange
    ThreeDSecure threeDSecure = new ThreeDSecure(true);

    // Act and Assert
    ThreeDSecure threeDSecure2 =
        AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(
                new AuthorisationSummary(threeDSecure))
            .getThreeDSecure();
    assertTrue(threeDSecure2.isRequired());
    assertSame(threeDSecure, threeDSecure2);
  }

  /**
   * Test {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}.
   *
   * <ul>
   *   <li>When {@link AuthorisationSummary#AuthorisationSummary()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}
   */
  @Test
  @DisplayName(
      "Test includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary); when AuthorisationSummary()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthorisationSummary AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)"
  })
  void testIncludeAuthorisationSummaryWhen3dsRequired_whenAuthorisationSummary() {
    // Arrange, Act and Assert
    assertNull(
        AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(
            new AuthorisationSummary()));
  }

  /**
   * Test {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}
   */
  @Test
  @DisplayName(
      "Test includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthorisationSummary AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)"
  })
  void testIncludeAuthorisationSummaryWhen3dsRequired_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(null));
  }
}
