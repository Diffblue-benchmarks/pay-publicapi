package uk.gov.pay.api.agreement.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AgreementCreatedResponseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AgreementCreatedResponse#AgreementCreatedResponse()}
   *   <li>{@link AgreementCreatedResponse#toString()}
   *   <li>{@link AgreementCreatedResponse#getAgreementId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementCreatedResponse.<init>()",
    "void AgreementCreatedResponse.<init>(String)",
    "String AgreementCreatedResponse.getAgreementId()",
    "String AgreementCreatedResponse.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AgreementCreatedResponse actualAgreementCreatedResponse = new AgreementCreatedResponse();
    String actualToStringResult = actualAgreementCreatedResponse.toString();

    // Assert
    assertEquals("AgreementCreatedResponse{agreementId='null}", actualToStringResult);
    assertNull(actualAgreementCreatedResponse.getAgreementId());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return AgreementId is {@code 42}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AgreementCreatedResponse#AgreementCreatedResponse(String)}
   *   <li>{@link AgreementCreatedResponse#toString()}
   *   <li>{@link AgreementCreatedResponse#getAgreementId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementCreatedResponse.<init>()",
    "void AgreementCreatedResponse.<init>(String)",
    "String AgreementCreatedResponse.getAgreementId()",
    "String AgreementCreatedResponse.toString()"
  })
  public void testGettersAndSetters_when42_thenReturnAgreementIdIs42() {
    // Arrange and Act
    AgreementCreatedResponse actualAgreementCreatedResponse = new AgreementCreatedResponse("42");
    String actualToStringResult = actualAgreementCreatedResponse.toString();

    // Assert
    assertEquals("42", actualAgreementCreatedResponse.getAgreementId());
    assertEquals("AgreementCreatedResponse{agreementId='42}", actualToStringResult);
  }
}
