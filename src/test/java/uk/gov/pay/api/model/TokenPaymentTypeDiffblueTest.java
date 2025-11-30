package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TokenPaymentTypeDiffblueTest {
  /**
   * Test {@link TokenPaymentType#getFriendlyName()}.
   *
   * <p>Method under test: {@link TokenPaymentType#getFriendlyName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String TokenPaymentType.getFriendlyName()"})
  public void testGetFriendlyName() {
    // Arrange, Act and Assert
    assertEquals("Card Payment", TokenPaymentType.valueOf("CARD").getFriendlyName());
  }
}
