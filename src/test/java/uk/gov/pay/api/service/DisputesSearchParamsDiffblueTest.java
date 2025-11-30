package uk.gov.pay.api.service;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.service.DisputesSearchParams.Builder;

public class DisputesSearchParamsDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>default or parameterless constructor of {@link Builder}
   *   <li>{@link Builder#withDisplaySize(String)}
   *   <li>{@link Builder#withFromDate(String)}
   *   <li>{@link Builder#withFromSettledDate(String)}
   *   <li>{@link Builder#withPage(String)}
   *   <li>{@link Builder#withStatus(String)}
   *   <li>{@link Builder#withToDate(String)}
   *   <li>{@link Builder#withToSettledDate(String)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>()",
    "DisputesSearchParams Builder.build()",
    "Builder Builder.withDisplaySize(String)",
    "Builder Builder.withFromDate(String)",
    "Builder Builder.withFromSettledDate(String)",
    "Builder Builder.withPage(String)",
    "Builder Builder.withStatus(String)",
    "Builder Builder.withToDate(String)",
    "Builder Builder.withToSettledDate(String)"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    DisputesSearchParams actualDisputesSearchParams =
        new Builder()
            .withDisplaySize("Display Size")
            .withFromDate("2020-03-01")
            .withFromSettledDate("2020-03-01")
            .withPage("Page")
            .withStatus("Status")
            .withToDate("2020-03-01")
            .withToSettledDate("2020-03-01")
            .build();

    // Assert
    Map<String, String> paramsAsMap = actualDisputesSearchParams.getParamsAsMap();
    assertEquals(7, paramsAsMap.size());
    assertEquals("2020-03-01", paramsAsMap.get(PaymentSearchParams.FROM_DATE_KEY));
    assertEquals("2020-03-01", paramsAsMap.get(PaymentSearchParams.FROM_SETTLED_DATE));
    assertEquals("2020-03-01", paramsAsMap.get(PaymentSearchParams.TO_DATE_KEY));
    assertEquals("2020-03-01", paramsAsMap.get(PaymentSearchParams.TO_SETTLED_DATE));
    assertEquals("2020-03-01", actualDisputesSearchParams.getFromDate());
    assertEquals("2020-03-01", actualDisputesSearchParams.getFromSettledDate());
    assertEquals("2020-03-01", actualDisputesSearchParams.getToDate());
    assertEquals("2020-03-01", actualDisputesSearchParams.getToSettledDate());
    assertEquals("Display Size", paramsAsMap.get(PaymentSearchParams.DISPLAY_SIZE));
    assertEquals("Display Size", actualDisputesSearchParams.getDisplaySize());
    assertEquals("Page", paramsAsMap.get(PaymentSearchParams.PAGE));
    assertEquals("Page", actualDisputesSearchParams.getPage());
    assertEquals("Status", actualDisputesSearchParams.getState());
  }

  /**
   * Test {@link DisputesSearchParams#getParamsAsMap()}.
   *
   * <p>Method under test: {@link DisputesSearchParams#getParamsAsMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DisputesSearchParams.getParamsAsMap()"})
  public void testGetParamsAsMap() {
    // Arrange and Act
    Map<String, String> actualParamsAsMap =
        new Builder()
            .withDisplaySize("Display Size")
            .withFromDate("2020-03-01")
            .withFromSettledDate("2020-03-01")
            .withPage("Page")
            .withStatus("Status")
            .withToDate("2020-03-01")
            .withToSettledDate("2020-03-01")
            .build()
            .getParamsAsMap();

    // Assert
    assertEquals(7, actualParamsAsMap.size());
    assertEquals("2020-03-01", actualParamsAsMap.get(PaymentSearchParams.FROM_DATE_KEY));
    assertEquals("2020-03-01", actualParamsAsMap.get(PaymentSearchParams.FROM_SETTLED_DATE));
    assertEquals("2020-03-01", actualParamsAsMap.get(PaymentSearchParams.TO_DATE_KEY));
    assertEquals("2020-03-01", actualParamsAsMap.get(PaymentSearchParams.TO_SETTLED_DATE));
    assertEquals("Display Size", actualParamsAsMap.get(PaymentSearchParams.DISPLAY_SIZE));
    assertEquals("Page", actualParamsAsMap.get(PaymentSearchParams.PAGE));
    assertEquals("Status", actualParamsAsMap.get("status"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DisputesSearchParams#getDisplaySize()}
   *   <li>{@link DisputesSearchParams#getFromDate()}
   *   <li>{@link DisputesSearchParams#getFromSettledDate()}
   *   <li>{@link DisputesSearchParams#getPage()}
   *   <li>{@link DisputesSearchParams#getState()}
   *   <li>{@link DisputesSearchParams#getToDate()}
   *   <li>{@link DisputesSearchParams#getToSettledDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DisputesSearchParams.getDisplaySize()",
    "String DisputesSearchParams.getFromDate()",
    "String DisputesSearchParams.getFromSettledDate()",
    "String DisputesSearchParams.getPage()",
    "String DisputesSearchParams.getState()",
    "String DisputesSearchParams.getToDate()",
    "String DisputesSearchParams.getToSettledDate()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DisputesSearchParams disputesSearchParams =
        new Builder()
            .withDisplaySize("Display Size")
            .withFromDate("2020-03-01")
            .withFromSettledDate("2020-03-01")
            .withPage("Page")
            .withStatus("Status")
            .withToDate("2020-03-01")
            .withToSettledDate("2020-03-01")
            .build();

    // Act
    String actualDisplaySize = disputesSearchParams.getDisplaySize();
    String actualFromDate = disputesSearchParams.getFromDate();
    String actualFromSettledDate = disputesSearchParams.getFromSettledDate();
    String actualPage = disputesSearchParams.getPage();
    String actualState = disputesSearchParams.getState();
    String actualToDate = disputesSearchParams.getToDate();

    // Assert
    assertEquals("2020-03-01", actualFromDate);
    assertEquals("2020-03-01", actualFromSettledDate);
    assertEquals("2020-03-01", actualToDate);
    assertEquals("2020-03-01", disputesSearchParams.getToSettledDate());
    assertEquals("Display Size", actualDisplaySize);
    assertEquals("Page", actualPage);
    assertEquals("Status", actualState);
  }
}
