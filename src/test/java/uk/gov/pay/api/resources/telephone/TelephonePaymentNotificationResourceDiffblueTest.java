package uk.gov.pay.api.resources.telephone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.util.Set;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.model.PaymentState;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.telephone.CreateTelephonePaymentRequest;
import uk.gov.pay.api.model.telephone.PaymentOutcome;
import uk.gov.pay.api.model.telephone.TelephonePaymentResponse;
import uk.gov.pay.api.model.telephone.TelephonePaymentResponse.Builder;
import uk.gov.pay.api.service.telephone.CreateTelephonePaymentService;

class TelephonePaymentNotificationResourceDiffblueTest {
  /**
   * Test {@link TelephonePaymentNotificationResource#newPayment(Account,
   * CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link TelephonePaymentNotificationResource#newPayment(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @DisplayName("Test newPayment(Account, CreateTelephonePaymentRequest)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response TelephonePaymentNotificationResource.newPayment(Account, CreateTelephonePaymentRequest)"
  })
  void testNewPayment() {
    // Arrange
    CreateTelephonePaymentService createTelephonePaymentService =
        mock(CreateTelephonePaymentService.class);

    Builder withPaymentIdResult =
        new Builder()
            .withAmount(10L)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card")
            .withPaymentId("42");

    Builder withReferenceResult =
        withPaymentIdResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference");
    TelephonePaymentResponse telephonePaymentResponse =
        withReferenceResult
            .withState(new PaymentState("Status", true))
            .withTelephoneNumber("6625550144")
            .build();
    when(createTelephonePaymentService.create(
            Mockito.<Account>any(), Mockito.<CreateTelephonePaymentRequest>any()))
        .thenReturn(new ImmutablePair<>(telephonePaymentResponse, 1));
    TelephonePaymentNotificationResource telephonePaymentNotificationResource =
        new TelephonePaymentNotificationResource(createTelephonePaymentService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualNewPaymentResult =
        telephonePaymentNotificationResource.newPayment(
            account, new CreateTelephonePaymentRequest());

    // Assert
    verify(createTelephonePaymentService)
        .create(isA(Account.class), isA(CreateTelephonePaymentRequest.class));
    assertTrue(actualNewPaymentResult instanceof OutboundJaxrsResponse);
    assertTrue(actualNewPaymentResult.getEntity() instanceof TelephonePaymentResponse);
    assertNull(actualNewPaymentResult.getEntityTag());
    assertNull(actualNewPaymentResult.getMediaType());
    assertNull(actualNewPaymentResult.getLocation());
    assertNull(actualNewPaymentResult.getDate());
    assertNull(actualNewPaymentResult.getLastModified());
    assertNull(actualNewPaymentResult.getLanguage());
    assertEquals(-1, actualNewPaymentResult.getLength());
    assertEquals(1, actualNewPaymentResult.getStatus());
    assertTrue(actualNewPaymentResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualNewPaymentResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualNewPaymentResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualNewPaymentResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualNewPaymentResult.getLinks());
    assertSame(headers, actualNewPaymentResult.getMetadata());
  }

  /**
   * Test {@link TelephonePaymentNotificationResource#newPayment(Account,
   * CreateTelephonePaymentRequest)}.
   *
   * <ul>
   *   <li>Then calls {@link Pair#getLeft()}.
   * </ul>
   *
   * <p>Method under test: {@link TelephonePaymentNotificationResource#newPayment(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @DisplayName("Test newPayment(Account, CreateTelephonePaymentRequest); then calls getLeft()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Response TelephonePaymentNotificationResource.newPayment(Account, CreateTelephonePaymentRequest)"
  })
  void testNewPayment_thenCallsGetLeft() {
    // Arrange
    Pair<TelephonePaymentResponse, Integer> pair = mock(Pair.class);

    Builder withPaymentIdResult =
        new Builder()
            .withAmount(10L)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card")
            .withPaymentId("42");

    Builder withReferenceResult =
        withPaymentIdResult
            .withPaymentOutcome(new PaymentOutcome("Status"))
            .withProcessorId("42")
            .withProviderId("42")
            .withReference("Reference");
    when(pair.getLeft())
        .thenReturn(
            withReferenceResult
                .withState(new PaymentState("Status", true))
                .withTelephoneNumber("6625550144")
                .build());
    when(pair.getRight()).thenReturn(1);

    CreateTelephonePaymentService createTelephonePaymentService =
        mock(CreateTelephonePaymentService.class);
    when(createTelephonePaymentService.create(
            Mockito.<Account>any(), Mockito.<CreateTelephonePaymentRequest>any()))
        .thenReturn(pair);
    TelephonePaymentNotificationResource telephonePaymentNotificationResource =
        new TelephonePaymentNotificationResource(createTelephonePaymentService);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    // Act
    Response actualNewPaymentResult =
        telephonePaymentNotificationResource.newPayment(
            account, new CreateTelephonePaymentRequest());

    // Assert
    verify(pair).getLeft();
    verify(pair).getRight();
    verify(createTelephonePaymentService)
        .create(isA(Account.class), isA(CreateTelephonePaymentRequest.class));
    assertTrue(actualNewPaymentResult instanceof OutboundJaxrsResponse);
    assertTrue(actualNewPaymentResult.getEntity() instanceof TelephonePaymentResponse);
    assertNull(actualNewPaymentResult.getEntityTag());
    assertNull(actualNewPaymentResult.getMediaType());
    assertNull(actualNewPaymentResult.getLocation());
    assertNull(actualNewPaymentResult.getDate());
    assertNull(actualNewPaymentResult.getLastModified());
    assertNull(actualNewPaymentResult.getLanguage());
    assertEquals(-1, actualNewPaymentResult.getLength());
    assertEquals(1, actualNewPaymentResult.getStatus());
    assertTrue(actualNewPaymentResult.getCookies().isEmpty());
    MultivaluedMap<String, Object> headers = actualNewPaymentResult.getHeaders();
    assertTrue(headers.isEmpty());
    assertTrue(actualNewPaymentResult.getStringHeaders().isEmpty());
    Set<String> allowedMethods = actualNewPaymentResult.getAllowedMethods();
    assertTrue(allowedMethods.isEmpty());
    assertSame(allowedMethods, actualNewPaymentResult.getLinks());
    assertSame(headers, actualNewPaymentResult.getMetadata());
  }
}
