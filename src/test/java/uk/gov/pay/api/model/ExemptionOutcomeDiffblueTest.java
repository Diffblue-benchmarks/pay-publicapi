package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ExemptionOutcomeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Result is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ExemptionOutcome#ExemptionOutcome()}
   *   <li>{@link ExemptionOutcome#getResult()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return Result is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExemptionOutcome.<init>()",
    "void ExemptionOutcome.<init>(String)",
    "String ExemptionOutcome.getResult()"
  })
  void testGettersAndSetters_thenReturnResultIsNull() {
    // Arrange, Act and Assert
    assertNull(new ExemptionOutcome().getResult());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Result}.
   *   <li>Then return {@code Result}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ExemptionOutcome#ExemptionOutcome(String)}
   *   <li>{@link ExemptionOutcome#getResult()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when 'Result'; then return 'Result'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExemptionOutcome.<init>()",
    "void ExemptionOutcome.<init>(String)",
    "String ExemptionOutcome.getResult()"
  })
  void testGettersAndSetters_whenResult_thenReturnResult() {
    // Arrange, Act and Assert
    assertEquals("Result", new ExemptionOutcome("Result").getResult());
  }
}
