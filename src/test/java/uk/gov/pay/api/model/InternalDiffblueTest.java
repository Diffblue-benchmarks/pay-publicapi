package uk.gov.pay.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.gov.service.payments.commons.model.Source;

class InternalDiffblueTest {
  /**
   * Test {@link Internal#getSource()}.
   *
   * <p>Method under test: {@link Internal#getSource()}
   */
  @Test
  @DisplayName("Test getSource()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional Internal.getSource()"})
  void testGetSource() {
    // Arrange, Act and Assert
    assertFalse(new Internal().getSource().isPresent());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Internal}
   *   <li>{@link Internal#setSource(Source)}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Internal.<init>()", "void Internal.setSource(Source)"})
  void testGettersAndSetters() {
    // Arrange and Act
    Internal actualInternal = new Internal();
    actualInternal.setSource(Source.CARD_API);

    // Assert
    Optional<Source> source = actualInternal.getSource();
    assertEquals(Source.CARD_API, source.get());
    assertTrue(source.isPresent());
  }
}
