package uk.gov.pay.api.app.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StringToListConverterDiffblueTest {
  /**
   * Test {@link StringToListConverter#convert(String)} with {@code value}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link StringToListConverter#convert(String)}
   */
  @Test
  @DisplayName("Test convert(String) with 'value'; when '42'; then return size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  void testConvertWithValue_when42_thenReturnSizeIsOne() {
    // Arrange and Act
    List<String> actualConvertResult = new StringToListConverter().convert("42");

    // Assert
    assertEquals(1, actualConvertResult.size());
    assertEquals("42", actualConvertResult.get(0));
  }

  /**
   * Test {@link StringToListConverter#convert(String)} with {@code value}.
   *
   * <ul>
   *   <li>When {@code ,42}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link StringToListConverter#convert(String)}
   */
  @Test
  @DisplayName("Test convert(String) with 'value'; when ',42'; then return size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  void testConvertWithValue_when42_thenReturnSizeIsOne2() {
    // Arrange and Act
    List<String> actualConvertResult = new StringToListConverter().convert(",42");

    // Assert
    assertEquals(1, actualConvertResult.size());
    assertEquals("42", actualConvertResult.get(0));
  }

  /**
   * Test {@link StringToListConverter#convert(String)} with {@code value}.
   *
   * <ul>
   *   <li>When {@code ,}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link StringToListConverter#convert(String)}
   */
  @Test
  @DisplayName("Test convert(String) with 'value'; when ','; then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  void testConvertWithValue_whenComma_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new StringToListConverter().convert(",").isEmpty());
  }

  /**
   * Test {@link StringToListConverter#convert(String)} with {@code value}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link StringToListConverter#convert(String)}
   */
  @Test
  @DisplayName("Test convert(String) with 'value'; when empty string; then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  void testConvertWithValue_whenEmptyString_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new StringToListConverter().convert("").isEmpty());
  }

  /**
   * Test {@link StringToListConverter#convert(String)} with {@code value}.
   *
   * <ul>
   *   <li>When {@code foo,bar}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link StringToListConverter#convert(String)}
   */
  @Test
  @DisplayName("Test convert(String) with 'value'; when 'foo,bar'; then return size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  void testConvertWithValue_whenFooBar_thenReturnSizeIsTwo() {
    // Arrange and Act
    List<String> actualConvertResult = new StringToListConverter().convert("foo,bar");

    // Assert
    assertEquals(2, actualConvertResult.size());
    assertEquals("bar", actualConvertResult.get(1));
    assertEquals("foo", actualConvertResult.get(0));
  }

  /**
   * Test {@link StringToListConverter#convert(String)} with {@code value}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link StringToListConverter#convert(String)}
   */
  @Test
  @DisplayName("Test convert(String) with 'value'; when 'null'; then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  void testConvertWithValue_whenNull_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new StringToListConverter().convert(null).isEmpty());
  }

  /**
   * Test {@link StringToListConverter#convert(String)} with {@code value}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link StringToListConverter#convert(String)}
   */
  @Test
  @DisplayName("Test convert(String) with 'value'; when space; then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  void testConvertWithValue_whenSpace_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new StringToListConverter().convert(" ").isEmpty());
  }
}
