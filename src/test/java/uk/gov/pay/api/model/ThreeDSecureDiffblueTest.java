package uk.gov.pay.api.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ThreeDSecureDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return not Required.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ThreeDSecure#ThreeDSecure()}
   *   <li>{@link ThreeDSecure#isRequired()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ThreeDSecure.<init>()",
    "void ThreeDSecure.<init>(boolean)",
    "boolean ThreeDSecure.isRequired()"
  })
  public void testGettersAndSetters_thenReturnNotRequired() {
    // Arrange, Act and Assert
    assertFalse(new ThreeDSecure().isRequired());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return Required.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ThreeDSecure#ThreeDSecure(boolean)}
   *   <li>{@link ThreeDSecure#isRequired()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ThreeDSecure.<init>()",
    "void ThreeDSecure.<init>(boolean)",
    "boolean ThreeDSecure.isRequired()"
  })
  public void testGettersAndSetters_whenTrue_thenReturnRequired() {
    // Arrange, Act and Assert
    assertTrue(new ThreeDSecure(true).isRequired());
  }
}
