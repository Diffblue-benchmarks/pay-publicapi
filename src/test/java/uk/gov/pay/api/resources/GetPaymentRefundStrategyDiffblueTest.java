package uk.gov.pay.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.client.Client;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.app.config.PublicApiConfig;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.ledger.service.LedgerUriGenerator;
import uk.gov.pay.api.model.RefundFromConnector;
import uk.gov.pay.api.model.RefundResponse;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.links.Link;
import uk.gov.pay.api.model.links.RefundLinksForSearch;
import uk.gov.pay.api.service.ConnectorService;
import uk.gov.pay.api.service.GetPaymentRefundService;
import uk.gov.pay.api.service.LedgerService;
import uk.gov.pay.api.service.PublicApiUriGenerator;

public class GetPaymentRefundStrategyDiffblueTest {
  /**
   * Test {@link GetPaymentRefundStrategy#executeLedgerOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentRefundStrategy#executeLedgerOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundResponse GetPaymentRefundStrategy.executeLedgerOnlyStrategy()"})
  public void testExecuteLedgerOnlyStrategy_thenReturnNull() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    GetPaymentRefundService getPaymentRefundsService = mock(GetPaymentRefundService.class);
    when(getPaymentRefundsService.getLedgerPaymentRefund(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    GetPaymentRefundStrategy getPaymentRefundStrategy =
        new GetPaymentRefundStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            "42",
            getPaymentRefundsService);

    // Act
    RefundResponse actualExecuteLedgerOnlyStrategyResult =
        getPaymentRefundStrategy.executeLedgerOnlyStrategy();

    // Assert
    verify(getPaymentRefundsService).getLedgerPaymentRefund(isA(Account.class), eq("42"), eq("42"));
    assertNull(actualExecuteLedgerOnlyStrategyResult);
  }

  /**
   * Test {@link GetPaymentRefundStrategy#executeDefaultStrategy()}.
   *
   * <ul>
   *   <li>Then return Links Payment Method is {@code GET}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentRefundStrategy#executeDefaultStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundResponse GetPaymentRefundStrategy.executeDefaultStrategy()"})
  public void testExecuteDefaultStrategy_thenReturnLinksPaymentMethodIsGet() {
    // Arrange
    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getPaymentRefund(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new RefundFromConnector());

    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getRefundsURI(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundService getPaymentRefundsService =
        new GetPaymentRefundService(connectorService, ledgerService, publicApiUriGenerator);
    GetPaymentRefundStrategy getPaymentRefundStrategy =
        new GetPaymentRefundStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            "42",
            getPaymentRefundsService);

    // Act
    RefundResponse actualExecuteDefaultStrategyResult =
        getPaymentRefundStrategy.executeDefaultStrategy();

    // Assert
    verify(connectorService).getPaymentRefund("42", "42", "42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    verify(publicApiUriGenerator).getRefundsURI("42", "42");
    RefundLinksForSearch links = actualExecuteDefaultStrategyResult.getLinks();
    Link payment = links.getPayment();
    assertEquals("GET", payment.getMethod());
    assertNull(actualExecuteDefaultStrategyResult.getAmount());
    assertNull(actualExecuteDefaultStrategyResult.getCreatedDate());
    assertNull(actualExecuteDefaultStrategyResult.getRefundId());
    assertNull(actualExecuteDefaultStrategyResult.getStatus());
    assertNull(actualExecuteDefaultStrategyResult.getSettlementSummary().getSettledDate());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        payment.getHref());
    assertEquals(payment, links.getSelf());
  }

  /**
   * Test {@link GetPaymentRefundStrategy#executeDefaultStrategy()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentRefundStrategy#executeDefaultStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundResponse GetPaymentRefundStrategy.executeDefaultStrategy()"})
  public void testExecuteDefaultStrategy_thenReturnNull() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    GetPaymentRefundService getPaymentRefundsService = mock(GetPaymentRefundService.class);
    when(getPaymentRefundsService.getPaymentRefund(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    GetPaymentRefundStrategy getPaymentRefundStrategy =
        new GetPaymentRefundStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            "42",
            getPaymentRefundsService);

    // Act
    RefundResponse actualExecuteDefaultStrategyResult =
        getPaymentRefundStrategy.executeDefaultStrategy();

    // Assert
    verify(getPaymentRefundsService).getPaymentRefund(isA(Account.class), eq("42"), eq("42"));
    assertNull(actualExecuteDefaultStrategyResult);
  }

  /**
   * Test {@link GetPaymentRefundStrategy#executeConnectorOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return Links Payment Method is {@code GET}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentRefundStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundResponse GetPaymentRefundStrategy.executeConnectorOnlyStrategy()"})
  public void testExecuteConnectorOnlyStrategy_thenReturnLinksPaymentMethodIsGet() {
    // Arrange
    ConnectorService connectorService = mock(ConnectorService.class);
    when(connectorService.getPaymentRefund(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new RefundFromConnector());

    PublicApiUriGenerator publicApiUriGenerator = mock(PublicApiUriGenerator.class);
    when(publicApiUriGenerator.getPaymentURI(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    when(publicApiUriGenerator.getRefundsURI(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    Client client = mock(Client.class);
    LedgerService ledgerService =
        new LedgerService(client, new LedgerUriGenerator(new PublicApiConfig()));

    GetPaymentRefundService getPaymentRefundsService =
        new GetPaymentRefundService(connectorService, ledgerService, publicApiUriGenerator);
    GetPaymentRefundStrategy getPaymentRefundStrategy =
        new GetPaymentRefundStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            "42",
            getPaymentRefundsService);

    // Act
    RefundResponse actualExecuteConnectorOnlyStrategyResult =
        getPaymentRefundStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(connectorService).getPaymentRefund("42", "42", "42");
    verify(publicApiUriGenerator).getPaymentURI("42");
    verify(publicApiUriGenerator).getRefundsURI("42", "42");
    RefundLinksForSearch links = actualExecuteConnectorOnlyStrategyResult.getLinks();
    Link payment = links.getPayment();
    assertEquals("GET", payment.getMethod());
    assertNull(actualExecuteConnectorOnlyStrategyResult.getAmount());
    assertNull(actualExecuteConnectorOnlyStrategyResult.getCreatedDate());
    assertNull(actualExecuteConnectorOnlyStrategyResult.getRefundId());
    assertNull(actualExecuteConnectorOnlyStrategyResult.getStatus());
    assertNull(actualExecuteConnectorOnlyStrategyResult.getSettlementSummary().getSettledDate());
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toString(),
        payment.getHref());
    assertEquals(payment, links.getSelf());
  }

  /**
   * Test {@link GetPaymentRefundStrategy#executeConnectorOnlyStrategy()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GetPaymentRefundStrategy#executeConnectorOnlyStrategy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RefundResponse GetPaymentRefundStrategy.executeConnectorOnlyStrategy()"})
  public void testExecuteConnectorOnlyStrategy_thenReturnNull() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    GetPaymentRefundService getPaymentRefundsService = mock(GetPaymentRefundService.class);
    when(getPaymentRefundsService.getConnectorPaymentRefund(
            Mockito.<Account>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    GetPaymentRefundStrategy getPaymentRefundStrategy =
        new GetPaymentRefundStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            "42",
            getPaymentRefundsService);

    // Act
    RefundResponse actualExecuteConnectorOnlyStrategyResult =
        getPaymentRefundStrategy.executeConnectorOnlyStrategy();

    // Assert
    verify(getPaymentRefundsService)
        .getConnectorPaymentRefund(isA(Account.class), eq("42"), eq("42"));
    assertNull(actualExecuteConnectorOnlyStrategyResult);
  }
}
