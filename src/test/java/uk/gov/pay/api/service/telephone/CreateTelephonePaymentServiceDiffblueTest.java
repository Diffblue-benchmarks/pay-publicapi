package uk.gov.pay.api.service.telephone;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.auth.Account;
import uk.gov.pay.api.exception.ConnectorResponseErrorException;
import uk.gov.pay.api.exception.ConnectorResponseErrorException.ConnectorErrorResponse;
import uk.gov.pay.api.exception.CreateChargeException;
import uk.gov.pay.api.model.TokenPaymentType;
import uk.gov.pay.api.model.telephone.CreateTelephonePaymentRequest;
import uk.gov.pay.api.model.telephone.PaymentOutcome;
import uk.gov.pay.api.service.ConnectorUriGenerator;

public class CreateTelephonePaymentServiceDiffblueTest {
  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate2() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode(null)
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate3() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate(null)
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate4() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry(null)
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate5() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType(null)
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate6() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate(null)
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate7() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress(null)
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate8() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits(null)
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate9() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits(null)
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate10() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard(null);

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber("6625550144")
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }

  /**
   * Test {@link CreateTelephonePaymentService#create(Account, CreateTelephonePaymentRequest)}.
   *
   * <p>Method under test: {@link CreateTelephonePaymentService#create(Account,
   * CreateTelephonePaymentRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.apache.commons.lang3.tuple.Pair CreateTelephonePaymentService.create(Account, CreateTelephonePaymentRequest)"
  })
  public void testCreate11() {
    // Arrange
    Response response = mock(Response.class);
    when(response.getStatus()).thenReturn(1);
    when(response.readEntity(ConnectorErrorResponse.class))
        .thenReturn(new ConnectorErrorResponse());
    doNothing().when(response).close();

    Builder builder = mock(Builder.class);
    when(builder.post(Mockito.<Entity<?>>any())).thenReturn(response);

    Builder builder2 = mock(Builder.class);
    when(builder2.accept(isA(String[].class))).thenReturn(builder);

    WebTarget webTarget = mock(WebTarget.class);
    when(webTarget.request()).thenReturn(builder2);

    Client client = mock(Client.class);
    when(client.target(Mockito.<String>any())).thenReturn(webTarget);

    ConnectorUriGenerator connectorUriGenerator = mock(ConnectorUriGenerator.class);
    when(connectorUriGenerator.telephoneChargesURI(Mockito.<Account>any()))
        .thenReturn("6625550144");

    CreateTelephonePaymentService createTelephonePaymentService =
        new CreateTelephonePaymentService(client, connectorUriGenerator);
    Account account = new Account("42", TokenPaymentType.CARD, "ABC123");

    CreateTelephonePaymentRequest.Builder withNameOnCardResult =
        new CreateTelephonePaymentRequest.Builder()
            .withAmount(10)
            .withAuthCode("Auth Code")
            .withAuthorisedDate("2020-03-01")
            .withCardExpiry("Card Expiry")
            .withCardType("Card Type")
            .withCreatedDate("2020-03-01")
            .withDescription("The characteristics of someone or something")
            .withEmailAddress("42 Main St")
            .withFirstSixDigits("First Six Digits")
            .withLastFourDigits("Last Four Digits")
            .withNameOnCard("Name On Card");

    // Act and Assert
    assertThrows(
        CreateChargeException.class,
        () ->
            createTelephonePaymentService.create(
                account,
                withNameOnCardResult
                    .withPaymentOutcome(new PaymentOutcome("Status"))
                    .withProcessorId("42")
                    .withProviderId("42")
                    .withReference("Reference")
                    .withTelephoneNumber(null)
                    .build()));
    verify(client).target("6625550144");
    verify(builder2).accept(isA(String[].class));
    verify(builder).post(isA(Entity.class));
    verify(webTarget).request();
    verify(response).close();
    verify(response, atLeast(1)).getStatus();
    verify(response).readEntity(isA(Class.class));
    verify(connectorUriGenerator).telephoneChargesURI(isA(Account.class));
  }
}
