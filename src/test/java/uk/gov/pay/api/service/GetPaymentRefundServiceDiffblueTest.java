package uk.gov.pay.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.AdminFactory;
import io.dropwizard.core.setup.HealthCheckConfiguration;
import io.dropwizard.health.HealthFactory;
import io.dropwizard.logging.common.DefaultLoggingFactory;
import io.dropwizard.metrics.common.MetricsFactory;
import io.dropwizard.servlets.tasks.TaskConfiguration;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.nio.file.Paths;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.exception.ConnectorResponseErrorException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException.ConnectorErrorResponse;
import uk.gov.pay.api.exception.GetRefundException;
import uk.gov.pay.api.ledger.service.LedgerUriGenerator;
import uk.gov.pay.api.model.RefundFromConnector;
import uk.gov.pay.api.model.RefundResponse;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.RefundLinksForSearch;

public class GetPaymentRefundServiceDiffblueTest {
  /**
   * Test {@link GetPaymentRefundService#getConnectorPaymentRefund(Account, String, String)}.
   *
   * <ul>
   *   <li>Then return Links Payment Method is {@code GET}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentRefundService#getConnectorPaymentRefund(Account, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundResponse GetPaymentRefundService.getConnectorPaymentRefund(Account, String, String)"
  })
  public void testGetConnectorPaymentRefund_thenReturnLinksPaymentMethodIsGet() {
    // Arrange
    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getPaymentRefund(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new RefundFromConnector());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getRefundsURI(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    GetPaymentRefundService getPaymentRefundService =
        new GetPaymentRefundService(connectorService, ledgerService, publicApiUriGenerator);

    // Act
    RefundResponse actualConnectorPaymentRefund =
        getPaymentRefundService.getConnectorPaymentRefund(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42");

    // Assert
    verify(connectorService).getPaymentRefund("42", "42", "42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    verify(publicApiUriGenerator).getRefundsURI("42", "42");
    RefundLinksForSearch links = actualConnectorPaymentRefund.getLinks();
    Link payment = links.getPayment();
    assertEquals("GET", payment.getMethod());
    assertNull(actualConnectorPaymentRefund.getAmount());
    assertNull(actualConnectorPaymentRefund.getCreatedDate());
    assertNull(actualConnectorPaymentRefund.getRefundId());
    assertNull(actualConnectorPaymentRefund.getStatus());
    assertNull(actualConnectorPaymentRefund.getSettlementSummary().getSettledDate());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        payment.getHref());
    assertEquals(payment, links.getSelf());
  }

  /**
   * Test {@link GetPaymentRefundService#getLedgerPaymentRefund(Account, String, String)}.
   *
   * <p>Method under test: {@link GetPaymentRefundService#getLedgerPaymentRefund(Account, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundResponse GetPaymentRefundService.getLedgerPaymentRefund(Account, String, String)"
  })
  public void testGetLedgerPaymentRefund() {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

    Builder builder = mock(Builder.class);
    when(builder.get())
        .thenReturn(new OutboundJaxrsResponse(Status.CREATED, new OutboundMessageContext()));

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(admin);
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());

    GetPaymentRefundService getPaymentRefundService =
        new GetPaymentRefundService(
            connectorService, ledgerService, new PublicApiUriGenerator(configuration2));

    // Act and Assert
    assertThrows(
        GetRefundException.class,
        () ->
            getPaymentRefundService.getLedgerPaymentRefund(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("REFUND"), eq("42"));
  }

  /**
   * Test {@link GetPaymentRefundService#getLedgerPaymentRefund(Account, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link OutboundJaxrsResponse#close()}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentRefundService#getLedgerPaymentRefund(Account, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundResponse GetPaymentRefundService.getLedgerPaymentRefund(Account, String, String)"
  })
  public void testGetLedgerPaymentRefund_thenCallsClose() throws ProcessingException {
    // Arrange
    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    ConnectorService connectorService =
        new ConnectorService(mock(Client.class), new ConnectorUriGenerator(configuration));

    OutboundJaxrsResponse outboundJaxrsResponse = mock(OutboundJaxrsResponse.class);
    when(outboundJaxrsResponse.getStatus()).thenReturn(1);
    when(outboundJaxrsResponse.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(outboundJaxrsResponse).close();

    Builder builder = mock(Builder.class);
    when(builder.get()).thenReturn(outboundJaxrsResponse);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    LedgerUriGenerator ledgerUriGenerator = mock(LedgerUriGenerator.class);
    when(ledgerUriGenerator.transactionURI(
            Mockito.<Account>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            Mockito.<String>any()))
        .thenReturn("Transaction URI");

    LedgerService ledgerService = new LedgerService(client, ledgerUriGenerator);

    AdminFactory admin = new AdminFactory();
    admin.setHealthChecks(new HealthCheckConfiguration());
    admin.setTasks(new TaskConfiguration());

    PublicApiConfig configuration2 = new PublicApiConfig();
    configuration2.setAdminFactory(admin);
    configuration2.setHealthFactory(mock(HealthFactory.class));
    configuration2.setLoggingFactory(new DefaultLoggingFactory());
    configuration2.setMetricsFactory(new MetricsFactory());
    configuration2.setServerFactory(new DefaultServerFactory());

    GetPaymentRefundService getPaymentRefundService =
        new GetPaymentRefundService(
            connectorService, ledgerService, new PublicApiUriGenerator(configuration2));

    // Act and Assert
    assertThrows(
        GetRefundException.class,
        () ->
            getPaymentRefundService.getLedgerPaymentRefund(
                new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42"));
    verify(client).target("Transaction URI");
    verify(builder).get();
    verify(webTarget).request();
    verify(outboundJaxrsResponse).close();
    verify(outboundJaxrsResponse, atLeast(1)).getStatus();
    verify(outboundJaxrsResponse).readEntity(isA(Class.class));
    verify(ledgerUriGenerator).transactionURI(isA(Account.class), eq("42"), eq("REFUND"), eq("42"));
  }

  /**
   * Test {@link GetPaymentRefundService#getPaymentRefund(Account, String, String)}.
   *
   * <ul>
   *   <li>Then return Links Payment Method is {@code GET}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentRefundService#getPaymentRefund(Account, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RefundResponse GetPaymentRefundService.getPaymentRefund(Account, String, String)"
  })
  public void testGetPaymentRefund_thenReturnLinksPaymentMethodIsGet() {
    // Arrange
    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getPaymentRefund(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new RefundFromConnector());

    PublicApiConfig configuration = new PublicApiConfig();
    configuration.setAdminFactory(new AdminFactory());
    configuration.setHealthFactory(mock(HealthFactory.class));
    configuration.setLoggingFactory(new DefaultLoggingFactory());
    configuration.setMetricsFactory(new MetricsFactory());
    configuration.setServerFactory(new DefaultServerFactory());
    LedgerService ledgerService =
        new LedgerService(mock(Client.class), new LedgerUriGenerator(configuration));

    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getRefundsURI(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    GetPaymentRefundService getPaymentRefundService =
        new GetPaymentRefundService(connectorService, ledgerService, publicApiUriGenerator);

    // Act
    RefundResponse actualPaymentRefund =
        getPaymentRefundService.getPaymentRefund(
            new Account("42", TokenPaymentType.CARD, "ABC123"), "42", "42");

    // Assert
    verify(connectorService).getPaymentRefund("42", "42", "42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    verify(publicApiUriGenerator).getRefundsURI("42", "42");
    RefundLinksForSearch links = actualPaymentRefund.getLinks();
    Link payment = links.getPayment();
    assertEquals("GET", payment.getMethod());
    assertNull(actualPaymentRefund.getAmount());
    assertNull(actualPaymentRefund.getCreatedDate());
    assertNull(actualPaymentRefund.getRefundId());
    assertNull(actualPaymentRefund.getStatus());
    assertNull(actualPaymentRefund.getSettlementSummary().getSettledDate());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        payment.getHref());
    assertEquals(payment, links.getSelf());
  }
}
