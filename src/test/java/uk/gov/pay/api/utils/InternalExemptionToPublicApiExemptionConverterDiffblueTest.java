package uk.gov.pay.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.ExemptionOutcome;

class InternalExemptionToPublicApiExemptionConverterDiffblueTest {
  /**
   * Test {@link InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}.
   *
   * <p>Method under test: {@link
   * InternalExemptionToPublicApiExemptionConverter#convertExemption(Exemption)}
   */
  @Test
  @DisplayName("Test convertExemption(Exemption)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  void testConvertExemption() {
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
  @DisplayName("Test convertExemption(Exemption)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  void testConvertExemption2() {
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
  @DisplayName("Test convertExemption(Exemption); then return Outcome Result is 'Result'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  void testConvertExemption_thenReturnOutcomeResultIsResult() {
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
  @DisplayName("Test convertExemption(Exemption); when Exemption(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  void testConvertExemption_whenExemption_thenReturnNull() {
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
  @DisplayName("Test convertExemption(Exemption); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Exemption InternalExemptionToPublicApiExemptionConverter.convertExemption(Exemption)"
  })
  void testConvertExemption_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(InternalExemptionToPublicApiExemptionConverter.convertExemption(null));
  }
}
