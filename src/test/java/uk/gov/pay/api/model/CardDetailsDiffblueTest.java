package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CardDetailsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CardDetails#CardDetails(String, String, String, String, Address, String, String,
   *       String)}
   *   <li>{@link CardDetails#setWalletType(String)}
   *   <li>{@link CardDetails#getCardBrand()}
   *   <li>{@link CardDetails#getCardHolderName()}
   *   <li>{@link CardDetails#getCardType()}
   *   <li>{@link CardDetails#getExpiryDate()}
   *   <li>{@link CardDetails#getFirstDigitsCardNumber()}
   *   <li>{@link CardDetails#getLastDigitsCardNumber()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CardDetails.<init>(String, String, String, String, Address, String, String, String)",
    "String CardDetails.getCardBrand()",
    "String CardDetails.getCardHolderName()",
    "String CardDetails.getCardType()",
    "String CardDetails.getExpiryDate()",
    "String CardDetails.getFirstDigitsCardNumber()",
    "String CardDetails.getLastDigitsCardNumber()",
    "void CardDetails.setWalletType(String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    Address billingAddress = new Address("Line1", "Line2", "OX1 1PT", "Oxford", "GB");

    // Act
    CardDetails actualCardDetails =
        new CardDetails(
            "42",
            "42",
            "Card Holder Name",
            "2020-03-01",
            billingAddress,
            "Card Brand",
            "Card Type",
            "Wallet Type");
    actualCardDetails.setWalletType("Wallet Type");
    String actualCardBrand = actualCardDetails.getCardBrand();
    String actualCardHolderName = actualCardDetails.getCardHolderName();
    String actualCardType = actualCardDetails.getCardType();
    String actualExpiryDate = actualCardDetails.getExpiryDate();
    String actualFirstDigitsCardNumber = actualCardDetails.getFirstDigitsCardNumber();

    // Assert
    assertEquals("2020-03-01", actualExpiryDate);
    assertEquals("42", actualFirstDigitsCardNumber);
    assertEquals("42", actualCardDetails.getLastDigitsCardNumber());
    assertEquals("Card Brand", actualCardBrand);
    assertEquals("Card Holder Name", actualCardHolderName);
    assertEquals("Card Type", actualCardType);
  }

  /**
   * Test {@link CardDetails#from(CardDetailsFromResponse, String)}.
   *
   * <ul>
   *   <li>Then return ExpiryDate is {@code 2020-03-01}.
   * </ul>
   *
   * <p>Method under test: {@link CardDetails#from(CardDetailsFromResponse, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CardDetails CardDetails.from(CardDetailsFromResponse, String)"})
  public void testFrom_thenReturnExpiryDateIs20200301() {
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
    CardDetails actualFromResult = CardDetails.from(cardDetailsFromResponse, "Wallet Type");

    // Assert
    assertEquals("2020-03-01", actualFromResult.getExpiryDate());
    assertEquals("42", actualFromResult.getFirstDigitsCardNumber());
    assertEquals("42", actualFromResult.getLastDigitsCardNumber());
    assertEquals("Card Brand", actualFromResult.getCardBrand());
    assertEquals("Card Holder Name", actualFromResult.getCardHolderName());
    assertEquals("Card Type", actualFromResult.getCardType());
    Optional<String> walletType = actualFromResult.getWalletType();
    assertEquals("Wallet Type", walletType.get());
    Optional<Address> billingAddress2 = actualFromResult.getBillingAddress();
    assertTrue(billingAddress2.isPresent());
    assertTrue(walletType.isPresent());
    assertSame(billingAddress, billingAddress2.get());
  }

  /**
   * Test {@link CardDetails#from(CardDetailsFromResponse, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CardDetails#from(CardDetailsFromResponse, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CardDetails CardDetails.from(CardDetailsFromResponse, String)"})
  public void testFrom_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(CardDetails.from(null, "Wallet Type"));
  }

  /**
   * Test {@link CardDetails#getBillingAddress()}.
   *
   * <p>Method under test: {@link CardDetails#getBillingAddress()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardDetails.getBillingAddress()"})
  public void testGetBillingAddress() {
    // Arrange
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

    // Act
    Optional<Address> actualBillingAddress = cardDetails.getBillingAddress();

    // Assert
    assertTrue(actualBillingAddress.isPresent());
    assertSame(billingAddress, actualBillingAddress.get());
  }

  /**
   * Test {@link CardDetails#getWalletType()}.
   *
   * <p>Method under test: {@link CardDetails#getWalletType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CardDetails.getWalletType()"})
  public void testGetWalletType() {
    // Arrange
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

    // Act
    Optional<String> actualWalletType = cardDetails.getWalletType();

    // Assert
    assertEquals("Wallet Type", actualWalletType.get());
    assertTrue(actualWalletType.isPresent());
  }
}
