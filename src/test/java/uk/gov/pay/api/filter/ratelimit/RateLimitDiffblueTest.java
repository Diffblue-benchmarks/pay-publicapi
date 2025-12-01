package uk.gov.pay.api.filter.ratelimit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RateLimitDiffblueTest {
  /**
   * Test {@link RateLimit#RateLimit(int, int)}.
   *
   * <p>Method under test: {@link RateLimit#RateLimit(int, int)}
   */
  @Test
  @DisplayName("Test new RateLimit(int, int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimit.<init>(int, int)"})
  void testNewRateLimit() {
    // Arrange and Act
    RateLimit actualRateLimit = new RateLimit(1, 1);

    // Assert
    assertEquals(0, actualRateLimit.getRequestCount());
    assertEquals(1, actualRateLimit.getNoOfReq());
  }

  /**
   * Test {@link RateLimit#updateAllowance()}.
   *
   * <p>Method under test: {@link RateLimit#updateAllowance()}
   */
  @Test
  @DisplayName("Test updateAllowance()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimit.updateAllowance()"})
  void testUpdateAllowance() throws RateLimitException {
    // Arrange
    RateLimit rateLimit = new RateLimit(1, 1);

    // Act
    rateLimit.updateAllowance();

    // Assert
    assertEquals(1, rateLimit.getRequestCount());
  }

  /**
   * Test {@link RateLimit#updateAllowance()}.
   *
   * <p>Method under test: {@link RateLimit#updateAllowance()}
   */
  @Test
  @DisplayName("Test updateAllowance()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimit.updateAllowance()"})
  void testUpdateAllowance2() throws RateLimitException {
    // Arrange
    RateLimit rateLimit = new RateLimit(1, -1);

    // Act
    rateLimit.updateAllowance();

    // Assert
    assertEquals(1, rateLimit.getRequestCount());
  }

  /**
   * Test {@link RateLimit#updateAllowance()}.
   *
   * <ul>
   *   <li>Then throw {@link RateLimitException}.
   * </ul>
   *
   * <p>Method under test: {@link RateLimit#updateAllowance()}
   */
  @Test
  @DisplayName("Test updateAllowance(); then throw RateLimitException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimit.updateAllowance()"})
  void testUpdateAllowance_thenThrowRateLimitException() throws RateLimitException {
    // Arrange, Act and Assert
    assertThrows(RateLimitException.class, () -> new RateLimit(-1, 1).updateAllowance());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RateLimit#getNoOfReq()}
   *   <li>{@link RateLimit#getRequestCount()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int RateLimit.getNoOfReq()", "int RateLimit.getRequestCount()"})
  void testGettersAndSetters() {
    // Arrange
    RateLimit rateLimit = new RateLimit(1, 1);

    // Act
    int actualNoOfReq = rateLimit.getNoOfReq();

    // Assert
    assertEquals(0, rateLimit.getRequestCount());
    assertEquals(1, actualNoOfReq);
  }
}
