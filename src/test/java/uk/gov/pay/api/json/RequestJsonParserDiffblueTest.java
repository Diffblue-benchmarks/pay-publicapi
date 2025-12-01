package uk.gov.pay.api.json;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.BadRequestException;

class RequestJsonParserDiffblueTest {
  /**
   * Test {@link RequestJsonParser#parseRefundRequest(JsonNode)}.
   *
   * <ul>
   *   <li>When valueOf ten.
   *   <li>Then throw {@link BadRequestException}.
   * </ul>
   *
   * <p>Method under test: {@link RequestJsonParser#parseRefundRequest(JsonNode)}
   */
  @Test
  @DisplayName(
      "Test parseRefundRequest(JsonNode); when valueOf ten; then throw BadRequestException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "uk.gov.pay.api.model.CreatePaymentRefundRequest RequestJsonParser.parseRefundRequest(JsonNode)"
  })
  void testParseRefundRequest_whenValueOfTen_thenThrowBadRequestException() {
    // Arrange, Act and Assert
    assertThrows(
        BadRequestException.class,
        () -> RequestJsonParser.parseRefundRequest(DoubleNode.valueOf(10.0d)));
  }

  /**
   * Test {@link RequestJsonParser#parsePaymentRequest(JsonNode)}.
   *
   * <ul>
   *   <li>When valueOf ten.
   *   <li>Then throw {@link BadRequestException}.
   * </ul>
   *
   * <p>Method under test: {@link RequestJsonParser#parsePaymentRequest(JsonNode)}
   */
  @Test
  @DisplayName(
      "Test parsePaymentRequest(JsonNode); when valueOf ten; then throw BadRequestException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "uk.gov.pay.api.model.CreateCardPaymentRequest RequestJsonParser.parsePaymentRequest(JsonNode)"
  })
  void testParsePaymentRequest_whenValueOfTen_thenThrowBadRequestException() {
    // Arrange, Act and Assert
    assertThrows(
        BadRequestException.class,
        () -> RequestJsonParser.parsePaymentRequest(DoubleNode.valueOf(10.0d)));
  }

  /**
   * Test {@link RequestJsonParser#parseAgreementRequest(JsonNode)}.
   *
   * <p>Method under test: {@link RequestJsonParser#parseAgreementRequest(JsonNode)}
   */
  @Test
  @DisplayName("Test parseAgreementRequest(JsonNode)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "uk.gov.pay.api.agreement.model.CreateAgreementRequest RequestJsonParser.parseAgreementRequest(JsonNode)"
  })
  void testParseAgreementRequest() {
    // Arrange, Act and Assert
    assertThrows(
        BadRequestException.class,
        () -> RequestJsonParser.parseAgreementRequest(DoubleNode.valueOf(10.0d)));
  }
}
