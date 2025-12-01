package uk.gov.pay.api.model.publicauth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.TokenPaymentType;

class AuthResponseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return AccountId is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AuthResponse#AuthResponse()}
   *   <li>{@link AuthResponse#getAccountId()}
   *   <li>{@link AuthResponse#getTokenLink()}
   *   <li>{@link AuthResponse#getTokenType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; then return AccountId is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthResponse.<init>()",
    "void AuthResponse.<init>(String, String, TokenPaymentType)",
    "String AuthResponse.getAccountId()",
    "String AuthResponse.getTokenLink()",
    "TokenPaymentType AuthResponse.getTokenType()"
  })
  void testGettersAndSetters_thenReturnAccountIdIsNull() {
    // Arrange and Act
    AuthResponse actualAuthResponse = new AuthResponse();
    String actualAccountId = actualAuthResponse.getAccountId();
    String actualTokenLink = actualAuthResponse.getTokenLink();

    // Assert
    assertNull(actualAccountId);
    assertNull(actualTokenLink);
    assertNull(actualAuthResponse.getTokenType());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return AccountId is {@code 42}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AuthResponse#AuthResponse(String, String, TokenPaymentType)}
   *   <li>{@link AuthResponse#getAccountId()}
   *   <li>{@link AuthResponse#getTokenLink()}
   *   <li>{@link AuthResponse#getTokenType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when '42'; then return AccountId is '42'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthResponse.<init>()",
    "void AuthResponse.<init>(String, String, TokenPaymentType)",
    "String AuthResponse.getAccountId()",
    "String AuthResponse.getTokenLink()",
    "TokenPaymentType AuthResponse.getTokenType()"
  })
  void testGettersAndSetters_when42_thenReturnAccountIdIs42() {
    // Arrange and Act
    AuthResponse actualAuthResponse = new AuthResponse("42", "ABC123", TokenPaymentType.CARD);
    String actualAccountId = actualAuthResponse.getAccountId();
    String actualTokenLink = actualAuthResponse.getTokenLink();

    // Assert
    assertEquals("42", actualAccountId);
    assertEquals("ABC123", actualTokenLink);
    assertEquals(TokenPaymentType.CARD, actualAuthResponse.getTokenType());
  }
}
