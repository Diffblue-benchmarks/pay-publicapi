package uk.gov.pay.api.app.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RateLimiterConfigDiffblueTest {
  /**
   * Test {@link RateLimiterConfig#getElevatedAccounts()}.
   *
   * <p>Method under test: {@link RateLimiterConfig#getElevatedAccounts()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List RateLimiterConfig.getElevatedAccounts()"})
  public void testGetElevatedAccounts() {
    // Arrange, Act and Assert
    assertTrue(new RateLimiterConfig().getElevatedAccounts().isEmpty());
  }

  /**
   * Test {@link RateLimiterConfig#getLowTrafficAccounts()}.
   *
   * <p>Method under test: {@link RateLimiterConfig#getLowTrafficAccounts()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List RateLimiterConfig.getLowTrafficAccounts()"})
  public void testGetLowTrafficAccounts() {
    // Arrange, Act and Assert
    assertTrue(new RateLimiterConfig().getLowTrafficAccounts().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RateLimiterConfig#getIntervalInMillisForLowTrafficAccounts()}
   *   <li>{@link RateLimiterConfig#getNoOfPostReqForElevatedAccounts()}
   *   <li>{@link RateLimiterConfig#getNoOfPostReqForLowTrafficAccounts()}
   *   <li>{@link RateLimiterConfig#getNoOfReq()}
   *   <li>{@link RateLimiterConfig#getNoOfReqForElevatedAccounts()}
   *   <li>{@link RateLimiterConfig#getNoOfReqForLowTrafficAccounts()}
   *   <li>{@link RateLimiterConfig#getNoOfReqForPost()}
   *   <li>{@link RateLimiterConfig#getNoOfReqForPostPerNode()}
   *   <li>{@link RateLimiterConfig#getNoOfReqPerNode()}
   *   <li>{@link RateLimiterConfig#getPerMillis()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int RateLimiterConfig.getIntervalInMillisForLowTrafficAccounts()",
    "int RateLimiterConfig.getNoOfPostReqForElevatedAccounts()",
    "int RateLimiterConfig.getNoOfPostReqForLowTrafficAccounts()",
    "int RateLimiterConfig.getNoOfReq()",
    "int RateLimiterConfig.getNoOfReqForElevatedAccounts()",
    "int RateLimiterConfig.getNoOfReqForLowTrafficAccounts()",
    "int RateLimiterConfig.getNoOfReqForPost()",
    "int RateLimiterConfig.getNoOfReqForPostPerNode()",
    "int RateLimiterConfig.getNoOfReqPerNode()",
    "int RateLimiterConfig.getPerMillis()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RateLimiterConfig rateLimiterConfig = new RateLimiterConfig();

    // Act
    int actualIntervalInMillisForLowTrafficAccounts =
        rateLimiterConfig.getIntervalInMillisForLowTrafficAccounts();
    int actualNoOfPostReqForElevatedAccounts =
        rateLimiterConfig.getNoOfPostReqForElevatedAccounts();
    int actualNoOfPostReqForLowTrafficAccounts =
        rateLimiterConfig.getNoOfPostReqForLowTrafficAccounts();
    int actualNoOfReq = rateLimiterConfig.getNoOfReq();
    int actualNoOfReqForElevatedAccounts = rateLimiterConfig.getNoOfReqForElevatedAccounts();
    int actualNoOfReqForLowTrafficAccounts = rateLimiterConfig.getNoOfReqForLowTrafficAccounts();
    int actualNoOfReqForPost = rateLimiterConfig.getNoOfReqForPost();
    int actualNoOfReqForPostPerNode = rateLimiterConfig.getNoOfReqForPostPerNode();
    int actualNoOfReqPerNode = rateLimiterConfig.getNoOfReqPerNode();

    // Assert
    assertEquals(0, actualIntervalInMillisForLowTrafficAccounts);
    assertEquals(0, actualNoOfPostReqForElevatedAccounts);
    assertEquals(0, actualNoOfPostReqForLowTrafficAccounts);
    assertEquals(0, actualNoOfReq);
    assertEquals(0, actualNoOfReqForElevatedAccounts);
    assertEquals(0, actualNoOfReqForLowTrafficAccounts);
    assertEquals(0, actualNoOfReqForPost);
    assertEquals(0, actualNoOfReqForPostPerNode);
    assertEquals(0, actualNoOfReqPerNode);
    assertEquals(0, rateLimiterConfig.getPerMillis());
  }

  /**
   * Test new {@link RateLimiterConfig} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link RateLimiterConfig}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RateLimiterConfig.<init>()"})
  public void testNewRateLimiterConfig() {
    // Arrange and Act
    RateLimiterConfig actualRateLimiterConfig = new RateLimiterConfig();

    // Assert
    assertTrue(actualRateLimiterConfig.getServerFactory() instanceof DefaultServerFactory);
    assertTrue(actualRateLimiterConfig.getLoggingFactory() instanceof DefaultLoggingFactory);
    assertEquals(0, actualRateLimiterConfig.getIntervalInMillisForLowTrafficAccounts());
    assertEquals(0, actualRateLimiterConfig.getNoOfPostReqForElevatedAccounts());
    assertEquals(0, actualRateLimiterConfig.getNoOfPostReqForLowTrafficAccounts());
    assertEquals(0, actualRateLimiterConfig.getNoOfReq());
    assertEquals(0, actualRateLimiterConfig.getNoOfReqForElevatedAccounts());
    assertEquals(0, actualRateLimiterConfig.getNoOfReqForLowTrafficAccounts());
    assertEquals(0, actualRateLimiterConfig.getNoOfReqForPost());
    assertEquals(0, actualRateLimiterConfig.getNoOfReqForPostPerNode());
    assertEquals(0, actualRateLimiterConfig.getNoOfReqPerNode());
    assertEquals(0, actualRateLimiterConfig.getPerMillis());
    assertFalse(actualRateLimiterConfig.getHealthFactory().isPresent());
    List<String> elevatedAccounts = actualRateLimiterConfig.getElevatedAccounts();
    assertTrue(elevatedAccounts.isEmpty());
    assertSame(elevatedAccounts, actualRateLimiterConfig.getLowTrafficAccounts());
  }
}
