package uk.gov.pay.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.pay.api.model.Wallet;

class WalletDeserializerDiffblueTest {
  /**
   * Test {@link WalletDeserializer#deserialize(JsonParser, DeserializationContext)} with {@code
   * jsonParser}, {@code deserializationContext}.
   *
   * <ul>
   *   <li>Given {@code Text}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WalletDeserializer#deserialize(JsonParser,
   * DeserializationContext)}
   */
  @Test
  @DisplayName(
      "Test deserialize(JsonParser, DeserializationContext) with 'jsonParser', 'deserializationContext'; given 'Text'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Wallet WalletDeserializer.deserialize(JsonParser, DeserializationContext)"})
  void testDeserializeWithJsonParserDeserializationContext_givenText_thenReturnNull()
      throws IOException {
    // Arrange
    WalletDeserializer walletDeserializer = new WalletDeserializer();

    JsonParserSequence d = mock(JsonParserSequence.class);
    when(d.getText()).thenReturn("Text");
    JsonParserDelegate jsonParser = new JsonParserDelegate(d);

    // Act
    Wallet actualDeserializeResult =
        walletDeserializer.deserialize(
            jsonParser, new Impl(new BeanDeserializerFactory(new DeserializerFactoryConfig())));

    // Assert
    verify(d).getText();
    assertNull(actualDeserializeResult);
  }

  /**
   * Test new {@link WalletDeserializer} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link WalletDeserializer}
   */
  @Test
  @DisplayName("Test new WalletDeserializer (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WalletDeserializer.<init>()"})
  void testNewWalletDeserializer() {
    // Arrange and Act
    WalletDeserializer actualWalletDeserializer = new WalletDeserializer();

    // Assert
    assertNull(actualWalletDeserializer.getDelegatee());
    assertNull(actualWalletDeserializer.getObjectIdReader());
    assertNull(actualWalletDeserializer.getEmptyValue());
    assertNull(actualWalletDeserializer.getKnownPropertyNames());
    assertNull(actualWalletDeserializer.getNullValue());
    assertEquals(AccessPattern.CONSTANT, actualWalletDeserializer.getNullAccessPattern());
    assertEquals(AccessPattern.DYNAMIC, actualWalletDeserializer.getEmptyAccessPattern());
    assertFalse(actualWalletDeserializer.isCachable());
  }
}
