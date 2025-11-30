package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExemptionOutcomeDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExemptionOutcome.<init>()",
    "void ExemptionOutcome.<init>(String)",
    "String ExemptionOutcome.getResult()"
  })
  public void testGettersAndSetters_thenReturnResultIsNull() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExemptionOutcome.<init>()",
    "void ExemptionOutcome.<init>(String)",
    "String ExemptionOutcome.getResult()"
  })
  public void testGettersAndSetters_whenResult_thenReturnResult() {
    // Arrange, Act and Assert
    assertEquals("Result", new ExemptionOutcome("Result").getResult());
  }
}
