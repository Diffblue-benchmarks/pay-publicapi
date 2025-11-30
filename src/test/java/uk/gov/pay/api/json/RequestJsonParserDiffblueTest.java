package uk.gov.pay.api.json;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.exception.BadRequestException;

public class RequestJsonParserDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "uk.gov.pay.api.model.CreatePaymentRefundRequest RequestJsonParser.parseRefundRequest(JsonNode)"
  })
  public void testParseRefundRequest_whenValueOfTen_thenThrowBadRequestException() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "uk.gov.pay.api.model.CreateCardPaymentRequest RequestJsonParser.parsePaymentRequest(JsonNode)"
  })
  public void testParsePaymentRequest_whenValueOfTen_thenThrowBadRequestException() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "uk.gov.pay.api.agreement.model.CreateAgreementRequest RequestJsonParser.parseAgreementRequest(JsonNode)"
  })
  public void testParseAgreementRequest() {
    // Arrange, Act and Assert
    assertThrows(
        BadRequestException.class,
        () -> RequestJsonParser.parseAgreementRequest(DoubleNode.valueOf(10.0d)));
  }
}
