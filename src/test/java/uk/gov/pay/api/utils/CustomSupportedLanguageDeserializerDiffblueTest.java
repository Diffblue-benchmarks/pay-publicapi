package uk.gov.pay.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CustomSupportedLanguageDeserializerDiffblueTest {
  /**
   * Test {@link CustomSupportedLanguageDeserializer#CustomSupportedLanguageDeserializer()}.
   *
   * <ul>
   *   <li>Then return ValueClass is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CustomSupportedLanguageDeserializer#CustomSupportedLanguageDeserializer()}
   */
  @Test
  @DisplayName("Test new CustomSupportedLanguageDeserializer(); then return ValueClass is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CustomSupportedLanguageDeserializer.<init>()",
    "void CustomSupportedLanguageDeserializer.<init>(Class)"
  })
  void testNewCustomSupportedLanguageDeserializer_thenReturnValueClassIsNull() {
    // Arrange and Act
    CustomSupportedLanguageDeserializer actualCustomSupportedLanguageDeserializer =
        new CustomSupportedLanguageDeserializer();

    // Assert
    assertNull(actualCustomSupportedLanguageDeserializer.getValueType());
    assertNull(actualCustomSupportedLanguageDeserializer.getValueClass());
  }

  /**
   * Test {@link CustomSupportedLanguageDeserializer#CustomSupportedLanguageDeserializer(Class)}.
   *
   * <ul>
   *   <li>Then return ValueClass is {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CustomSupportedLanguageDeserializer#CustomSupportedLanguageDeserializer(Class)}
   */
  @Test
  @DisplayName(
      "Test new CustomSupportedLanguageDeserializer(Class); then return ValueClass is Object")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CustomSupportedLanguageDeserializer.<init>()",
    "void CustomSupportedLanguageDeserializer.<init>(Class)"
  })
  void testNewCustomSupportedLanguageDeserializer_thenReturnValueClassIsObject() {
    // Arrange
    Class<Object> vc = Object.class;

    // Act
    CustomSupportedLanguageDeserializer actualCustomSupportedLanguageDeserializer =
        new CustomSupportedLanguageDeserializer(vc);

    // Assert
    assertNull(actualCustomSupportedLanguageDeserializer.getValueType());
    Class<Object> expectedValueClass = Object.class;
    assertEquals(expectedValueClass, actualCustomSupportedLanguageDeserializer.getValueClass());
  }
}
