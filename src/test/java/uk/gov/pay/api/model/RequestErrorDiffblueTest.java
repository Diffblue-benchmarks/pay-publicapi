package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.model.RequestError.Code;

public class RequestErrorDiffblueTest {
  /**
   * Test {@link RequestError#aRequestError(Code, Object[])} with {@code code}, {@code parameters}.
   *
   * <ul>
   *   <li>Then return Code is {@code P0199}.
   * </ul>
   *
   * <p>Method under test: {@link RequestError#aRequestError(Code, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestError RequestError.aRequestError(Code, Object[])"})
  public void testARequestErrorWithCodeParameters_thenReturnCodeIsP0199() {
    // Arrange and Act
    RequestError actualARequestErrorResult =
        RequestError.aRequestError(Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Assert
    assertEquals("P0199", actualARequestErrorResult.getCode());
    assertEquals(
        "There is an error with this account. Contact support with your error code - https://www.payments.service"
            + ".gov.uk/support/ .",
        actualARequestErrorResult.getDescription());
    assertNull(actualARequestErrorResult.getField());
    assertNull(actualARequestErrorResult.getHeader());
  }

  /**
   * Test {@link RequestError#aRequestError(String, Code, Object[])} with {@code fieldName}, {@code
   * code}, {@code parameters}.
   *
   * <ul>
   *   <li>Then return Field is {@code Field Name}.
   * </ul>
   *
   * <p>Method under test: {@link RequestError#aRequestError(String, Code, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestError RequestError.aRequestError(String, Code, Object[])"})
  public void testARequestErrorWithFieldNameCodeParameters_thenReturnFieldIsFieldName() {
    // Arrange and Act
    RequestError actualARequestErrorResult =
        RequestError.aRequestError("Field Name", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Assert
    assertEquals("Field Name", actualARequestErrorResult.getField());
    assertEquals("P0199", actualARequestErrorResult.getCode());
    assertEquals(
        "There is an error with this account. Contact support with your error code - https://www.payments.service"
            + ".gov.uk/support/ .",
        actualARequestErrorResult.getDescription());
    assertNull(actualARequestErrorResult.getHeader());
  }

  /**
   * Test {@link RequestError#aRequestError(String, Code, Object[])} with {@code fieldName}, {@code
   * code}, {@code parameters}.
   *
   * <ul>
   *   <li>Then return Field is {@code Field Name}.
   * </ul>
   *
   * <p>Method under test: {@link RequestError#aRequestError(String, Code, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestError RequestError.aRequestError(String, Code, Object[])"})
  public void testARequestErrorWithFieldNameCodeParameters_thenReturnFieldIsFieldName2() {
    // Arrange and Act
    RequestError actualARequestErrorResult =
        RequestError.aRequestError("Field Name", Code.CREATE_PAYMENT_ACCOUNT_ERROR);

    // Assert
    assertEquals("Field Name", actualARequestErrorResult.getField());
    assertEquals("P0199", actualARequestErrorResult.getCode());
    assertEquals(
        "There is an error with this account. Contact support with your error code - https://www.payments.service"
            + ".gov.uk/support/ .",
        actualARequestErrorResult.getDescription());
    assertNull(actualARequestErrorResult.getHeader());
  }

  /**
   * Test {@link RequestError#aHeaderRequestError(String, Code, Object[])}.
   *
   * <ul>
   *   <li>When {@code CREATE_PAYMENT_ACCOUNT_ERROR}.
   *   <li>Then return {@code Header}.
   * </ul>
   *
   * <p>Method under test: {@link RequestError#aHeaderRequestError(String, Code, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestError RequestError.aHeaderRequestError(String, Code, Object[])"})
  public void testAHeaderRequestError_whenCreatePaymentAccountError_thenReturnHeader() {
    // Arrange and Act
    RequestError actualAHeaderRequestErrorResult =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Assert
    assertEquals("Header", actualAHeaderRequestErrorResult.getHeader());
    assertEquals("P0199", actualAHeaderRequestErrorResult.getCode());
    assertEquals(
        "There is an error with this account. Contact support with your error code - https://www.payments.service"
            + ".gov.uk/support/ .",
        actualAHeaderRequestErrorResult.getDescription());
    assertNull(actualAHeaderRequestErrorResult.getField());
  }

  /**
   * Test Code getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Code#getFormat()}
   *   <li>{@link Code#value()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Code.getFormat()", "String Code.value()"})
  public void testCodeGettersAndSetters() {
    // Arrange
    Code valueOfResult = Code.valueOf("CREATE_PAYMENT_ACCOUNT_ERROR");

    // Act
    String actualFormat = valueOfResult.getFormat();

    // Assert
    assertEquals("P0199", valueOfResult.value());
    assertEquals(
        "There is an error with this account. Contact support with your error code - https://www.payments.service"
            + ".gov.uk/support/ .",
        actualFormat);
  }

  /**
   * Test {@link RequestError#getCode()}.
   *
   * <p>Method under test: {@link RequestError#getCode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RequestError.getCode()"})
  public void testGetCode() {
    // Arrange, Act and Assert
    assertEquals(
        "P0199",
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters")
            .getCode());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RequestError#toString()}
   *   <li>{@link RequestError#getDescription()}
   *   <li>{@link RequestError#getField()}
   *   <li>{@link RequestError#getHeader()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String RequestError.getDescription()",
    "String RequestError.getField()",
    "String RequestError.getHeader()",
    "String RequestError.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RequestError aHeaderRequestErrorResult =
        RequestError.aHeaderRequestError("Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters");

    // Act
    String actualToStringResult = aHeaderRequestErrorResult.toString();
    String actualDescription = aHeaderRequestErrorResult.getDescription();
    String actualField = aHeaderRequestErrorResult.getField();

    // Assert
    assertEquals("Header", aHeaderRequestErrorResult.getHeader());
    assertEquals(
        "RequestError{field=null, code=P0199, name=CREATE_PAYMENT_ACCOUNT_ERROR, description='There is an error"
            + " with this account. Contact support with your error code - https://www.payments.service.gov.uk/support/"
            + " .'}",
        actualToStringResult);
    assertEquals(
        "There is an error with this account. Contact support with your error code - https://www.payments.service"
            + ".gov.uk/support/ .",
        actualDescription);
    assertNull(actualField);
  }
}
