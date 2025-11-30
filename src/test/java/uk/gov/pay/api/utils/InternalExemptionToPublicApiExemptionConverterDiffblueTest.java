package uk.gov.pay.api.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.ExemptionOutcome;

public class InternalExemptionToPublicApiExemptionConverterDiffblueTest {
  /**
   * Test {@link InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}.
   *
   * <p>Method under test: {@link
   * InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  public void testConvertExemption() {
    // Arrange
    Exemption maybeExemption = new Exemption(true, "Type", new ExemptionOutcome("Result"));

    // Act and Assert
    assertNull(InternalExemptionToPublicApiExemptionConverter.convertExemption(maybeExemption));
  }

  /**
   * Test {@link InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}.
   *
   * <p>Method under test: {@link
   * InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  public void testConvertExemption2() {
    // Arrange
    Exemption maybeExemption = new Exemption(true, "corporate", null);

    // Act and Assert
    assertNull(InternalExemptionToPublicApiExemptionConverter.convertExemption(maybeExemption));
  }

  /**
   * Test {@link InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}.
   *
   * <ul>
   *   <li>Then return Outcome Result is {@code Result}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  public void testConvertExemption_thenReturnOutcomeResultIsResult() {
    // Arrange
    ExemptionOutcome outcome = new ExemptionOutcome("Result");
    Exemption maybeExemption = new Exemption(true, "corporate", outcome);

    // Act
    Exemption actualConvertExemptionResult =
        InternalExemptionToPublicApiExemptionConverter.convertExemption(maybeExemption);

    // Assert
    ExemptionOutcome outcome2 = actualConvertExemptionResult.getOutcome();
    assertEquals("Result", outcome2.getResult());
    assertEquals("corporate", actualConvertExemptionResult.getType());
    assertTrue(actualConvertExemptionResult.getRequested());
    assertSame(outcome, outcome2);
  }

  /**
   * Test {@link InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}.
   *
   * <ul>
   *   <li>When {@link Exemption#Exemption()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  public void testConvertExemption_whenExemption_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(InternalExemptionToPublicApiExemptionConverter.convertExemption(new Exemption()));
  }

  /**
   * Test {@link InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  public void testConvertExemption_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(InternalExemptionToPublicApiExemptionConverter.convertExemption(null));
  }
}
