package uk.gov.pay.api.ledger.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AgreementSearchParamsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AgreementSearchParams#AgreementSearchParams()}
   *   <li>{@link AgreementSearchParams#setDisplaySize(String)}
   *   <li>{@link AgreementSearchParams#setPageNumber(String)}
   *   <li>{@link AgreementSearchParams#setReference(String)}
   *   <li>{@link AgreementSearchParams#setStatus(String)}
   *   <li>{@link AgreementSearchParams#getDisplaySize()}
   *   <li>{@link AgreementSearchParams#getPageNumber()}
   *   <li>{@link AgreementSearchParams#getReference()}
   *   <li>{@link AgreementSearchParams#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchParams.<init>()",
    "void AgreementSearchParams.<init>(String, String, String, String)",
    "String AgreementSearchParams.getDisplaySize()",
    "String AgreementSearchParams.getPageNumber()",
    "String AgreementSearchParams.getReference()",
    "String AgreementSearchParams.getStatus()",
    "void AgreementSearchParams.setDisplaySize(String)",
    "void AgreementSearchParams.setPageNumber(String)",
    "void AgreementSearchParams.setReference(String)",
    "void AgreementSearchParams.setStatus(String)"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    AgreementSearchParams actualAgreementSearchParams = new AgreementSearchParams();
    actualAgreementSearchParams.setDisplaySize("Display Size");
    actualAgreementSearchParams.setPageNumber("42");
    actualAgreementSearchParams.setReference("Reference");
    actualAgreementSearchParams.setStatus("Status");
    String actualDisplaySize = actualAgreementSearchParams.getDisplaySize();
    String actualPageNumber = actualAgreementSearchParams.getPageNumber();
    String actualReference = actualAgreementSearchParams.getReference();

    // Assert
    assertEquals("42", actualPageNumber);
    assertEquals("Display Size", actualDisplaySize);
    assertEquals("Reference", actualReference);
    assertEquals("Status", actualAgreementSearchParams.getStatus());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Reference}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AgreementSearchParams#AgreementSearchParams(String, String, String, String)}
   *   <li>{@link AgreementSearchParams#setDisplaySize(String)}
   *   <li>{@link AgreementSearchParams#setPageNumber(String)}
   *   <li>{@link AgreementSearchParams#setReference(String)}
   *   <li>{@link AgreementSearchParams#setStatus(String)}
   *   <li>{@link AgreementSearchParams#getDisplaySize()}
   *   <li>{@link AgreementSearchParams#getPageNumber()}
   *   <li>{@link AgreementSearchParams#getReference()}
   *   <li>{@link AgreementSearchParams#getStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when 'Reference'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AgreementSearchParams.<init>()",
    "void AgreementSearchParams.<init>(String, String, String, String)",
    "String AgreementSearchParams.getDisplaySize()",
    "String AgreementSearchParams.getPageNumber()",
    "String AgreementSearchParams.getReference()",
    "String AgreementSearchParams.getStatus()",
    "void AgreementSearchParams.setDisplaySize(String)",
    "void AgreementSearchParams.setPageNumber(String)",
    "void AgreementSearchParams.setReference(String)",
    "void AgreementSearchParams.setStatus(String)"
  })
  void testGettersAndSetters_whenReference() {
    // Arrange and Act
    AgreementSearchParams actualAgreementSearchParams =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");
    actualAgreementSearchParams.setDisplaySize("Display Size");
    actualAgreementSearchParams.setPageNumber("42");
    actualAgreementSearchParams.setReference("Reference");
    actualAgreementSearchParams.setStatus("Status");
    String actualDisplaySize = actualAgreementSearchParams.getDisplaySize();
    String actualPageNumber = actualAgreementSearchParams.getPageNumber();
    String actualReference = actualAgreementSearchParams.getReference();

    // Assert
    assertEquals("42", actualPageNumber);
    assertEquals("Display Size", actualDisplaySize);
    assertEquals("Reference", actualReference);
    assertEquals("Status", actualAgreementSearchParams.getStatus());
  }

  /**
   * Test {@link AgreementSearchParams#getQueryMap()}.
   *
   * <ul>
   *   <li>Given {@link AgreementSearchParams#AgreementSearchParams()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link AgreementSearchParams#getQueryMap()}
   */
  @Test
  @DisplayName("Test getQueryMap(); given AgreementSearchParams(); then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AgreementSearchParams.getQueryMap()"})
  void testGetQueryMap_givenAgreementSearchParams_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new AgreementSearchParams().getQueryMap().isEmpty());
  }

  /**
   * Test {@link AgreementSearchParams#getQueryMap()}.
   *
   * <ul>
   *   <li>Then return size is four.
   * </ul>
   *
   * <p>Method under test: {@link AgreementSearchParams#getQueryMap()}
   */
  @Test
  @DisplayName("Test getQueryMap(); then return size is four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AgreementSearchParams.getQueryMap()"})
  void testGetQueryMap_thenReturnSizeIsFour() {
    // Arrange
    AgreementSearchParams agreementSearchParams =
        new AgreementSearchParams("Reference", "Status", "42", "Display Size");

    // Act
    Map<String, String> actualQueryMap = agreementSearchParams.getQueryMap();

    // Assert
    assertEquals(4, actualQueryMap.size());
    assertEquals("42", actualQueryMap.get("page"));
    assertEquals("Display Size", actualQueryMap.get("display_size"));
    assertEquals("Reference", actualQueryMap.get("reference"));
    assertEquals("Status", actualQueryMap.get("status"));
  }
}
