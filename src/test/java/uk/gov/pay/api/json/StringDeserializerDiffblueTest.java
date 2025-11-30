package uk.gov.pay.api.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;
import java.io.IOException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import uk.gov.pay.api.exception.PaymentValidationException;
import uk.gov.pay.api.model.RequestError;
import uk.gov.pay.api.model.RequestError.Code;

public class StringDeserializerDiffblueTest {
  /**
   * Test new {@link StringDeserializer} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link StringDeserializer}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringDeserializer.<init>()"})
  public void testNewStringDeserializer() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringDeserializer.deserialize(JsonParser, DeserializationContext)"})
  public void testDeserializeWithPCtxt() throws IOException {
    // Arrange
    StringDeserializer stringDeserializer = new StringDeserializer();

    JsonParserSequence p = mock(JsonParserSequence.class);
    when(p.getText())
        .thenThrow(
            new PaymentValidationException(
                RequestError.aHeaderRequestError(
                    "Header", Code.CREATE_PAYMENT_ACCOUNT_ERROR, "Parameters")));
    when(p.hasToken(Mockito.<JsonToken>any())).thenReturn(true);

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            stringDeserializer.deserialize(
                p, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(p).getText();
    verify(p).hasToken(JsonToken.VALUE_STRING);
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringDeserializer.deserialize(JsonParser, DeserializationContext)"})
  public void testDeserializeWithPCtxt_givenFalse_thenCallsGetCurrentName() throws IOException {
    // Arrange
    StringDeserializer stringDeserializer = new StringDeserializer();

    JsonParserSequence p = mock(JsonParserSequence.class);
    when(p.hasToken(Mockito.<JsonToken>any())).thenReturn(false);
    when(p.getCurrentName()).thenReturn("Current Name");

    // Act and Assert
    assertThrows(
        PaymentValidationException.class,
        () ->
            stringDeserializer.deserialize(
                p, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(p).getCurrentName();
    verify(p).hasToken(JsonToken.VALUE_STRING);
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringDeserializer.deserialize(JsonParser, DeserializationContext)"})
  public void testDeserializeWithPCtxt_givenText_thenReturnText() throws IOException {
    // Arrange
    StringDeserializer stringDeserializer = new StringDeserializer();

    JsonParserSequence p = mock(JsonParserSequence.class);
    when(p.getText()).thenReturn("Text");
    when(p.hasToken(Mockito.<JsonToken>any())).thenReturn(true);

    // Act
    String actualDeserializeResult =
        stringDeserializer.deserialize(
            p, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig())));

    // Assert
    verify(p).getText();
    verify(p).hasToken(JsonToken.VALUE_STRING);
    assertEquals("Text", actualDeserializeResult);
  }
}
