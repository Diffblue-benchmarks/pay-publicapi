package uk.gov.pay.api.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JsonStringBuilderDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link JsonStringBuilder}
   *   <li>{@link JsonStringBuilder#addRoot(String)}
   *   <li>{@link JsonStringBuilder#noPrettyPrint()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonStringBuilder.<init>()",
    "JsonStringBuilder JsonStringBuilder.addRoot(String)",
    "JsonStringBuilder JsonStringBuilder.noPrettyPrint()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    JsonStringBuilder actualJsonStringBuilder = new JsonStringBuilder();
    JsonStringBuilder actualAddRootResult = actualJsonStringBuilder.addRoot("Root");
    JsonStringBuilder actualNoPrettyPrintResult = actualJsonStringBuilder.noPrettyPrint();

    // Assert
    assertSame(actualJsonStringBuilder, actualAddRootResult);
    assertSame(actualJsonStringBuilder, actualNoPrettyPrintResult);
  }

  /**
   * Test {@link JsonStringBuilder#add(String, Object)}.
   *
   * <ul>
   *   <li>Then {@link JsonStringBuilder} (default constructor) build is {@code { }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#add(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.add(String, Object)"})
  public void testAdd_thenJsonStringBuilderBuildIsLeftCurlyBracketSpaceRightCurlyBracket() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();

    // Act
    JsonStringBuilder actualAddResult = jsonStringBuilder.add("Key", null);

    // Assert
    String actualString = jsonStringBuilder.build();
    assertEquals("{ }", actualString);
    assertSame(jsonStringBuilder, actualAddResult);
  }

  /**
   * Test {@link JsonStringBuilder#add(String, Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then {@link JsonStringBuilder} (default constructor) build is {@code { "Key" : "Value"
   *       }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#add(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.add(String, Object)"})
  public void testAdd_whenValue_thenJsonStringBuilderBuildIsKeyValue() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();

    // Act
    JsonStringBuilder actualAddResult = jsonStringBuilder.add("Key", "Value");

    // Assert
    String actualString = jsonStringBuilder.build();
    assertEquals("{\n  \"Key\" : \"Value\"\n}", actualString);
    assertSame(jsonStringBuilder, actualAddResult);
  }

  /**
   * Test {@link JsonStringBuilder#addToMap(String, String, Object)} with {@code mapKey}, {@code
   * key}, {@code value}.
   *
   * <p>Method under test: {@link JsonStringBuilder#addToMap(String, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.addToMap(String, String, Object)"})
  public void testAddToMapWithMapKeyKeyValue() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();

    // Act
    JsonStringBuilder actualAddToMapResult = jsonStringBuilder.addToMap("Map Key", "Key", "Value");

    // Assert
    String actualString = jsonStringBuilder.build();
    assertEquals("{\n  \"Map Key\" : {\n    \"Key\" : \"Value\"\n  }\n}", actualString);
    assertSame(jsonStringBuilder, actualAddToMapResult);
  }

  /**
   * Test {@link JsonStringBuilder#addToMap(String, String, Object)} with {@code mapKey}, {@code
   * key}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link JsonStringBuilder#addToMap(String, String, Object)} with {@code Map Key} and
   *       {@code Key} and {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#addToMap(String, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.addToMap(String, String, Object)"})
  public void testAddToMapWithMapKeyKeyValue_givenAddToMapWithMapKeyAndKeyAndValue() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.addToMap("Map Key", "Key", "Value");

    // Act
    JsonStringBuilder actualAddToMapResult = jsonStringBuilder.addToMap("Map Key", "Key", "Value");

    // Assert
    String actualString = jsonStringBuilder.build();
    assertEquals("{\n  \"Map Key\" : {\n    \"Key\" : \"Value\"\n  }\n}", actualString);
    assertSame(jsonStringBuilder, actualAddToMapResult);
  }

  /**
   * Test {@link JsonStringBuilder#addToMap(String)} with {@code mapKey}.
   *
   * <ul>
   *   <li>Then {@link JsonStringBuilder} (default constructor) build is {@code { "Map Key" : { }
   *       }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#addToMap(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.addToMap(String)"})
  public void testAddToMapWithMapKey_thenJsonStringBuilderBuildIsMapKey() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();

    // Act
    JsonStringBuilder actualAddToMapResult = jsonStringBuilder.addToMap("Map Key");

    // Assert
    String actualString = jsonStringBuilder.build();
    assertEquals("{\n  \"Map Key\" : { }\n}", actualString);
    assertSame(jsonStringBuilder, actualAddToMapResult);
  }

  /**
   * Test {@link JsonStringBuilder#addToMap(String)} with {@code mapKey}.
   *
   * <ul>
   *   <li>Then {@link JsonStringBuilder} (default constructor) build is {@code { "Map Key" : {
   *       "Key" : "Value" } }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#addToMap(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.addToMap(String)"})
  public void testAddToMapWithMapKey_thenJsonStringBuilderBuildIsMapKeyKeyValue() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.addToMap("Map Key", "Key", "Value");

    // Act
    JsonStringBuilder actualAddToMapResult = jsonStringBuilder.addToMap("Map Key");

    // Assert
    String actualString = jsonStringBuilder.build();
    assertEquals("{\n  \"Map Key\" : {\n    \"Key\" : \"Value\"\n  }\n}", actualString);
    assertSame(jsonStringBuilder, actualAddToMapResult);
  }

  /**
   * Test {@link JsonStringBuilder#addToNestedMap(String, Object, String[])}.
   *
   * <p>Method under test: {@link JsonStringBuilder#addToNestedMap(String, Object, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JsonStringBuilder JsonStringBuilder.addToNestedMap(String, Object, String[])"
  })
  public void testAddToNestedMap() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.addToNestedMap("Key", "Value", "Map Keys");

    // Act
    JsonStringBuilder actualAddToNestedMapResult =
        jsonStringBuilder.addToNestedMap("Key", "Value", "Map Keys");

    // Assert
    String actualString = jsonStringBuilder.build();
    assertEquals("{\n  \"Map Keys\" : {\n    \"Key\" : \"Value\"\n  }\n}", actualString);
    assertSame(jsonStringBuilder, actualAddToNestedMapResult);
  }

  /**
   * Test {@link JsonStringBuilder#addToNestedMap(String, Object, String[])}.
   *
   * <ul>
   *   <li>When {@code Map Keys}.
   *   <li>Then {@link JsonStringBuilder} (default constructor) build is {@code { "Map Keys" : {
   *       "Key" : "Value" } }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#addToNestedMap(String, Object, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JsonStringBuilder JsonStringBuilder.addToNestedMap(String, Object, String[])"
  })
  public void testAddToNestedMap_whenMapKeys_thenJsonStringBuilderBuildIsMapKeysKeyValue() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();

    // Act
    JsonStringBuilder actualAddToNestedMapResult =
        jsonStringBuilder.addToNestedMap("Key", "Value", "Map Keys");

    // Assert
    String actualString = jsonStringBuilder.build();
    assertEquals("{\n  \"Map Keys\" : {\n    \"Key\" : \"Value\"\n  }\n}", actualString);
    assertSame(jsonStringBuilder, actualAddToNestedMapResult);
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Given {@link JsonStringBuilder#addToMap(String, String, Object)} with mapKey is {@code
   *       java.util.Map} and key is {@code null} and {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_givenAddToMapWithMapKeyIsJavaUtilMapAndKeyIsNullAndValue() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.addToMap("java.util.Map", null, "Value");
    jsonStringBuilder.addRoot("Root");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonStringBuilder.build());
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Given {@link JsonStringBuilder} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_givenJsonStringBuilder() {
    // Arrange and Act
    String actualString = new JsonStringBuilder().build();

    // Assert
    assertEquals("{ }", actualString);
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Given {@link JsonStringBuilder} (default constructor) add {@code 42} and {@code Value}.
   *   <li>Then return {@code { "Root" : { "42" : "Value" } }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_givenJsonStringBuilderAdd42AndValue_thenReturnRoot42Value() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.add("42", "Value");
    jsonStringBuilder.addRoot("Root");

    // Act
    String actualString = jsonStringBuilder.build();

    // Assert
    assertEquals("{\n  \"Root\" : {\n    \"42\" : \"Value\"\n  }\n}", actualString);
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Given {@link JsonStringBuilder} (default constructor) add {@code Key} and {@code Value}.
   *   <li>Then return {@code { "Key" : "Value" }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_givenJsonStringBuilderAddKeyAndValue_thenReturnKeyValue() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.add("Key", "Value");

    // Act
    String actualString = jsonStringBuilder.build();

    // Assert
    assertEquals("{\n  \"Key\" : \"Value\"\n}", actualString);
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Given {@link JsonStringBuilder} (default constructor) add {@code null} and {@code Value}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_givenJsonStringBuilderAddNullAndValue_thenThrowIllegalStateException() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.add(null, "Value");
    jsonStringBuilder.addRoot("Root");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonStringBuilder.build());
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Given {@link JsonStringBuilder} (default constructor) add {@code null} and {@code Value}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_givenJsonStringBuilderAddNullAndValue_thenThrowIllegalStateException2() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.add(null, "Value");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jsonStringBuilder.build());
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Given {@link JsonStringBuilder} (default constructor) addRoot empty string.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_givenJsonStringBuilderAddRootEmptyString() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.addRoot("");

    // Act
    String actualString = jsonStringBuilder.build();

    // Assert
    assertEquals("{ }", actualString);
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Given {@link JsonStringBuilder} (default constructor) addRoot {@code Root}.
   *   <li>Then return {@code { "Root" : { } }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_givenJsonStringBuilderAddRootRoot_thenReturnRoot() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.addRoot("Root");

    // Act
    String actualString = jsonStringBuilder.build();

    // Assert
    assertEquals("{\n  \"Root\" : { }\n}", actualString);
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Then return {@code { "Root" : { "java.util.Map" : { "java.util.Map" : 42 } } }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_thenReturnRootJavaUtilMapJavaUtilMap42() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.addToMap("java.util.Map", "java.util.Map", 42);
    jsonStringBuilder.addRoot("Root");

    // Act
    String actualString = jsonStringBuilder.build();

    // Assert
    assertEquals(
        "{\n  \"Root\" : {\n    \"java.util.Map\" : {\n      \"java.util.Map\" : 42\n    }\n  }\n}",
        actualString);
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Then return {@code { "Root" : { "java.util.Map" : { "java.util.Map" : "Value" } } }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_thenReturnRootJavaUtilMapJavaUtilMapValue() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.addToMap("java.util.Map", "java.util.Map", "Value");
    jsonStringBuilder.addRoot("Root");

    // Act
    String actualString = jsonStringBuilder.build();

    // Assert
    assertEquals(
        "{\n  \"Root\" : {\n    \"java.util.Map\" : {\n      \"java.util.Map\" : \"Value\"\n    }\n  }\n}",
        actualString);
  }

  /**
   * Test {@link JsonStringBuilder#build()}.
   *
   * <ul>
   *   <li>Then return {@code { "Root" : { "java.util.Map" : "Value" } }}.
   * </ul>
   *
   * <p>Method under test: {@link JsonStringBuilder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  public void testBuild_thenReturnRootJavaUtilMapValue() {
    // Arrange
    JsonStringBuilder jsonStringBuilder = new JsonStringBuilder();
    jsonStringBuilder.add("java.util.Map", "Value");
    jsonStringBuilder.addRoot("Root");

    // Act
    String actualString = jsonStringBuilder.build();

    // Assert
    assertEquals("{\n  \"Root\" : {\n    \"java.util.Map\" : \"Value\"\n  }\n}", actualString);
  }
}
