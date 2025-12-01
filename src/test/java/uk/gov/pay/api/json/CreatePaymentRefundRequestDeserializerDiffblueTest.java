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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.exception.BadRequestException;
import uk.gov.pay.api.model.CreatePaymentRefundRequest;
import uk.gov.pay.api.validation.PaymentRefundRequestValidator;

class CreatePaymentRefundRequestDeserializerDiffblueTest {
  /**
   * Test {@link
   * CreatePaymentRefundRequestDeserializer#CreatePaymentRefundRequestDeserializer(PaymentRefundRequestValidator)}.
   *
   * <p>Method under test: {@link
   * CreatePaymentRefundRequestDeserializer#CreatePaymentRefundRequestDeserializer(PaymentRefundRequestValidator)}
   */
  @Test
  @DisplayName("Test new CreatePaymentRefundRequestDeserializer(PaymentRefundRequestValidator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CreatePaymentRefundRequestDeserializer.<init>(PaymentRefundRequestValidator)"
  })
  void testNewCreatePaymentRefundRequestDeserializer() {
    // Arrange and Act
    CreatePaymentRefundRequestDeserializer actualCreatePaymentRefundRequestDeserializer =
        new CreatePaymentRefundRequestDeserializer(new PaymentRefundRequestValidator());

    // Assert
    assertNull(actualCreatePaymentRefundRequestDeserializer.getValueType());
    Class<CreatePaymentRefundRequest> expectedValueClass = CreatePaymentRefundRequest.class;
    assertEquals(expectedValueClass, actualCreatePaymentRefundRequestDeserializer.getValueClass());
  }

  /**
   * Test {@link CreatePaymentRefundRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)} with {@code parser}, {@code context}.
   *
   * <p>Method under test: {@link CreatePaymentRefundRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @DisplayName("Test deserialize(JsonParser, DeserializationContext) with 'parser', 'context'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatePaymentRefundRequest CreatePaymentRefundRequestDeserializer.deserialize(JsonParser, DeserializationContext)"
  })
  void testDeserializeWithParserContext() {
    // Arrange
    CreatePaymentRefundRequestDeserializer createPaymentRefundRequestDeserializer =
        new CreatePaymentRefundRequestDeserializer(new PaymentRefundRequestValidator());

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
            createPaymentRefundRequestDeserializer.deserialize(
                parser, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(st).hashSeed();
  }

  /**
   * Test {@link CreatePaymentRefundRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)} with {@code parser}, {@code context}.
   *
   * <p>Method under test: {@link CreatePaymentRefundRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @DisplayName("Test deserialize(JsonParser, DeserializationContext) with 'parser', 'context'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatePaymentRefundRequest CreatePaymentRefundRequestDeserializer.deserialize(JsonParser, DeserializationContext)"
  })
  void testDeserializeWithParserContext2() {
    // Arrange
    CreatePaymentRefundRequestDeserializer createPaymentRefundRequestDeserializer =
        new CreatePaymentRefundRequestDeserializer(new PaymentRefundRequestValidator());

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
            createPaymentRefundRequestDeserializer.deserialize(
                parser, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(st).hashSeed();
  }

  /**
   * Test {@link CreatePaymentRefundRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)} with {@code parser}, {@code context}.
   *
   * <p>Method under test: {@link CreatePaymentRefundRequestDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @DisplayName("Test deserialize(JsonParser, DeserializationContext) with 'parser', 'context'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CreatePaymentRefundRequest CreatePaymentRefundRequestDeserializer.deserialize(JsonParser, DeserializationContext)"
  })
  void testDeserializeWithParserContext3() {
    // Arrange
    CreatePaymentRefundRequestDeserializer createPaymentRefundRequestDeserializer =
        new CreatePaymentRefundRequestDeserializer(new PaymentRefundRequestValidator());

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
            createPaymentRefundRequestDeserializer.deserialize(
                parser, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig()))));
    verify(st).hashSeed();
  }
}
