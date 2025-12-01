package uk.gov.pay.api.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.pay.api.exception.PaymentValidationException;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;

class StringDeserializerDiffblueTest {
  /**
   * Test new {@link StringDeserializer} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link StringDeserializer}
   */
  @Test
  @DisplayName("Test new StringDeserializer (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringDeserializer.<init>()"})
  void testNewStringDeserializer() {
    // Arrange and Act
    StringDeserializer actualStringDeserializer = new StringDeserializer();

    // Assert
    assertNull(actualStringDeserializer.getValueType());
    Class<String> expectedValueClass = String.class;
    assertEquals(expectedValueClass, actualStringDeserializer.getValueClass());
  }

  /**
   * Test {@link StringDeserializer#deserialize(JsonParser, DeserializationContext)} with {@code p},
   * {@code ctxt}.
   *
   * <p>Method under test: {@link StringDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @DisplayName("Test deserialize(JsonParser, DeserializationContext) with 'p', 'ctxt'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringDeserializer.deserialize(JsonParser, DeserializationContext)"})
  void testDeserializeWithPCtxt() throws IOException {
    // Arrange
    StringDeserializer stringDeserializer = new StringDeserializer();

    JsonParserSequence d = mock(JsonParserSequence.class);
    when(d.getText())
        .thenThrow(
            new PaymentValidationException(
                RequestError.aHeaderRequestError(
                    "Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters")));
    when(d.hasToken(Mockito.<JsonToken>any())).thenReturn(true);
    JsonParserDelegate p = new JsonParserDelegate(d);

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            stringDeserializer.deserialize(
                p, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(d).getText();
    verify(d).hasToken(JsonToken.VALUE_STRING);
  }

  /**
   * Test {@link StringDeserializer#deserialize(JsonParser, DeserializationContext)} with {@code p},
   * {@code ctxt}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then calls {@link JsonParserSequence#getCurrentName()}.
   * </ul>
   *
   * <p>Method under test: {@link StringDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @DisplayName(
      "Test deserialize(JsonParser, DeserializationContext) with 'p', 'ctxt'; given 'false'; then calls getCurrentName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringDeserializer.deserialize(JsonParser, DeserializationContext)"})
  void testDeserializeWithPCtxt_givenFalse_thenCallsGetCurrentName() throws IOException {
    // Arrange
    StringDeserializer stringDeserializer = new StringDeserializer();

    JsonParserSequence d = mock(JsonParserSequence.class);
    when(d.hasToken(Mockito.<JsonToken>any())).thenReturn(false);
    when(d.getCurrentName()).thenReturn("Current Name");
    JsonParserDelegate p = new JsonParserDelegate(d);

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            stringDeserializer.deserialize(
                p, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(d).getCurrentName();
    verify(d).hasToken(JsonToken.VALUE_STRING);
  }

  /**
   * Test {@link StringDeserializer#deserialize(JsonParser, DeserializationContext)} with {@code p},
   * {@code ctxt}.
   *
   * <ul>
   *   <li>Given {@code Text}.
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link StringDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @DisplayName(
      "Test deserialize(JsonParser, DeserializationContext) with 'p', 'ctxt'; given 'Text'; then return 'Text'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringDeserializer.deserialize(JsonParser, DeserializationContext)"})
  void testDeserializeWithPCtxt_givenText_thenReturnText() throws IOException {
    // Arrange
    StringDeserializer stringDeserializer = new StringDeserializer();

    JsonParserSequence d = mock(JsonParserSequence.class);
    when(d.getText()).thenReturn("Text");
    when(d.hasToken(Mockito.<JsonToken>any())).thenReturn(true);
    JsonParserDelegate p = new JsonParserDelegate(d);

    // Act
    String actualDeserializeResult =
        stringDeserializer.deserialize(
            p, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig())));

    // Assert
    verify(d).getText();
    verify(d).hasToken(JsonToken.VALUE_STRING);
    assertEquals("Text", actualDeserializeResult);
  }
}
