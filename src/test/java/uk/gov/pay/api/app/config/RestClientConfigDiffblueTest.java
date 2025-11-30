package uk.gov.pay.api.app.config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RestClientConfigDiffblueTest {
  /**
   * Test {@link RestClientConfig#RestClientConfig()}.
   *
   * <p>Method under test: {@link RestClientConfig#RestClientConfig()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RestClientConfig.<init>()"})
  public void testNewRestClientConfig() {
    // Arrange and Act
    RestClientConfig actualRestClientConfig = new RestClientConfig();

    // Assert
    assertTrue(actualRestClientConfig.getServerFactory() instanceof DefaultServerFactory);
    assertTrue(actualRestClientConfig.getLoggingFactory() instanceof DefaultLoggingFactory);
    assertFalse(actualRestClientConfig.getHealthFactory().isPresent());
  }

  /**
   * Test {@link RestClientConfig#RestClientConfig(boolean)}.
   *
   * <p>Method under test: {@link RestClientConfig#RestClientConfig(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RestClientConfig.<init>(boolean)"})
  public void testNewRestClientConfig2() {
    // Arrange and Act
    RestClientConfig actualRestClientConfig = new RestClientConfig(true);

    // Assert
    assertTrue(actualRestClientConfig.getServerFactory() instanceof DefaultServerFactory);
    assertTrue(actualRestClientConfig.getLoggingFactory() instanceof DefaultLoggingFactory);
    assertFalse(actualRestClientConfig.getHealthFactory().isPresent());
  }

  /**
   * Test {@link RestClientConfig#isDisabledSecureConnection()}.
   *
   * <ul>
   *   <li>Given {@link RestClientConfig#RestClientConfig()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RestClientConfig#isDisabledSecureConnection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Boolean RestClientConfig.isDisabledSecureConnection()"})
  public void testIsDisabledSecureConnection_givenRestClientConfig_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new RestClientConfig().isDisabledSecureConnection());
  }

  /**
   * Test {@link RestClientConfig#isDisabledSecureConnection()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RestClientConfig#isDisabledSecureConnection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Boolean RestClientConfig.isDisabledSecureConnection()"})
  public void testIsDisabledSecureConnection_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new RestClientConfig(true).isDisabledSecureConnection());
  }
}
