package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.gov.service.payments.commons.model.Source;

public class InternalDiffblueTest {
  /**
   * Test {@link Internal#getSource()}.
   *
   * <p>Method under test: {@link Internal#getSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional Internal.getSource()"})
  public void testGetSource() {
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
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Internal.<init>()", "void Internal.setSource(Source)"})
  public void testGettersAndSetters() {
    // Arrange and Act
    Internal actualInternal = new Internal();
    actualInternal.setSource(Source.CARD_API);

    // Assert
    Optional<Source> source = actualInternal.getSource();
    assertEquals(Source.CARD_API, source.get());
    assertTrue(source.isPresent());
  }
}
