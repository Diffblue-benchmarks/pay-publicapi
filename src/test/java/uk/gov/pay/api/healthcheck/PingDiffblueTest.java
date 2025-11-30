package uk.gov.pay.api.healthcheck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PingDiffblueTest {
  /**
   * Test {@link Ping#check()}.
   *
   * <p>Method under test: {@link Ping#check()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"HealthCheck.Result Ping.check()"})
  public void testCheck() {
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
