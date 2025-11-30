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
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.core.io.ContentReference;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.StringReader;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.pay.api.exception.BadRequestException;
import uk.gov.pay.api.model.CreateCardPaymentRequest;

public class CreateCardPaymentRequestDeserializerDiffblueTest {
  /**
   * Test new {@link CreateCardPaymentRequestDeserializer} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * CreateCardPaymentRequestDeserializer}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CreateCardPaymentRequestDeserializer.<init>()"})
  public void testNewCreateCardPaymentRequestDeserializer() {
    // Arrange and Act
    CreateCardPaymentRequestDeserializer actualCreateCardPaymentRequestDeserializer =
        new CreateCardPaymentRequestDeserializer();

    // Assert
    assertNull(actualCreateCardPaymentRequestDeserializer.getValueType());
    Class<CreateCardPaymentRequest> expectedValueClass = CreateCardPaymentRequest.class;
    assertEquals(expectedValueClass, actualCreateCardPaymentRequestDeserializer.getValueClass());
  }

  /**
   * Test {@link CreateCardPaymentRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)} with {@code parser}, {@code context}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreateCardPaymentRequest CreateCardPaymentRequestDeserializer.deserialize(JsonParser, DeserializationContext)"
  })
  public void testDeserializeWithParserContext() {
    // Arrange
    CreateCardPaymentRequestDeserializer createCardPaymentRequestDeserializer =
        new CreateCardPaymentRequestDeserializer();

    CharsToNameCanonicalizer st = mock(CharsToNameCanonicalizer.class);
    when(st.hashSeed()).thenReturn(19088743);
    BufferRecycler br = new BufferRecycler();
    IOContext ctxt = new IOContext(br, ContentReference.redacted(), true);
    StringReader r = new StringReader("foo");
    JsonMapper codec = JsonMapper.builder().findAndAddModules().build();

    ReaderBasedJsonParser parser = new ReaderBasedJsonParser(ctxt, 1, r, codec, st);

    // Act and Assert
    assertThrows(
        BadRequestException.class,
        () ->
            createCardPaymentRequestDeserializer.deserialize(
                parser, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(st).hashSeed();
  }

  /**
   * Test {@link CreateCardPaymentRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)} with {@code parser}, {@code context}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreateCardPaymentRequest CreateCardPaymentRequestDeserializer.deserialize(JsonParser, DeserializationContext)"
  })
  public void testDeserializeWithParserContext2() {
    // Arrange
    CreateCardPaymentRequestDeserializer createCardPaymentRequestDeserializer =
        new CreateCardPaymentRequestDeserializer();

    CharsToNameCanonicalizer st = mock(CharsToNameCanonicalizer.class);
    when(st.hashSeed()).thenReturn(19088743);
    BufferRecycler br = new BufferRecycler();
    IOContext ctxt = new IOContext(br, ContentReference.redacted(), false);
    StringReader r = new StringReader("foo");
    JsonMapper codec = JsonMapper.builder().findAndAddModules().build();

    ReaderBasedJsonParser parser = new ReaderBasedJsonParser(ctxt, 1, r, codec, st);

    // Act and Assert
    assertThrows(
        BadRequestException.class,
        () ->
            createCardPaymentRequestDeserializer.deserialize(
                parser, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(st).hashSeed();
  }

  /**
   * Test {@link CreateCardPaymentRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)} with {@code parser}, {@code context}.
   *
   * <p>Method under test: {@link CreateCardPaymentRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreateCardPaymentRequest CreateCardPaymentRequestDeserializer.deserialize(JsonParser, DeserializationContext)"
  })
  public void testDeserializeWithParserContext3() {
    // Arrange
    CreateCardPaymentRequestDeserializer createCardPaymentRequestDeserializer =
        new CreateCardPaymentRequestDeserializer();

    CharsToNameCanonicalizer st = mock(CharsToNameCanonicalizer.class);
    when(st.hashSeed()).thenReturn(19088743);
    StreamReadConstraints src =
        StreamReadConstraints.builder()
            .maxDocumentLength(3L)
            .maxNameLength(3)
            .maxNestingDepth(2)
            .maxNumberLength(3)
            .maxStringLength(3)
            .maxTokenCount(3L)
            .build();
    BufferRecycler br = new BufferRecycler();

    IOContext ctxt = new IOContext(src, br, ContentReference.redacted(), true);
    StringReader r = new StringReader("foo");
    JsonMapper codec = JsonMapper.builder().findAndAddModules().build();

    ReaderBasedJsonParser parser = new ReaderBasedJsonParser(ctxt, 1, r, codec, st);

    // Act and Assert
    assertThrows(
        BadRequestException.class,
        () ->
            createCardPaymentRequestDeserializer.deserialize(
                parser, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(st).hashSeed();
  }
}
