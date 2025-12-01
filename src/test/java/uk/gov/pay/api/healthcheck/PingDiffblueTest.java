package uk.gov.pay.api.healthcheck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PingDiffblueTest {
  /**
   * Test {@link Ping#check()}.
   *
   * <p>Method under test: {@link Ping#check()}
   */
  @Test
  @DisplayName("Test check()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"HealthCheck.Result Ping.check()"})
  void testCheck() {
    // Arrange and Act
    Result actualCheckResult = new Ping().check();

    // Assert
    assertNull(actualCheckResult.getMessage());
    assertNull(actualCheckResult.getError());
    assertNull(actualCheckResult.getDetails());
    assertEquals(0L, actualCheckResult.getDuration());
    assertTrue(actualCheckResult.isHealthy());
  }
}
