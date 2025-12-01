package uk.gov.pay.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class JsonStringBuilderDiffblueTest {
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JsonStringBuilder.<init>()",
    "JsonStringBuilder JsonStringBuilder.addRoot(String)",
    "JsonStringBuilder JsonStringBuilder.noPrettyPrint()"
  })
  void testGettersAndSetters() {
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
  @DisplayName(
      "Test add(String, Object); then JsonStringBuilder (default constructor) build is '{ }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.add(String, Object)"})
  void testAdd_thenJsonStringBuilderBuildIsLeftCurlyBracketSpaceRightCurlyBracket() {
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
  @DisplayName(
      "Test add(String, Object); when 'Value'; then JsonStringBuilder (default constructor) build is '{ \"Key\" : \"Value\" }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.add(String, Object)"})
  void testAdd_whenValue_thenJsonStringBuilderBuildIsKeyValue() {
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
  @DisplayName("Test addToMap(String, String, Object) with 'mapKey', 'key', 'value'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.addToMap(String, String, Object)"})
  void testAddToMapWithMapKeyKeyValue() {
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
  @DisplayName(
      "Test addToMap(String, String, Object) with 'mapKey', 'key', 'value'; given addToMap(String, String, Object) with 'Map Key' and 'Key' and 'Value'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.addToMap(String, String, Object)"})
  void testAddToMapWithMapKeyKeyValue_givenAddToMapWithMapKeyAndKeyAndValue() {
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
  @DisplayName(
      "Test addToMap(String) with 'mapKey'; then JsonStringBuilder (default constructor) build is '{ \"Map Key\" : { } }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.addToMap(String)"})
  void testAddToMapWithMapKey_thenJsonStringBuilderBuildIsMapKey() {
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
  @DisplayName(
      "Test addToMap(String) with 'mapKey'; then JsonStringBuilder (default constructor) build is '{ \"Map Key\" : { \"Key\" : \"Value\" } }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"JsonStringBuilder JsonStringBuilder.addToMap(String)"})
  void testAddToMapWithMapKey_thenJsonStringBuilderBuildIsMapKeyKeyValue() {
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
  @DisplayName("Test addToNestedMap(String, Object, String[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JsonStringBuilder JsonStringBuilder.addToNestedMap(String, Object, String[])"
  })
  void testAddToNestedMap() {
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
  @DisplayName(
      "Test addToNestedMap(String, Object, String[]); when 'Map Keys'; then JsonStringBuilder (default constructor) build is '{ \"Map Keys\" : { \"Key\" : \"Value\" } }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JsonStringBuilder JsonStringBuilder.addToNestedMap(String, Object, String[])"
  })
  void testAddToNestedMap_whenMapKeys_thenJsonStringBuilderBuildIsMapKeysKeyValue() {
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
  @DisplayName(
      "Test build(); given addToMap(String, String, Object) with mapKey is 'java.util.Map' and key is 'null' and 'Value'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_givenAddToMapWithMapKeyIsJavaUtilMapAndKeyIsNullAndValue() {
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
  @DisplayName("Test build(); given JsonStringBuilder (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_givenJsonStringBuilder() {
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
  @DisplayName(
      "Test build(); given JsonStringBuilder (default constructor) add '42' and 'Value'; then return '{ \"Root\" : { \"42\" : \"Value\" } }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_givenJsonStringBuilderAdd42AndValue_thenReturnRoot42Value() {
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
  @DisplayName(
      "Test build(); given JsonStringBuilder (default constructor) add 'Key' and 'Value'; then return '{ \"Key\" : \"Value\" }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_givenJsonStringBuilderAddKeyAndValue_thenReturnKeyValue() {
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
  @DisplayName(
      "Test build(); given JsonStringBuilder (default constructor) add 'null' and 'Value'; then throw IllegalStateException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_givenJsonStringBuilderAddNullAndValue_thenThrowIllegalStateException() {
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
  @DisplayName(
      "Test build(); given JsonStringBuilder (default constructor) add 'null' and 'Value'; then throw IllegalStateException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_givenJsonStringBuilderAddNullAndValue_thenThrowIllegalStateException2() {
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
  @DisplayName("Test build(); given JsonStringBuilder (default constructor) addRoot empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_givenJsonStringBuilderAddRootEmptyString() {
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
  @DisplayName(
      "Test build(); given JsonStringBuilder (default constructor) addRoot 'Root'; then return '{ \"Root\" : { } }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_givenJsonStringBuilderAddRootRoot_thenReturnRoot() {
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
  @DisplayName(
      "Test build(); then return '{ \"Root\" : { \"java.util.Map\" : { \"java.util.Map\" : 42 } } }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_thenReturnRootJavaUtilMapJavaUtilMap42() {
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
  @DisplayName(
      "Test build(); then return '{ \"Root\" : { \"java.util.Map\" : { \"java.util.Map\" : \"Value\" } } }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_thenReturnRootJavaUtilMapJavaUtilMapValue() {
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
  @DisplayName("Test build(); then return '{ \"Root\" : { \"java.util.Map\" : \"Value\" } }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String JsonStringBuilder.build()"})
  void testBuild_thenReturnRootJavaUtilMapValue() {
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
