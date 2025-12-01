package uk.gov.pay.api.filter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.SecurityContext;
import java.net.URI;
import java.nio.file.Paths;
import org.glassfish.jersey.internal.MapPropertiesDelegate;
import org.glassfish.jersey.server.ContainerRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.model.TokenPaymentType;

class LoggingMDCRequestFilterDiffblueTest {
  /**
   * Test {@link LoggingMDCRequestFilter#filter(ContainerRequestContext)}.
   *
   * <ul>
   *   <li>Given {@link Account#Account(String, TokenPaymentType, String)} with accountId is {@code
   *       42} and paymentType is {@code CARD} and tokenLink is {@code ABC123}.
   * </ul>
   *
   * <p>Method under test: {@link LoggingMDCRequestFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName(
      "Test filter(ContainerRequestContext); given Account(String, TokenPaymentType, String) with accountId is '42' and paymentType is 'CARD' and tokenLink is 'ABC123'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LoggingMDCRequestFilter.filter(ContainerRequestContext)"})
  void testFilter_givenAccountWithAccountIdIs42AndPaymentTypeIsCardAndTokenLinkIsAbc123() {
    // Arrange
    LoggingMDCRequestFilter loggingMDCRequestFilter = new LoggingMDCRequestFilter();

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal())
        .thenReturn(new Account("42", TokenPaymentType.CARD, "ABC123"));
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    loggingMDCRequestFilter.filter(requestContext);

    // Assert
    verify(securityContext).getUserPrincipal();
  }

  /**
   * Test {@link LoggingMDCRequestFilter#filter(ContainerRequestContext)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link SecurityContext} {@link SecurityContext#getUserPrincipal()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link LoggingMDCRequestFilter#filter(ContainerRequestContext)}
   */
  @Test
  @DisplayName(
      "Test filter(ContainerRequestContext); given 'null'; when SecurityContext getUserPrincipal() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LoggingMDCRequestFilter.filter(ContainerRequestContext)"})
  void testFilter_givenNull_whenSecurityContextGetUserPrincipalReturnNull() {
    // Arrange
    LoggingMDCRequestFilter loggingMDCRequestFilter = new LoggingMDCRequestFilter();

    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getUserPrincipal()).thenReturn(null);
    URI baseUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    URI requestUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    ContainerRequest requestContext =
        new ContainerRequest(
            baseUri,
            requestUri,
            "https://example.org/example",
            securityContext,
            new MapPropertiesDelegate());

    // Act
    loggingMDCRequestFilter.filter(requestContext);

    // Assert
    verify(securityContext).getUserPrincipal();
  }
}
