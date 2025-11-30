package uk.gov.pay.api.resources.error;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.resources.error.ApiErrorResponse.Code;

public class ApiErrorResponseDiffblueTest {
  /**
   * Test {@link ApiErrorResponse#anApiErrorResponse(Code, Object[])}.
   *
   * <p>Method under test: {@link ApiErrorResponse#anApiErrorResponse(Code, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ApiErrorResponse ApiErrorResponse.anApiErrorResponse(Code, Object[])"})
  public void testAnApiErrorResponse() {
    // Arrange and Act
    ApiErrorResponse actualAnApiErrorResponseResult =
        ApiErrorResponse.anApiErrorResponse(Code.TOO_MANY_REQUESTS_ERROR, "Parameters");

    // Assert
    assertEquals("P0900", actualAnApiErrorResponseResult.getCode());
    assertEquals("Too many requests", actualAnApiErrorResponseResult.getDescription());
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
    Code valueOfResult = Code.valueOf("TOO_MANY_REQUESTS_ERROR");

    // Act
    String actualFormat = valueOfResult.getFormat();

    // Assert
    assertEquals("P0900", valueOfResult.value());
    assertEquals("Too many requests", actualFormat);
  }

  /**
   * Test {@link ApiErrorResponse#getCode()}.
   *
   * <p>Method under test: {@link ApiErrorResponse#getCode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ApiErrorResponse.getCode()"})
  public void testGetCode() {
    // Arrange, Act and Assert
    assertEquals(
        "P0900",
        ApiErrorResponse.anApiErrorResponse(Code.TOO_MANY_REQUESTS_ERROR, "Parameters").getCode());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ApiErrorResponse#toString()}
   *   <li>{@link ApiErrorResponse#getDescription()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String ApiErrorResponse.getDescription()",
    "String ApiErrorResponse.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ApiErrorResponse anApiErrorResponseResult =
        ApiErrorResponse.anApiErrorResponse(Code.TOO_MANY_REQUESTS_ERROR, "Parameters");

    // Act
    String actualToStringResult = anApiErrorResponseResult.toString();

    // Assert
    assertEquals(
        "ApiErrorResponse{code=P0900, name=TOO_MANY_REQUESTS_ERROR, description='Too many requests'}",
        actualToStringResult);
    assertEquals("Too many requests", anApiErrorResponseResult.getDescription());
  }
}
