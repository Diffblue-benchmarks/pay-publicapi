package uk.gov.pay.api.auth;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.TokenPaymentType;

public class AccountDiffblueTest {
  /**
   * Test {@link Account#getName()}.
   *
   * <p>Method under test: {@link Account#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Account.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("42", new Account("42", TokenPaymentType.CARD, "ABC123").getName());
  }
}
