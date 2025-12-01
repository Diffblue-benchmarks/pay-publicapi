package uk.gov.pay.api.agreement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.CreateAgreementRequestBuilder;

class CreateAgreementRequestDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreateAgreementRequest#CreateAgreementRequest()}
   *   <li>{@link CreateAgreementRequest#getDescription()}
   *   <li>{@link CreateAgreementRequest#getReference()}
   *   <li>{@link CreateAgreementRequest#getUserIdentifier()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreateAgreementRequest.<init>()",
    "String CreateAgreementRequest.getDescription()",
    "String CreateAgreementRequest.getReference()",
    "String CreateAgreementRequest.getUserIdentifier()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    CreateAgreementRequest actualCreateAgreementRequest = new CreateAgreementRequest();
    String actualDescription = actualCreateAgreementRequest.getDescription();
    String actualReference = actualCreateAgreementRequest.getReference();

    // Assert
    assertNull(actualDescription);
    assertNull(actualReference);
    assertNull(actualCreateAgreementRequest.getUserIdentifier());
  }

  /**
   * Test {@link CreateAgreementRequest#CreateAgreementRequest(CreateAgreementRequestBuilder)}.
   *
   * <ul>
   *   <li>When builder.
   *   <li>Then return Description is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CreateAgreementRequest#CreateAgreementRequest(CreateAgreementRequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test new CreateAgreementRequest(CreateAgreementRequestBuilder); when builder; then return Description is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateAgreementRequest.<init>(CreateAgreementRequestBuilder)"})
  void testNewCreateAgreementRequest_whenBuilder_thenReturnDescriptionIsNull() {
    // Arrange and Act
    CreateAgreementRequest actualCreateAgreementRequest =
        new CreateAgreementRequest(CreateAgreementRequestBuilder.builder());

    // Assert
    assertNull(actualCreateAgreementRequest.getDescription());
    assertNull(actualCreateAgreementRequest.getReference());
    assertNull(actualCreateAgreementRequest.getUserIdentifier());
  }

  /**
   * Test {@link CreateAgreementRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given builder userIdentifier {@code 42}.
   *   <li>Then return {@code { "user_identifier" : "42" }}.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#toConnectorPayload()}
   */
  @Test
  @DisplayName(
      "Test toConnectorPayload(); given builder userIdentifier '42'; then return '{ \"user_identifier\" : \"42\" }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateAgreementRequest.toConnectorPayload()"})
  void testToConnectorPayload_givenBuilderUserIdentifier42_thenReturnUserIdentifier42() {
    // Arrange
    CreateAgreementRequestBuilder builder = CreateAgreementRequestBuilder.builder();
    builder.userIdentifier("42");

    // Act and Assert
    assertEquals(
        "{\n  \"user_identifier\" : \"42\"\n}",
        new CreateAgreementRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateAgreementRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Given {@link CreateAgreementRequest#CreateAgreementRequest()}.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#toConnectorPayload()}
   */
  @Test
  @DisplayName("Test toConnectorPayload(); given CreateAgreementRequest()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateAgreementRequest.toConnectorPayload()"})
  void testToConnectorPayload_givenCreateAgreementRequest() {
    // Arrange, Act and Assert
    assertEquals("{ }", new CreateAgreementRequest().toConnectorPayload());
  }

  /**
   * Test {@link CreateAgreementRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Then return {@code { }}.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#toConnectorPayload()}
   */
  @Test
  @DisplayName("Test toConnectorPayload(); then return '{ }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateAgreementRequest.toConnectorPayload()"})
  void testToConnectorPayload_thenReturnLeftCurlyBracketSpaceRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals(
        "{ }",
        new CreateAgreementRequest(CreateAgreementRequestBuilder.builder()).toConnectorPayload());
  }

  /**
   * Test {@link CreateAgreementRequest#toConnectorPayload()}.
   *
   * <ul>
   *   <li>Then return {@code { "reference" : "reference" }}.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#toConnectorPayload()}
   */
  @Test
  @DisplayName("Test toConnectorPayload(); then return '{ \"reference\" : \"reference\" }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String CreateAgreementRequest.toConnectorPayload()"})
  void testToConnectorPayload_thenReturnReferenceReference() {
    // Arrange
    CreateAgreementRequestBuilder builder = CreateAgreementRequestBuilder.builder();
    builder.reference("reference");

    // Act and Assert
    assertEquals(
        "{\n  \"reference\" : \"reference\"\n}",
        new CreateAgreementRequest(builder).toConnectorPayload());
  }

  /**
   * Test {@link CreateAgreementRequest#equals(Object)}, and {@link
   * CreateAgreementRequest#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreateAgreementRequest#equals(Object)}
   *   <li>{@link CreateAgreementRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CreateAgreementRequest.equals(Object)",
    "int CreateAgreementRequest.hashCode()"
  })
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    CreateAgreementRequest createAgreementRequest = new CreateAgreementRequest();
    CreateAgreementRequest createAgreementRequest2 = new CreateAgreementRequest();

    // Act and Assert
    assertEquals(createAgreementRequest, createAgreementRequest2);
    assertEquals(createAgreementRequest.hashCode(), createAgreementRequest2.hashCode());
  }

  /**
   * Test {@link CreateAgreementRequest#equals(Object)}, and {@link
   * CreateAgreementRequest#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CreateAgreementRequest#equals(Object)}
   *   <li>{@link CreateAgreementRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CreateAgreementRequest.equals(Object)",
    "int CreateAgreementRequest.hashCode()"
  })
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    CreateAgreementRequest createAgreementRequest = new CreateAgreementRequest();

    // Act and Assert
    assertEquals(createAgreementRequest, createAgreementRequest);
    int expectedHashCodeResult = createAgreementRequest.hashCode();
    assertEquals(expectedHashCodeResult, createAgreementRequest.hashCode());
  }

  /**
   * Test {@link CreateAgreementRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CreateAgreementRequest.equals(Object)",
    "int CreateAgreementRequest.hashCode()"
  })
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new CreateAgreementRequest(), 1);
  }

  /**
   * Test {@link CreateAgreementRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CreateAgreementRequest.equals(Object)",
    "int CreateAgreementRequest.hashCode()"
  })
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    CreateAgreementRequestBuilder builder = CreateAgreementRequestBuilder.builder();
    builder.reference("Reference");
    CreateAgreementRequest createAgreementRequest = new CreateAgreementRequest(builder);

    // Act and Assert
    assertNotEquals(createAgreementRequest, new CreateAgreementRequest());
  }

  /**
   * Test {@link CreateAgreementRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CreateAgreementRequest.equals(Object)",
    "int CreateAgreementRequest.hashCode()"
  })
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    CreateAgreementRequestBuilder builder = CreateAgreementRequestBuilder.builder();
    builder.description("The characteristics of someone or something");
    CreateAgreementRequest createAgreementRequest = new CreateAgreementRequest(builder);

    // Act and Assert
    assertNotEquals(createAgreementRequest, new CreateAgreementRequest());
  }

  /**
   * Test {@link CreateAgreementRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CreateAgreementRequest.equals(Object)",
    "int CreateAgreementRequest.hashCode()"
  })
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    CreateAgreementRequestBuilder builder = CreateAgreementRequestBuilder.builder();
    builder.userIdentifier("42");
    CreateAgreementRequest createAgreementRequest = new CreateAgreementRequest(builder);

    // Act and Assert
    assertNotEquals(createAgreementRequest, new CreateAgreementRequest());
  }

  /**
   * Test {@link CreateAgreementRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CreateAgreementRequest.equals(Object)",
    "int CreateAgreementRequest.hashCode()"
  })
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new CreateAgreementRequest(), null);
  }

  /**
   * Test {@link CreateAgreementRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CreateAgreementRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CreateAgreementRequest.equals(Object)",
    "int CreateAgreementRequest.hashCode()"
  })
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new CreateAgreementRequest(), "Different type to CreateAgreementRequest");
  }
}
