package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WalletDiffblueTest {
  /**
   * Test {@link Wallet#getTitleCase()}.
   *
   * <p>Method under test: {@link Wallet#getTitleCase()}
   */
  @Test
  @DisplayName("Test getTitleCase()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Wallet.getTitleCase()"})
  void testGetTitleCase() {
    // Arrange, Act and Assert
    assertEquals("Apple Pay", Wallet.valueOf("APPLE_PAY").getTitleCase());
  }
}
