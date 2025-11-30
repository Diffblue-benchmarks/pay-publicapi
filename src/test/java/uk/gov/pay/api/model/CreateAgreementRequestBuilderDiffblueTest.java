package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.agreement.model.CreateAgreementRequest;

public class CreateAgreementRequestBuilderDiffblueTest {
  /**
   * Test {@link CreateAgreementRequestBuilder#builder()}.
   *
   * <p>Method under test: {@link CreateAgreementRequestBuilder#builder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CreateAgreementRequestBuilder CreateAgreementRequestBuilder.builder()"})
  public void testBuilder() {
    // Arrange and Act
    CreateAgreementRequestBuilder actualBuilderResult = CreateAgreementRequestBuilder.builder();

    // Assert
    assertNull(actualBuilderResult.getDescription());
    assertNull(actualBuilderResult.getReference());
    assertNull(actualBuilderResult.getUserIdentifier());
  }

  /**
   * Test {@link CreateAgreementRequestBuilder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreateAgreementRequestBuilder#build()}
   *   <li>{@link CreateAgreementRequestBuilder#description(String)}
   *   <li>{@link CreateAgreementRequestBuilder#reference(String)}
   *   <li>{@link CreateAgreementRequestBuilder#userIdentifier(String)}
   *   <li>{@link CreateAgreementRequestBuilder#getUserIdentifier()}
   *   <li>{@link CreateAgreementRequestBuilder#getReference()}
   *   <li>{@link CreateAgreementRequestBuilder#getDescription()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreateAgreementRequestBuilder.<init>()",
    "CreateAgreementRequest CreateAgreementRequestBuilder.build()",
    "CreateAgreementRequestBuilder CreateAgreementRequestBuilder.description(String)",
    "String CreateAgreementRequestBuilder.getDescription()",
    "String CreateAgreementRequestBuilder.getReference()",
    "String CreateAgreementRequestBuilder.getUserIdentifier()",
    "CreateAgreementRequestBuilder CreateAgreementRequestBuilder.reference(String)",
    "CreateAgreementRequestBuilder CreateAgreementRequestBuilder.userIdentifier(String)"
  })
  public void testBuild() {
    // Arrange and Act
    CreateAgreementRequestBuilder actualBuilderResult = CreateAgreementRequestBuilder.builder();
    CreateAgreementRequestBuilder actualDescriptionResult =
        actualBuilderResult.description("The characteristics of someone or something");
    CreateAgreementRequestBuilder actualReferenceResult =
        actualDescriptionResult.reference("Reference");
    CreateAgreementRequestBuilder actualUserIdentifierResult =
        actualReferenceResult.userIdentifier("42");
    CreateAgreementRequest actualCreateAgreementRequest = actualUserIdentifierResult.build();

    // Assert
    assertEquals("42", actualCreateAgreementRequest.getUserIdentifier());
    assertEquals("42", actualDescriptionResult.getUserIdentifier());
    assertEquals("42", actualReferenceResult.getUserIdentifier());
    assertEquals("42", actualUserIdentifierResult.getUserIdentifier());
    assertEquals("42", actualBuilderResult.getUserIdentifier());
    assertEquals("Reference", actualCreateAgreementRequest.getReference());
    assertEquals("Reference", actualDescriptionResult.getReference());
    assertEquals("Reference", actualReferenceResult.getReference());
    assertEquals("Reference", actualUserIdentifierResult.getReference());
    assertEquals("Reference", actualBuilderResult.getReference());
    assertEquals(
        "The characteristics of someone or something",
        actualCreateAgreementRequest.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualDescriptionResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualReferenceResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualUserIdentifierResult.getDescription());
    assertEquals(
        "The characteristics of someone or something", actualBuilderResult.getDescription());
  }
}
