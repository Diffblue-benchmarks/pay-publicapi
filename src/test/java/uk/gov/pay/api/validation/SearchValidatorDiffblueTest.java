package uk.gov.pay.api.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SearchValidatorDiffblueTest {
  /**
   * Test {@link SearchValidator#validatePageIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validatePageIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validatePageIfNotNull(String, List)"})
  public void testValidatePageIfNotNull_givenEmptyString_thenArrayListSizeIsTwo() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();
    validationErrors.add("");
    validationErrors.add("9");

    // Act
    SearchValidator.validatePageIfNotNull("42", validationErrors);

    // Assert that nothing has changed
    assertEquals(2, validationErrors.size());
    assertEquals("", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validatePageIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When {@code 0}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validatePageIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validatePageIfNotNull(String, List)"})
  public void testValidatePageIfNotNull_when0_thenArrayListSizeIsOne() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validatePageIfNotNull("0", validationErrors);

    // Assert
    assertEquals(1, validationErrors.size());
    assertEquals("page", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validatePageIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validatePageIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validatePageIfNotNull(String, List)"})
  public void testValidatePageIfNotNull_when42_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validatePageIfNotNull("42", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validatePageIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validatePageIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validatePageIfNotNull(String, List)"})
  public void testValidatePageIfNotNull_whenEmptyString_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validatePageIfNotNull("", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validatePageIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When {@code not blank}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validatePageIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validatePageIfNotNull(String, List)"})
  public void testValidatePageIfNotNull_whenNotBlank_thenArrayListSizeIsOne() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validatePageIfNotNull("not blank", validationErrors);

    // Assert
    assertEquals(1, validationErrors.size());
    assertEquals("page", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validatePageIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validatePageIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validatePageIfNotNull(String, List)"})
  public void testValidatePageIfNotNull_whenNull_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validatePageIfNotNull(null, validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>Given {@code 9}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 9}.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateDisplaySizeIfNotNull(String, List)"})
  public void testValidateDisplaySizeIfNotNull_given9_whenArrayListAdd9_thenArrayListSizeIsTwo() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();
    validationErrors.add("9");

    // Act
    SearchValidator.validateDisplaySizeIfNotNull("Display Size", validationErrors);

    // Assert
    assertEquals(2, validationErrors.size());
    assertEquals("9", validationErrors.get(0));
    assertEquals("display_size", validationErrors.get(1));
  }

  /**
   * Test {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When {@code 0}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateDisplaySizeIfNotNull(String, List)"})
  public void testValidateDisplaySizeIfNotNull_when0_thenArrayListSizeIsOne() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateDisplaySizeIfNotNull("0", validationErrors);

    // Assert
    assertEquals(1, validationErrors.size());
    assertEquals("display_size", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When {@code 1}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateDisplaySizeIfNotNull(String, List)"})
  public void testValidateDisplaySizeIfNotNull_when1_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateDisplaySizeIfNotNull("1", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When {@code Display Size}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateDisplaySizeIfNotNull(String, List)"})
  public void testValidateDisplaySizeIfNotNull_whenDisplaySize_thenArrayListSizeIsOne() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateDisplaySizeIfNotNull("Display Size", validationErrors);

    // Assert
    assertEquals(1, validationErrors.size());
    assertEquals("display_size", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateDisplaySizeIfNotNull(String, List)"})
  public void testValidateDisplaySizeIfNotNull_whenEmptyString_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateDisplaySizeIfNotNull("", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateDisplaySizeIfNotNull(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateDisplaySizeIfNotNull(String, List)"})
  public void testValidateDisplaySizeIfNotNull_whenNull_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateDisplaySizeIfNotNull(null, validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateToDate(String, List)}.
   *
   * <ul>
   *   <li>Given {@code to_date}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code to_date}.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateToDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateToDate(String, List)"})
  public void testValidateToDate_givenToDate_whenArrayListAddToDate_thenArrayListSizeIsTwo() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();
    validationErrors.add("to_date");

    // Act
    SearchValidator.validateToDate("2020-03-01", validationErrors);

    // Assert
    assertEquals(2, validationErrors.size());
    assertEquals("to_date", validationErrors.get(0));
    assertEquals("to_date", validationErrors.get(1));
  }

  /**
   * Test {@link SearchValidator#validateToDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code 2020-03-01}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateToDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateToDate(String, List)"})
  public void testValidateToDate_when20200301_thenArrayListSizeIsOne() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateToDate("2020-03-01", validationErrors);

    // Assert
    assertEquals(1, validationErrors.size());
    assertEquals("to_date", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validateToDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateToDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateToDate(String, List)"})
  public void testValidateToDate_whenNull_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateToDate(null, validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateToDate(String, List)}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateToDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateToDate(String, List)"})
  public void testValidateToDate_whenSpace_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateToDate(" ", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateFromDate(String, List)}.
   *
   * <ul>
   *   <li>Given {@code from_date}.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateFromDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateFromDate(String, List)"})
  public void testValidateFromDate_givenFromDate_thenArrayListSizeIsTwo() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();
    validationErrors.add("from_date");

    // Act
    SearchValidator.validateFromDate("2020-03-01", validationErrors);

    // Assert
    assertEquals(2, validationErrors.size());
    assertEquals("from_date", validationErrors.get(0));
    assertEquals("from_date", validationErrors.get(1));
  }

  /**
   * Test {@link SearchValidator#validateFromDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code 2020-03-01}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateFromDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateFromDate(String, List)"})
  public void testValidateFromDate_when20200301_thenArrayListSizeIsOne() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateFromDate("2020-03-01", validationErrors);

    // Assert
    assertEquals(1, validationErrors.size());
    assertEquals("from_date", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validateFromDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateFromDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateFromDate(String, List)"})
  public void testValidateFromDate_whenNull_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateFromDate(null, validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateFromDate(String, List)}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateFromDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateFromDate(String, List)"})
  public void testValidateFromDate_whenSpace_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateFromDate(" ", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateFromSettledDate(String, List)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateFromSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateFromSettledDate(String, List)"})
  public void testValidateFromSettledDate_givenEmptyString_thenArrayListSizeIsTwo() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();
    validationErrors.add("");
    validationErrors.add("foo");

    // Act
    SearchValidator.validateFromSettledDate("2020-03-01", validationErrors);

    // Assert that nothing has changed
    assertEquals(2, validationErrors.size());
    assertEquals("", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validateFromSettledDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code 2020-03-01}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateFromSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateFromSettledDate(String, List)"})
  public void testValidateFromSettledDate_when20200301_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateFromSettledDate("2020-03-01", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateFromSettledDate(String, List)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateFromSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateFromSettledDate(String, List)"})
  public void testValidateFromSettledDate_whenEmptyString_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateFromSettledDate("", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateFromSettledDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code not blank}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateFromSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateFromSettledDate(String, List)"})
  public void testValidateFromSettledDate_whenNotBlank_thenArrayListSizeIsOne() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateFromSettledDate("not blank", validationErrors);

    // Assert
    assertEquals(1, validationErrors.size());
    assertEquals("from_settled_date", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validateFromSettledDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateFromSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateFromSettledDate(String, List)"})
  public void testValidateFromSettledDate_whenNull_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateFromSettledDate(null, validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateToSettledDate(String, List)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateToSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateToSettledDate(String, List)"})
  public void testValidateToSettledDate_givenEmptyString_thenArrayListSizeIsTwo() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();
    validationErrors.add("");
    validationErrors.add("foo");

    // Act
    SearchValidator.validateToSettledDate("2020-03-01", validationErrors);

    // Assert that nothing has changed
    assertEquals(2, validationErrors.size());
    assertEquals("", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validateToSettledDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code 2020-03-01}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateToSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateToSettledDate(String, List)"})
  public void testValidateToSettledDate_when20200301_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateToSettledDate("2020-03-01", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateToSettledDate(String, List)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateToSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateToSettledDate(String, List)"})
  public void testValidateToSettledDate_whenEmptyString_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateToSettledDate("", validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }

  /**
   * Test {@link SearchValidator#validateToSettledDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code not blank}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateToSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateToSettledDate(String, List)"})
  public void testValidateToSettledDate_whenNotBlank_thenArrayListSizeIsOne() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateToSettledDate("not blank", validationErrors);

    // Assert
    assertEquals(1, validationErrors.size());
    assertEquals("to_settled_date", validationErrors.get(0));
  }

  /**
   * Test {@link SearchValidator#validateToSettledDate(String, List)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link SearchValidator#validateToSettledDate(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SearchValidator.validateToSettledDate(String, List)"})
  public void testValidateToSettledDate_whenNull_thenArrayListEmpty() {
    // Arrange
    ArrayList<String> validationErrors = new ArrayList<>();

    // Act
    SearchValidator.validateToSettledDate(null, validationErrors);

    // Assert that nothing has changed
    assertTrue(validationErrors.isEmpty());
  }
}
