package uk.gov.pay.api.resources.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.resources.error.ApiErrorResponse.Code;

class ApiErrorResponseDiffblueTest {
  /**
   * Test {@link ApiErrorResponse#anApiErrorResponse(Code, Object[])}.
   *
   * <p>Method under test: {@link ApiErrorResponse#anApiErrorResponse(Code, Object[])}
   */
  @Test
  @DisplayName("Test anApiErrorResponse(Code, Object[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ApiErrorResponse ApiErrorResponse.anApiErrorResponse(Code, Object[])"})
  void testAnApiErrorResponse() {
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
  @DisplayName("Test Code getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Code.getFormat()", "String Code.value()"})
  void testCodeGettersAndSetters() {
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
  @DisplayName("Test getCode()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String ApiErrorResponse.getCode()"})
  void testGetCode() {
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String ApiErrorResponse.getDescription()",
    "String ApiErrorResponse.toString()"
  })
  void testGettersAndSetters() {
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
