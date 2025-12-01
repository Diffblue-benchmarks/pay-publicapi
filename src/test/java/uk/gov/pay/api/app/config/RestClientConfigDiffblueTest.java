package uk.gov.pay.api.app.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RestClientConfigDiffblueTest {
  /**
   * Test {@link RestClientConfig#RestClientConfig()}.
   *
   * <p>Method under test: {@link RestClientConfig#RestClientConfig()}
   */
  @Test
  @DisplayName("Test new RestClientConfig()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RestClientConfig.<init>()"})
  void testNewRestClientConfig() {
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
  @DisplayName("Test new RestClientConfig(boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RestClientConfig.<init>(boolean)"})
  void testNewRestClientConfig2() {
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
  @DisplayName("Test isDisabledSecureConnection(); given RestClientConfig(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Boolean RestClientConfig.isDisabledSecureConnection()"})
  void testIsDisabledSecureConnection_givenRestClientConfig_thenReturnFalse() {
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
  @DisplayName("Test isDisabledSecureConnection(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Boolean RestClientConfig.isDisabledSecureConnection()"})
  void testIsDisabledSecureConnection_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new RestClientConfig(true).isDisabledSecureConnection());
  }
}
