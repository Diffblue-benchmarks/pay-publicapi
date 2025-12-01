package uk.gov.pay.api.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.client.Client;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.app.config.RestClientConfig;

class RestClientFactoryDiffblueTest {
  /**
   * Test {@link RestClientFactory#buildClient(RestClientConfig)}.
   *
   * <ul>
   *   <li>Then return SslContext Protocol is {@code TLS}.
   * </ul>
   *
   * <p>Method under test: {@link RestClientFactory#buildClient(RestClientConfig)}
   */
  @Test
  @DisplayName("Test buildClient(RestClientConfig); then return SslContext Protocol is 'TLS'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Client RestClientFactory.buildClient(RestClientConfig)"})
  void testBuildClient_thenReturnSslContextProtocolIsTls() {
    // Arrange and Act
    Client actualBuildClientResult = RestClientFactory.buildClient(new RestClientConfig(true));

    // Assert
    assertTrue(actualBuildClientResult instanceof JerseyClient);
    SSLContext sslContext = actualBuildClientResult.getSslContext();
    assertEquals("TLS", sslContext.getProtocol());
    SSLParameters defaultSSLParameters = sslContext.getDefaultSSLParameters();
    assertEquals(2, defaultSSLParameters.getProtocols().length);
    assertEquals(37, defaultSSLParameters.getCipherSuites().length);
    assertTrue(((JerseyClient) actualBuildClientResult).isDefaultSslContext());
    assertSame(
        actualBuildClientResult,
        ((ClientConfig) actualBuildClientResult.getConfiguration()).getClient());
  }

  /**
   * Test {@link RestClientFactory#buildClient(RestClientConfig)}.
   *
   * <ul>
   *   <li>When {@link RestClientConfig#RestClientConfig()}.
   *   <li>Then return SslContext Protocol is {@code TLSv1.2}.
   * </ul>
   *
   * <p>Method under test: {@link RestClientFactory#buildClient(RestClientConfig)}
   */
  @Test
  @DisplayName(
      "Test buildClient(RestClientConfig); when RestClientConfig(); then return SslContext Protocol is 'TLSv1.2'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Client RestClientFactory.buildClient(RestClientConfig)"})
  void testBuildClient_whenRestClientConfig_thenReturnSslContextProtocolIsTLSv12() {
    // Arrange and Act
    Client actualBuildClientResult = RestClientFactory.buildClient(new RestClientConfig());

    // Assert
    assertTrue(actualBuildClientResult instanceof JerseyClient);
    SSLContext sslContext = actualBuildClientResult.getSslContext();
    assertEquals("TLSv1.2", sslContext.getProtocol());
    SSLParameters defaultSSLParameters = sslContext.getDefaultSSLParameters();
    assertEquals(1, defaultSSLParameters.getProtocols().length);
    assertEquals(34, defaultSSLParameters.getCipherSuites().length);
    assertFalse(((JerseyClient) actualBuildClientResult).isDefaultSslContext());
    assertSame(
        actualBuildClientResult,
        ((ClientConfig) actualBuildClientResult.getConfiguration()).getClient());
  }
}
