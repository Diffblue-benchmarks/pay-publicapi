package uk.gov.pay.api.app.config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import java.net.URI;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PublicApiConfigDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PublicApiConfig#getAllowHttpForReturnUrl()}
   *   <li>{@link PublicApiConfig#getApiKeyHmacSecret()}
   *   <li>{@link PublicApiConfig#getAuthenticationCachePolicy()}
   *   <li>{@link PublicApiConfig#getBaseUrl()}
   *   <li>{@link PublicApiConfig#getConnectorUrl()}
   *   <li>{@link PublicApiConfig#getLedgerUrl()}
   *   <li>{@link PublicApiConfig#getPublicAuthUrl()}
   *   <li>{@link PublicApiConfig#getRateLimiterConfig()}
   *   <li>{@link PublicApiConfig#getRedisConfiguration()}
   *   <li>{@link PublicApiConfig#getRestClientConfig()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Boolean PublicApiConfig.getAllowHttpForReturnUrl()",
    "String PublicApiConfig.getApiKeyHmacSecret()",
    "CaffeineSpec PublicApiConfig.getAuthenticationCachePolicy()",
    "String PublicApiConfig.getBaseUrl()",
    "String PublicApiConfig.getConnectorUrl()",
    "String PublicApiConfig.getLedgerUrl()",
    "String PublicApiConfig.getPublicAuthUrl()",
    "RateLimiterConfig PublicApiConfig.getRateLimiterConfig()",
    "RedisConfiguration PublicApiConfig.getRedisConfiguration()",
    "uk.gov.pay.api.app.config.RestClientConfig PublicApiConfig.getRestClientConfig()"
  })
  public void testGettersAndSetters() {
    // Arrange
    PublicApiConfig publicApiConfig = new PublicApiConfig();

    // Act
    Boolean actualAllowHttpForReturnUrl = publicApiConfig.getAllowHttpForReturnUrl();
    String actualApiKeyHmacSecret = publicApiConfig.getApiKeyHmacSecret();
    CaffeineSpec actualAuthenticationCachePolicy = publicApiConfig.getAuthenticationCachePolicy();
    String actualBaseUrl = publicApiConfig.getBaseUrl();
    String actualConnectorUrl = publicApiConfig.getConnectorUrl();
    String actualLedgerUrl = publicApiConfig.getLedgerUrl();
    String actualPublicAuthUrl = publicApiConfig.getPublicAuthUrl();
    RateLimiterConfig actualRateLimiterConfig = publicApiConfig.getRateLimiterConfig();
    RedisConfiguration actualRedisConfiguration = publicApiConfig.getRedisConfiguration();

    // Assert
    assertNull(actualAuthenticationCachePolicy);
    assertNull(actualAllowHttpForReturnUrl);
    assertNull(actualApiKeyHmacSecret);
    assertNull(actualBaseUrl);
    assertNull(actualConnectorUrl);
    assertNull(actualLedgerUrl);
    assertNull(actualPublicAuthUrl);
    assertNull(actualRateLimiterConfig);
    assertNull(actualRedisConfiguration);
    assertNull(publicApiConfig.getRestClientConfig());
  }

  /**
   * Test {@link PublicApiConfig#getEcsContainerMetadataUriV4()}.
   *
   * <p>Method under test: {@link PublicApiConfig#getEcsContainerMetadataUriV4()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional PublicApiConfig.getEcsContainerMetadataUriV4()"})
  public void testGetEcsContainerMetadataUriV4() {
    // Arrange, Act and Assert
    assertFalse(new PublicApiConfig().getEcsContainerMetadataUriV4().isPresent());
  }

  /**
   * Test new {@link PublicApiConfig} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link PublicApiConfig}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PublicApiConfig.<init>()"})
  public void testNewPublicApiConfig() {
    // Arrange and Act
    PublicApiConfig actualPublicApiConfig = new PublicApiConfig();

    // Assert
    assertTrue(actualPublicApiConfig.getServerFactory() instanceof DefaultServerFactory);
    assertTrue(actualPublicApiConfig.getLoggingFactory() instanceof DefaultLoggingFactory);
    assertNull(actualPublicApiConfig.getAuthenticationCachePolicy());
    assertNull(actualPublicApiConfig.getAllowHttpForReturnUrl());
    assertNull(actualPublicApiConfig.getApiKeyHmacSecret());
    assertNull(actualPublicApiConfig.getBaseUrl());
    assertNull(actualPublicApiConfig.getConnectorUrl());
    assertNull(actualPublicApiConfig.getLedgerUrl());
    assertNull(actualPublicApiConfig.getPublicAuthUrl());
    assertNull(actualPublicApiConfig.getRateLimiterConfig());
    assertNull(actualPublicApiConfig.getRedisConfiguration());
    assertNull(actualPublicApiConfig.getRestClientConfig());
    Optional<URI> ecsContainerMetadataUriV4 = actualPublicApiConfig.getEcsContainerMetadataUriV4();
    assertFalse(ecsContainerMetadataUriV4.isPresent());
    assertSame(ecsContainerMetadataUriV4, actualPublicApiConfig.getHealthFactory());
  }
}
