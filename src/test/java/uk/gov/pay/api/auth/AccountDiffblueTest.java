package uk.gov.pay.api.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.TokenPaymentType;

class AccountDiffblueTest {
  /**
   * Test {@link Account#getName()}.
   *
   * <p>Method under test: {@link Account#getName()}
   */
  @Test
  @DisplayName("Test getName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Account.getName()"})
  void testGetName() {
    // Arrange, Act and Assert
    assertEquals("42", new Account("42", TokenPaymentType.CARD, "ABC123").getName());
  }
}
