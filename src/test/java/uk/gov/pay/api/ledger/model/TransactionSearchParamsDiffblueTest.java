package uk.gov.pay.api.ledger.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TransactionSearchParamsDiffblueTest {
  /**
   * Test {@link TransactionSearchParams#getQueryMap()}.
   *
   * <p>Method under test: {@link TransactionSearchParams#getQueryMap()}
   */
  @Test
  @DisplayName("Test getQueryMap()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Map TransactionSearchParams.getQueryMap()"})
  void testGetQueryMap() {
    // Arrange and Act
    Map<String, String> actualQueryMap = new TransactionSearchParams().getQueryMap();

    // Assert
    assertEquals(14, actualQueryMap.size());
    assertNull(actualQueryMap.get("account_id"));
    assertNull(actualQueryMap.get("card_brand"));
    assertNull(actualQueryMap.get("cardholder_name"));
    assertNull(actualQueryMap.get("display_size"));
    assertNull(actualQueryMap.get("first_digits_card_number"));
    assertNull(actualQueryMap.get("from_date"));
    assertNull(actualQueryMap.get("from_settled_date"));
    assertNull(actualQueryMap.get("last_digits_card_number"));
    assertNull(actualQueryMap.get("reference"));
    assertNull(actualQueryMap.get("to_date"));
    assertNull(actualQueryMap.get("to_settled_date"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link TransactionSearchParams}
   *   <li>{@link TransactionSearchParams#setAccountId(String)}
   *   <li>{@link TransactionSearchParams#getCardBrand()}
   *   <li>{@link TransactionSearchParams#getCardHolderName()}
   *   <li>{@link TransactionSearchParams#getDisplaySize()}
   *   <li>{@link TransactionSearchParams#getEmail()}
   *   <li>{@link TransactionSearchParams#getFirstDigitsCardNumber()}
   *   <li>{@link TransactionSearchParams#getFromDate()}
   *   <li>{@link TransactionSearchParams#getFromSettledDate()}
   *   <li>{@link TransactionSearchParams#getLastDigitsCardNumber()}
   *   <li>{@link TransactionSearchParams#getPageNumber()}
   *   <li>{@link TransactionSearchParams#getReference()}
   *   <li>{@link TransactionSearchParams#getState()}
   *   <li>{@link TransactionSearchParams#getToDate()}
   *   <li>{@link TransactionSearchParams#getToSettledDate()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TransactionSearchParams.<init>()",
    "String TransactionSearchParams.getCardBrand()",
    "String TransactionSearchParams.getCardHolderName()",
    "String TransactionSearchParams.getDisplaySize()",
    "String TransactionSearchParams.getEmail()",
    "String TransactionSearchParams.getFirstDigitsCardNumber()",
    "String TransactionSearchParams.getFromDate()",
    "String TransactionSearchParams.getFromSettledDate()",
    "String TransactionSearchParams.getLastDigitsCardNumber()",
    "String TransactionSearchParams.getPageNumber()",
    "String TransactionSearchParams.getReference()",
    "String TransactionSearchParams.getState()",
    "String TransactionSearchParams.getToDate()",
    "String TransactionSearchParams.getToSettledDate()",
    "void TransactionSearchParams.setAccountId(String)"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    TransactionSearchParams actualTransactionSearchParams = new TransactionSearchParams();
    actualTransactionSearchParams.setAccountId("42");
    String actualCardBrand = actualTransactionSearchParams.getCardBrand();
    String actualCardHolderName = actualTransactionSearchParams.getCardHolderName();
    String actualDisplaySize = actualTransactionSearchParams.getDisplaySize();
    String actualEmail = actualTransactionSearchParams.getEmail();
    String actualFirstDigitsCardNumber = actualTransactionSearchParams.getFirstDigitsCardNumber();
    String actualFromDate = actualTransactionSearchParams.getFromDate();
    String actualFromSettledDate = actualTransactionSearchParams.getFromSettledDate();
    String actualLastDigitsCardNumber = actualTransactionSearchParams.getLastDigitsCardNumber();
    String actualPageNumber = actualTransactionSearchParams.getPageNumber();
    String actualReference = actualTransactionSearchParams.getReference();
    String actualState = actualTransactionSearchParams.getState();
    String actualToDate = actualTransactionSearchParams.getToDate();

    // Assert
    assertNull(actualCardBrand);
    assertNull(actualCardHolderName);
    assertNull(actualDisplaySize);
    assertNull(actualEmail);
    assertNull(actualFirstDigitsCardNumber);
    assertNull(actualFromDate);
    assertNull(actualFromSettledDate);
    assertNull(actualLastDigitsCardNumber);
    assertNull(actualPageNumber);
    assertNull(actualReference);
    assertNull(actualState);
    assertNull(actualToDate);
    assertNull(actualTransactionSearchParams.getToSettledDate());
  }
}
