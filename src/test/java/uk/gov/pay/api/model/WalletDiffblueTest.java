package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WalletDiffblueTest {
  /**
   * Test {@link Wallet#getTitleCase()}.
   *
   * <p>Method under test: {@link Wallet#getTitleCase()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Wallet.getTitleCase()"})
  public void testGetTitleCase() {
    // Arrange, Act and Assert
    assertEquals("Apple Pay", Wallet.valueOf("APPLE_PAY").getTitleCase());
  }
}
