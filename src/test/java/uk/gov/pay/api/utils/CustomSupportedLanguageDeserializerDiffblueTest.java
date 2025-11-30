package uk.gov.pay.api.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CustomSupportedLanguageDeserializerDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CustomSupportedLanguageDeserializer.<init>()",
    "void CustomSupportedLanguageDeserializer.<init>(Class)"
  })
  public void testNewCustomSupportedLanguageDeserializer_thenReturnValueClassIsNull() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CustomSupportedLanguageDeserializer.<init>()",
    "void CustomSupportedLanguageDeserializer.<init>(Class)"
  })
  public void testNewCustomSupportedLanguageDeserializer_thenReturnValueClassIsObject() {
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
