package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TokenPaymentTypeDiffblueTest {
  /**
   * Test {@link TokenPaymentType#getFriendlyName()}.
   *
   * <p>Method under test: {@link TokenPaymentType#getFriendlyName()}
   */
  @Test
  @DisplayName("Test getFriendlyName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String TokenPaymentType.getFriendlyName()"})
  void testGetFriendlyName() {
    // Arrange, Act and Assert
    assertEquals("Card Payment", TokenPaymentType.valueOf("CARD").getFriendlyName());
  }
}
