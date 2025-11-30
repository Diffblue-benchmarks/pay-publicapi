package uk.gov.pay.api.model.publicauth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.TokenPaymentType;

public class AuthResponseDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthResponse.<init>()",
    "void AuthResponse.<init>(String, String, TokenPaymentType)",
    "String AuthResponse.getAccountId()",
    "String AuthResponse.getTokenLink()",
    "TokenPaymentType AuthResponse.getTokenType()"
  })
  public void testGettersAndSetters_thenReturnAccountIdIsNull() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthResponse.<init>()",
    "void AuthResponse.<init>(String, String, TokenPaymentType)",
    "String AuthResponse.getAccountId()",
    "String AuthResponse.getTokenLink()",
    "TokenPaymentType AuthResponse.getTokenType()"
  })
  public void testGettersAndSetters_when42_thenReturnAccountIdIs42() {
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
