package uk.gov.pay.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RefundsParamsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RefundsParams#RefundsParams(String, String, String, String, String, String)}
   *   <li>{@link RefundsParams#getDisplaySize()}
   *   <li>{@link RefundsParams#getFromDate()}
   *   <li>{@link RefundsParams#getFromSettledDate()}
   *   <li>{@link RefundsParams#getPage()}
   *   <li>{@link RefundsParams#getToDate()}
   *   <li>{@link RefundsParams#getToSettledDate()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RefundsParams.<init>(String, String, String, String, String, String)",
    "String RefundsParams.getDisplaySize()",
    "String RefundsParams.getFromDate()",
    "String RefundsParams.getFromSettledDate()",
    "String RefundsParams.getPage()",
    "String RefundsParams.getToDate()",
    "String RefundsParams.getToSettledDate()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    RefundsParams actualRefundsParams =
        new RefundsParams(
            "2020-03-01", "2020-03-01", "Page", "Display Size", "2020-03-01", "2020-03-01");
    String actualDisplaySize = actualRefundsParams.getDisplaySize();
    String actualFromDate = actualRefundsParams.getFromDate();
    String actualFromSettledDate = actualRefundsParams.getFromSettledDate();
    String actualPage = actualRefundsParams.getPage();
    String actualToDate = actualRefundsParams.getToDate();

    // Assert
    assertEquals("2020-03-01", actualFromDate);
    assertEquals("2020-03-01", actualFromSettledDate);
    assertEquals("2020-03-01", actualToDate);
    assertEquals("2020-03-01", actualRefundsParams.getToSettledDate());
    assertEquals("Display Size", actualDisplaySize);
    assertEquals("Page", actualPage);
  }

  /**
   * Test {@link RefundsParams#getParamsAsMap()}.
   *
   * <p>Method under test: {@link RefundsParams#getParamsAsMap()}
   */
  @Test
  @DisplayName("Test getParamsAsMap()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RefundsParams.getParamsAsMap()"})
  void testGetParamsAsMap() {
    // Arrange
    RefundsParams refundsParams =
        new RefundsParams(
            "2020-03-01", "2020-03-01", "Page", "Display Size", "2020-03-01", "2020-03-01");

    // Act
    Map<String, String> actualParamsAsMap = refundsParams.getParamsAsMap();

    // Assert
    assertEquals(6, actualParamsAsMap.size());
    assertEquals("2020-03-01", actualParamsAsMap.get(PaymentSearchParams.FROM_DATE_KEY));
    assertEquals("2020-03-01", actualParamsAsMap.get(PaymentSearchParams.FROM_SETTLED_DATE));
    assertEquals("2020-03-01", actualParamsAsMap.get(PaymentSearchParams.TO_DATE_KEY));
    assertEquals("2020-03-01", actualParamsAsMap.get(PaymentSearchParams.TO_SETTLED_DATE));
    assertEquals("Display Size", actualParamsAsMap.get(PaymentSearchParams.DISPLAY_SIZE));
    assertEquals("Page", actualParamsAsMap.get(PaymentSearchParams.PAGE));
  }
}
