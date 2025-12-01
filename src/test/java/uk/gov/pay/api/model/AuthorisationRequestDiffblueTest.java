package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AuthorisationRequestDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return CardNumber is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AuthorisationRequest#AuthorisationRequest()}
   *   <li>{@link AuthorisationRequest#getCardNumber()}
   *   <li>{@link AuthorisationRequest#getCardholderName()}
   *   <li>{@link AuthorisationRequest#getCvc()}
   *   <li>{@link AuthorisationRequest#getExpiryDate()}
   *   <li>{@link AuthorisationRequest#getOneTimeToken()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return CardNumber is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthorisationRequest.<init>()",
    "void AuthorisationRequest.<init>(String, String, String, String, String)",
    "String AuthorisationRequest.getCardNumber()",
    "String AuthorisationRequest.getCardholderName()",
    "String AuthorisationRequest.getCvc()",
    "String AuthorisationRequest.getExpiryDate()",
    "String AuthorisationRequest.getOneTimeToken()"
  })
  void testGettersAndSetters_thenReturnCardNumberIsNull() {
    // Arrange and Act
    AuthorisationRequest actualAuthorisationRequest = new AuthorisationRequest();
    String actualCardNumber = actualAuthorisationRequest.getCardNumber();
    String actualCardholderName = actualAuthorisationRequest.getCardholderName();
    String actualCvc = actualAuthorisationRequest.getCvc();
    String actualExpiryDate = actualAuthorisationRequest.getExpiryDate();

    // Assert
    assertNull(actualCardNumber);
    assertNull(actualCardholderName);
    assertNull(actualCvc);
    assertNull(actualExpiryDate);
    assertNull(actualAuthorisationRequest.getOneTimeToken());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code ABC123}.
   *   <li>Then return ExpiryDate is {@code 2020-03-01}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AuthorisationRequest#AuthorisationRequest(String, String, String, String, String)}
   *   <li>{@link AuthorisationRequest#getCardNumber()}
   *   <li>{@link AuthorisationRequest#getCardholderName()}
   *   <li>{@link AuthorisationRequest#getCvc()}
   *   <li>{@link AuthorisationRequest#getExpiryDate()}
   *   <li>{@link AuthorisationRequest#getOneTimeToken()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when 'ABC123'; then return ExpiryDate is '2020-03-01'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthorisationRequest.<init>()",
    "void AuthorisationRequest.<init>(String, String, String, String, String)",
    "String AuthorisationRequest.getCardNumber()",
    "String AuthorisationRequest.getCardholderName()",
    "String AuthorisationRequest.getCvc()",
    "String AuthorisationRequest.getExpiryDate()",
    "String AuthorisationRequest.getOneTimeToken()"
  })
  void testGettersAndSetters_whenAbc123_thenReturnExpiryDateIs20200301() {
    // Arrange and Act
    AuthorisationRequest actualAuthorisationRequest =
        new AuthorisationRequest("ABC123", "42", "Cvc", "2020-03-01", "Cardholder Name");
    String actualCardNumber = actualAuthorisationRequest.getCardNumber();
    String actualCardholderName = actualAuthorisationRequest.getCardholderName();
    String actualCvc = actualAuthorisationRequest.getCvc();
    String actualExpiryDate = actualAuthorisationRequest.getExpiryDate();

    // Assert
    assertEquals("2020-03-01", actualExpiryDate);
    assertEquals("42", actualCardNumber);
    assertEquals("ABC123", actualAuthorisationRequest.getOneTimeToken());
    assertEquals("Cardholder Name", actualCardholderName);
    assertEquals("Cvc", actualCvc);
  }
}
