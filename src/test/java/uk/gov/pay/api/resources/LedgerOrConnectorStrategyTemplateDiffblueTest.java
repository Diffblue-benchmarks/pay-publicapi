package uk.gov.pay.api.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.model.Address;
import uk.gov.pay.api.model.AuthorisationSummary;
import uk.gov.pay.api.model.CardDetails;
import uk.gov.pay.api.model.Exemption;
import uk.gov.pay.api.model.PaymentSettlementSummary;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.RefundSummary;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.links.PaymentWithAllLinks;
import uk.gov.pay.api.model.links.PaymentWithAllLinks.PaymentWithAllLinksBuilder;
import uk.gov.pay.api.service.GetPaymentService;
import uk.gov.service.payments.commons.model.AgreementPaymentType;
import uk.gov.service.payments.commons.model.AuthorisationMode;
import uk.gov.service.payments.commons.model.SupportedLanguage;
import uk.gov.service.payments.commons.model.charge.ExternalMetadata;

public class LedgerOrConnectorStrategyTemplateDiffblueTest {
  /**
   * Test {@link LedgerOrConnectorStrategyTemplate#validateAndExecute()}.
   *
   * <p>Method under test: {@link LedgerOrConnectorStrategyTemplate#validateAndExecute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object LedgerOrConnectorStrategyTemplate.validateAndExecute()"})
  public void testValidateAndExecute() {
    // Arrange
    GetPaymentService getPaymentService = mock(GetPaymentService.class);

    PaymentWithAllLinksBuilder withAuthorisationModeResult =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult =
        withAuthorisationModeResult.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult =
        withAuthorisationSummaryResult
            .withCardDetails(cardDetails)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult =
        withEmailResult
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult =
        withLanguageResult
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult =
        withPaymentCaptureUriResult
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult =
        withReferenceResult
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult =
        withSelfLinkResult.withSettlementSummary(settlementSummary);
    when(getPaymentService.getPayment(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(
            withSettlementSummaryResult
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "Strategy",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    getOnePaymentStrategy.validateAndExecute();

    // Assert
    verify(getPaymentService).getPayment(isA(Account.class), eq("42"));
  }

  /**
   * Test {@link LedgerOrConnectorStrategyTemplate#validateAndExecute()}.
   *
   * <p>Method under test: {@link LedgerOrConnectorStrategyTemplate#validateAndExecute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object LedgerOrConnectorStrategyTemplate.validateAndExecute()"})
  public void testValidateAndExecute2() {
    // Arrange
    PaymentWithAllLinksBuilder withAuthorisationModeResult =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult =
        withAuthorisationModeResult.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult =
        withAuthorisationSummaryResult
            .withCardDetails(cardDetails)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult =
        withEmailResult
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult =
        withLanguageResult
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult =
        withPaymentCaptureUriResult
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult =
        withReferenceResult
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult =
        withSelfLinkResult.withSettlementSummary(settlementSummary);
    PaymentState state = new PaymentState("Status", true);
    withSettlementSummaryResult.withState(state).withTotalAmount(1L).build();

    GetPaymentService getPaymentService = mock(GetPaymentService.class);

    PaymentWithAllLinksBuilder withAuthorisationModeResult2 =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult2 =
        withAuthorisationModeResult2.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress2 = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails2 =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress2,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult2 =
        withAuthorisationSummaryResult2
            .withCardDetails(cardDetails2)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult2 =
        withEmailResult2
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult2 =
        withLanguageResult2
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult2 =
        withPaymentCaptureUriResult2
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult2 =
        withReferenceResult2
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary2 =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult2 =
        withSelfLinkResult2.withSettlementSummary(settlementSummary2);
    when(getPaymentService.getPayment(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(
            withSettlementSummaryResult2
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "", new Account("42", TokenPaymentType.CARD, "ABC123"), "42", getPaymentService);

    // Act
    PaymentWithAllLinks actualValidateAndExecuteResult = getOnePaymentStrategy.validateAndExecute();

    // Assert
    verify(getPaymentService).getPayment(isA(Account.class), eq("42"));
    assertEquals(
        billingAddress,
        actualValidateAndExecuteResult.getCardDetails().get().getBillingAddress().get());
    assertEquals(state, actualValidateAndExecuteResult.getState());
  }

  /**
   * Test {@link LedgerOrConnectorStrategyTemplate#validateAndExecute()}.
   *
   * <ul>
   *   <li>Then calls {@link GetPaymentService#getConnectorCharge(Account, String)}.
   * </ul>
   *
   * <p>Method under test: {@link LedgerOrConnectorStrategyTemplate#validateAndExecute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object LedgerOrConnectorStrategyTemplate.validateAndExecute()"})
  public void testValidateAndExecute_thenCallsGetConnectorCharge() {
    // Arrange
    PaymentWithAllLinksBuilder withAuthorisationModeResult =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult =
        withAuthorisationModeResult.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult =
        withAuthorisationSummaryResult
            .withCardDetails(cardDetails)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult =
        withEmailResult
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult =
        withLanguageResult
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult =
        withPaymentCaptureUriResult
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult =
        withReferenceResult
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult =
        withSelfLinkResult.withSettlementSummary(settlementSummary);
    withSettlementSummaryResult
        .withState(new PaymentState("Status", true))
        .withTotalAmount(1L)
        .build();

    GetPaymentService getPaymentService = mock(GetPaymentService.class);

    PaymentWithAllLinksBuilder withAuthorisationModeResult2 =
        new PaymentWithAllLinksBuilder()
            .withAgreementId("42")
            .withAgreementPaymentType(AgreementPaymentType.INSTALMENT)
            .withAmount(10L)
            .withAuthorisationMode(AuthorisationMode.WEB);

    PaymentWithAllLinksBuilder withAuthorisationSummaryResult2 =
        withAuthorisationModeResult2.withAuthorisationSummary(new AuthorisationSummary());
    Address billingAddress2 = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetails cardDetails2 =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress2,
            "Card Brand",
            "Card Type",
            "Wallet Type");

    PaymentWithAllLinksBuilder withEmailResult2 =
        withAuthorisationSummaryResult2
            .withCardDetails(cardDetails2)
            .withChargeId("42")
            .withCorporateCardSurcharge(1L)
            .withCreatedDate("2020-03-01")
            .withDelayedCapture(true)
            .withDescription("The characteristics of someone or something")
            .withEmail("jane.doe@example.org");

    PaymentWithAllLinksBuilder withLanguageResult2 =
        withEmailResult2
            .withExemption(new Exemption())
            .withFee(1L)
            .withLanguage(SupportedLanguage.ENGLISH);

    PaymentWithAllLinksBuilder withPaymentCaptureUriResult2 =
        withLanguageResult2
            .withMetadata(new ExternalMetadata(new HashMap<>()))
            .withMoto(true)
            .withNetAmount(1L)
            .withPaymentAuthorisationUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCancelUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentCaptureUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    PaymentWithAllLinksBuilder withReferenceResult2 =
        withPaymentCaptureUriResult2
            .withPaymentConnectorResponseLinks(new ArrayList<>())
            .withPaymentEventsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withPaymentProvider("Payment Provider")
            .withPaymentRefundsUri(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .withProviderId("42")
            .withReference("Reference");

    PaymentWithAllLinksBuilder withSelfLinkResult2 =
        withReferenceResult2
            .withRefundSummary(new RefundSummary("Status", 10L, 10L))
            .withReturnUrl("https://example.org/example")
            .withSelfLink(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    PaymentSettlementSummary settlementSummary2 =
        new PaymentSettlementSummary("Capture Submit Time", "2020-03-01", "2020-03-01");

    PaymentWithAllLinksBuilder withSettlementSummaryResult2 =
        withSelfLinkResult2.withSettlementSummary(settlementSummary2);
    when(getPaymentService.getConnectorCharge(Mockito.<Account>any(), Mockito.<String>any()))
        .thenReturn(
            withSettlementSummaryResult2
                .withState(new PaymentState("Status", true))
                .withTotalAmount(1L)
                .build());
    GetOnePaymentStrategy getOnePaymentStrategy =
        new GetOnePaymentStrategy(
            "connector-only",
            new Account("42", TokenPaymentType.CARD, "ABC123"),
            "42",
            getPaymentService);

    // Act
    getOnePaymentStrategy.validateAndExecute();

    // Assert
    verify(getPaymentService).getConnectorCharge(isA(Account.class), eq("42"));
  }
}
