package uk.gov.pay.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.ThreeDSecure;

public class AuthorisationSummaryHelperDiffblueTest {
  /**
   * Test {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}.
   *
   * <p>Method under test: {@link
   * AuthorisationSummaryHelper#includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthorisationSummary AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)"
  })
  public void testIncludeAuthorisationSummaryWhen3dsRequired() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthorisationSummary AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)"
  })
  public void testIncludeAuthorisationSummaryWhen3dsRequired_thenReturnThreeDSecureRequired() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthorisationSummary AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)"
  })
  public void testIncludeAuthorisationSummaryWhen3dsRequired_whenAuthorisationSummary() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthorisationSummary AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(AuthorisationSummary)"
  })
  public void testIncludeAuthorisationSummaryWhen3dsRequired_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AuthorisationSummaryHelper.includeAuthorisationSummaryWhen3dsRequired(null));
  }
}
