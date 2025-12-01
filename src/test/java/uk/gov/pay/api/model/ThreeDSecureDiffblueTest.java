package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ThreeDSecureDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return not Required.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ThreeDSecure#ThreeDSecure()}
   *   <li>{@link ThreeDSecure#isRequired()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return not Required")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ThreeDSecure.<init>()",
    "void ThreeDSecure.<init>(boolean)",
    "boolean ThreeDSecure.isRequired()"
  })
  void testGettersAndSetters_thenReturnNotRequired() {
    // Arrange, Act and Assert
    assertFalse(new ThreeDSecure().isRequired());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return Required.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ThreeDSecure#ThreeDSecure(boolean)}
   *   <li>{@link ThreeDSecure#isRequired()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when 'true'; then return Required")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ThreeDSecure.<init>()",
    "void ThreeDSecure.<init>(boolean)",
    "boolean ThreeDSecure.isRequired()"
  })
  void testGettersAndSetters_whenTrue_thenReturnRequired() {
    // Arrange, Act and Assert
    assertTrue(new ThreeDSecure(true).isRequired());
  }
}
