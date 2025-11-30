package uk.gov.pay.api.app.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StringToListConverterDiffblueTest {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  public void testConvertWithValue_when42_thenReturnSizeIsOne() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  public void testConvertWithValue_when42_thenReturnSizeIsOne2() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  public void testConvertWithValue_whenComma_thenReturnEmpty() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  public void testConvertWithValue_whenEmptyString_thenReturnEmpty() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  public void testConvertWithValue_whenFooBar_thenReturnSizeIsTwo() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  public void testConvertWithValue_whenNull_thenReturnEmpty() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List StringToListConverter.convert(String)"})
  public void testConvertWithValue_whenSpace_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new StringToListConverter().convert(" ").isEmpty());
  }
}
