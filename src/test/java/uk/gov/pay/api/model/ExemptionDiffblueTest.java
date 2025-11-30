package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExemptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Type is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Exemption#Exemption()}
   *   <li>{@link Exemption#getOutcome()}
   *   <li>{@link Exemption#getRequested()}
   *   <li>{@link Exemption#getType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Exemption.<init>()",
    "void Exemption.<init>(Boolean, String, ExemptionOutcome)",
    "ExemptionOutcome Exemption.getOutcome()",
    "boolean Exemption.getRequested()",
    "String Exemption.getType()"
  })
  public void testGettersAndSetters_thenReturnTypeIsNull() {
    // Arrange and Act
    Exemption actualExemption = new Exemption();
    ExemptionOutcome actualOutcome = actualExemption.getOutcome();
    boolean actualRequested = actualExemption.getRequested();

    // Assert
    assertNull(actualExemption.getType());
    assertNull(actualOutcome);
    assertFalse(actualRequested);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return Outcome Result is {@code Result}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Exemption#Exemption(Boolean, String, ExemptionOutcome)}
   *   <li>{@link Exemption#getOutcome()}
   *   <li>{@link Exemption#getRequested()}
   *   <li>{@link Exemption#getType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Exemption.<init>()",
    "void Exemption.<init>(Boolean, String, ExemptionOutcome)",
    "ExemptionOutcome Exemption.getOutcome()",
    "boolean Exemption.getRequested()",
    "String Exemption.getType()"
  })
  public void testGettersAndSetters_whenTrue_thenReturnOutcomeResultIsResult() {
    // Arrange
    ExemptionOutcome outcome = new ExemptionOutcome("Result");

    // Act
    Exemption actualExemption = new Exemption(true, "Type", outcome);
    ExemptionOutcome actualOutcome = actualExemption.getOutcome();
    boolean actualRequested = actualExemption.getRequested();

    // Assert
    assertEquals("Result", actualOutcome.getResult());
    assertEquals("Type", actualExemption.getType());
    assertTrue(actualRequested);
    assertSame(outcome, actualOutcome);
  }
}
