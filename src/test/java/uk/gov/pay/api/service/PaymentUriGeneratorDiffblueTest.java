package uk.gov.pay.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PaymentUriGeneratorDiffblueTest {
  /**
   * Test {@link PaymentUriGenerator#getPaymentURI(String, String)}.
   *
   * <p>Method under test: {@link PaymentUriGenerator#getPaymentURI(String, String)}
   */
  @Test
  @DisplayName("Test getPaymentURI(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.net.URI PaymentUriGenerator.getPaymentURI(String, String)"})
  void testGetPaymentURI() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example/v1/payments/42",
        new PaymentUriGenerator().getPaymentURI("https://example.org/example", "42").toString());
  }

  /**
   * Test {@link PaymentUriGenerator#getPaymentEventsURI(String, String)}.
   *
   * <p>Method under test: {@link PaymentUriGenerator#getPaymentEventsURI(String, String)}
   */
  @Test
  @DisplayName("Test getPaymentEventsURI(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.net.URI PaymentUriGenerator.getPaymentEventsURI(String, String)"})
  void testGetPaymentEventsURI() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example/v1/payments/42/events",
        new PaymentUriGenerator()
            .getPaymentEventsURI("https://example.org/example", "42")
            .toString());
  }

  /**
   * Test {@link PaymentUriGenerator#getPaymentCancelURI(String, String)}.
   *
   * <p>Method under test: {@link PaymentUriGenerator#getPaymentCancelURI(String, String)}
   */
  @Test
  @DisplayName("Test getPaymentCancelURI(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.net.URI PaymentUriGenerator.getPaymentCancelURI(String, String)"})
  void testGetPaymentCancelURI() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example/v1/payments/42/cancel",
        new PaymentUriGenerator()
            .getPaymentCancelURI("https://example.org/example", "42")
            .toString());
  }

  /**
   * Test {@link PaymentUriGenerator#getPaymentRefundsURI(String, String)}.
   *
   * <p>Method under test: {@link PaymentUriGenerator#getPaymentRefundsURI(String, String)}
   */
  @Test
  @DisplayName("Test getPaymentRefundsURI(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.net.URI PaymentUriGenerator.getPaymentRefundsURI(String, String)"})
  void testGetPaymentRefundsURI() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example/v1/payments/42/refunds",
        new PaymentUriGenerator()
            .getPaymentRefundsURI("https://example.org/example", "42")
            .toString());
  }

  /**
   * Test {@link PaymentUriGenerator#getPaymentCaptureURI(String, String)}.
   *
   * <p>Method under test: {@link PaymentUriGenerator#getPaymentCaptureURI(String, String)}
   */
  @Test
  @DisplayName("Test getPaymentCaptureURI(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.net.URI PaymentUriGenerator.getPaymentCaptureURI(String, String)"})
  void testGetPaymentCaptureURI() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example/v1/payments/42/capture",
        new PaymentUriGenerator()
            .getPaymentCaptureURI("https://example.org/example", "42")
            .toString());
  }
}
