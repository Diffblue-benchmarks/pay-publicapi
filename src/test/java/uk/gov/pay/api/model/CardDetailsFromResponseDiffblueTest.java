package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CardDetailsFromResponseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CardDetailsFromResponse#CardDetailsFromResponse(String, String, String, String,
   *       Address, String, String)}
   *   <li>{@link CardDetailsFromResponse#getCardBrand()}
   *   <li>{@link CardDetailsFromResponse#getCardHolderName()}
   *   <li>{@link CardDetailsFromResponse#getCardType()}
   *   <li>{@link CardDetailsFromResponse#getExpiryDate()}
   *   <li>{@link CardDetailsFromResponse#getFirstDigitsCardNumber()}
   *   <li>{@link CardDetailsFromResponse#getLastDigitsCardNumber()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CardDetailsFromResponse.<init>(String, String, String, String, Address, String, String)",
    "String CardDetailsFromResponse.getCardBrand()",
    "String CardDetailsFromResponse.getCardHolderName()",
    "String CardDetailsFromResponse.getCardType()",
    "String CardDetailsFromResponse.getExpiryDate()",
    "String CardDetailsFromResponse.getFirstDigitsCardNumber()",
    "String CardDetailsFromResponse.getLastDigitsCardNumber()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");

    // Act
    CardDetailsFromResponse actualCardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");
    String actualCardBrand = actualCardDetailsFromResponse.getCardBrand();
    String actualCardHolderName = actualCardDetailsFromResponse.getCardHolderName();
    String actualCardType = actualCardDetailsFromResponse.getCardType();
    String actualExpiryDate = actualCardDetailsFromResponse.getExpiryDate();
    String actualFirstDigitsCardNumber = actualCardDetailsFromResponse.getFirstDigitsCardNumber();

    // Assert
    assertEquals("2020-03-01", actualExpiryDate);
    assertEquals("42", actualFirstDigitsCardNumber);
    assertEquals("42", actualCardDetailsFromResponse.getLastDigitsCardNumber());
    assertEquals("Card Brand", actualCardBrand);
    assertEquals("Card Holder Name", actualCardHolderName);
    assertEquals("Card Type", actualCardType);
  }

  /**
   * Test {@link CardDetailsFromResponse#getBillingAddress()}.
   *
   * <p>Method under test: {@link CardDetailsFromResponse#getBillingAddress()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardDetailsFromResponse.getBillingAddress()"})
  public void testGetBillingAddress() {
    // Arrange
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");
    CardDetailsFromResponse cardDetailsFromResponse =
        new CardDetailsFromResponse(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type");

    // Act
    Optional<Address> actualBillingAddress = cardDetailsFromResponse.getBillingAddress();

    // Assert
    assertTrue(actualBillingAddress.isPresent());
    assertSame(billingAddress, actualBillingAddress.get());
  }
}
