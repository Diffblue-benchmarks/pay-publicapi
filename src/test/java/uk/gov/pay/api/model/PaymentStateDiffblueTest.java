package uk.gov.pay.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.math.BigInteger;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PaymentStateDiffblueTest {
  /**
   * Test {@link PaymentState#createPaymentState(JsonNode)}.
   *
   * <ul>
   *   <li>Given False.
   *   <li>Then return Code is {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#createPaymentState(JsonNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentState PaymentState.createPaymentState(JsonNode)"})
  public void testCreatePaymentState_givenFalse_thenReturnCodeIsFalseToString() {
    // Arrange
    ArrayNode node = mock(ArrayNode.class);
    when(node.has(Mockito.<String>any())).thenReturn(true);
    when(node.get(Mockito.<String>any())).thenReturn(BooleanNode.getFalse());

    // Act
    PaymentState actualCreatePaymentStateResult = PaymentState.createPaymentState(node);

    // Assert
    verify(node, atLeast(1)).has(Mockito.<String>any());
    verify(node, atLeast(1)).get(Mockito.<String>any());
    assertFalse(actualCreatePaymentStateResult.getCanRetry());
    assertEquals(Boolean.FALSE.toString(), actualCreatePaymentStateResult.getCode());
    assertEquals(Boolean.FALSE.toString(), actualCreatePaymentStateResult.getMessage());
    assertEquals(Boolean.FALSE.toString(), actualCreatePaymentStateResult.getStatus());
  }

  /**
   * Test {@link PaymentState#createPaymentState(JsonNode)}.
   *
   * <ul>
   *   <li>Given False.
   *   <li>Then return Status is {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#createPaymentState(JsonNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentState PaymentState.createPaymentState(JsonNode)"})
  public void testCreatePaymentState_givenFalse_thenReturnStatusIsFalseToString() {
    // Arrange
    ArrayNode node = mock(ArrayNode.class);
    when(node.has(Mockito.<String>any())).thenReturn(false);
    when(node.get(Mockito.<String>any())).thenReturn(BooleanNode.getFalse());

    // Act
    PaymentState actualCreatePaymentStateResult = PaymentState.createPaymentState(node);

    // Assert
    verify(node, atLeast(1)).has(Mockito.<String>any());
    verify(node, atLeast(1)).get(Mockito.<String>any());
    assertNull(actualCreatePaymentStateResult.getCanRetry());
    assertNull(actualCreatePaymentStateResult.getCode());
    assertNull(actualCreatePaymentStateResult.getMessage());
    assertEquals(Boolean.FALSE.toString(), actualCreatePaymentStateResult.getStatus());
  }

  /**
   * Test {@link PaymentState#createPaymentState(JsonNode)}.
   *
   * <ul>
   *   <li>Given Instance.
   *   <li>Then return Code is empty string.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#createPaymentState(JsonNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentState PaymentState.createPaymentState(JsonNode)"})
  public void testCreatePaymentState_givenInstance_thenReturnCodeIsEmptyString() {
    // Arrange
    ArrayNode node = mock(ArrayNode.class);
    when(node.has(Mockito.<String>any())).thenReturn(true);
    when(node.get(Mockito.<String>any())).thenReturn(MissingNode.getInstance());

    // Act
    PaymentState actualCreatePaymentStateResult = PaymentState.createPaymentState(node);

    // Assert
    verify(node, atLeast(1)).has(Mockito.<String>any());
    verify(node, atLeast(1)).get(Mockito.<String>any());
    assertEquals("", actualCreatePaymentStateResult.getCode());
    assertEquals("", actualCreatePaymentStateResult.getMessage());
    assertEquals("", actualCreatePaymentStateResult.getStatus());
    assertFalse(actualCreatePaymentStateResult.getCanRetry());
  }

  /**
   * Test {@link PaymentState#createPaymentState(JsonNode)}.
   *
   * <ul>
   *   <li>Given Instance.
   *   <li>Then return Status is empty string.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#createPaymentState(JsonNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentState PaymentState.createPaymentState(JsonNode)"})
  public void testCreatePaymentState_givenInstance_thenReturnStatusIsEmptyString() {
    // Arrange
    ArrayNode node = mock(ArrayNode.class);
    when(node.has(Mockito.<String>any())).thenReturn(false);
    when(node.get(Mockito.<String>any())).thenReturn(MissingNode.getInstance());

    // Act
    PaymentState actualCreatePaymentStateResult = PaymentState.createPaymentState(node);

    // Assert
    verify(node, atLeast(1)).has(Mockito.<String>any());
    verify(node, atLeast(1)).get(Mockito.<String>any());
    assertEquals("", actualCreatePaymentStateResult.getStatus());
    assertNull(actualCreatePaymentStateResult.getCanRetry());
    assertNull(actualCreatePaymentStateResult.getCode());
    assertNull(actualCreatePaymentStateResult.getMessage());
  }

  /**
   * Test {@link PaymentState#createPaymentState(JsonNode)}.
   *
   * <ul>
   *   <li>Given valueOf ten.
   *   <li>Then return Code is {@code 10.0}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#createPaymentState(JsonNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentState PaymentState.createPaymentState(JsonNode)"})
  public void testCreatePaymentState_givenValueOfTen_thenReturnCodeIs100() {
    // Arrange
    ArrayNode node = mock(ArrayNode.class);
    when(node.has(Mockito.<String>any())).thenReturn(true);
    when(node.get(Mockito.<String>any())).thenReturn(DoubleNode.valueOf(10.0d));

    // Act
    PaymentState actualCreatePaymentStateResult = PaymentState.createPaymentState(node);

    // Assert
    verify(node, atLeast(1)).has(Mockito.<String>any());
    verify(node, atLeast(1)).get(Mockito.<String>any());
    assertEquals("10.0", actualCreatePaymentStateResult.getCode());
    assertEquals("10.0", actualCreatePaymentStateResult.getMessage());
    assertEquals("10.0", actualCreatePaymentStateResult.getStatus());
    assertFalse(actualCreatePaymentStateResult.getCanRetry());
  }

  /**
   * Test {@link PaymentState#createPaymentState(JsonNode)}.
   *
   * <ul>
   *   <li>Given valueOf ten.
   *   <li>Then return Status is {@code 10.0}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#createPaymentState(JsonNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentState PaymentState.createPaymentState(JsonNode)"})
  public void testCreatePaymentState_givenValueOfTen_thenReturnStatusIs100() {
    // Arrange
    ArrayNode node = mock(ArrayNode.class);
    when(node.has(Mockito.<String>any())).thenReturn(false);
    when(node.get(Mockito.<String>any())).thenReturn(DoubleNode.valueOf(10.0d));

    // Act
    PaymentState actualCreatePaymentStateResult = PaymentState.createPaymentState(node);

    // Assert
    verify(node, atLeast(1)).has(Mockito.<String>any());
    verify(node, atLeast(1)).get(Mockito.<String>any());
    assertEquals("10.0", actualCreatePaymentStateResult.getStatus());
    assertNull(actualCreatePaymentStateResult.getCanRetry());
    assertNull(actualCreatePaymentStateResult.getCode());
    assertNull(actualCreatePaymentStateResult.getMessage());
  }

  /**
   * Test {@link PaymentState#createPaymentState(JsonNode)}.
   *
   * <ul>
   *   <li>Then return Status is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#createPaymentState(JsonNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PaymentState PaymentState.createPaymentState(JsonNode)"})
  public void testCreatePaymentState_thenReturnStatusIs42() {
    // Arrange
    ArrayNode node = mock(ArrayNode.class);
    when(node.has(Mockito.<String>any())).thenReturn(false);
    BigInteger v = BigInteger.valueOf(42L);
    when(node.get(Mockito.<String>any())).thenReturn(new BigIntegerNode(v));

    // Act
    PaymentState actualCreatePaymentStateResult = PaymentState.createPaymentState(node);

    // Assert
    verify(node, atLeast(1)).has(Mockito.<String>any());
    verify(node, atLeast(1)).get(Mockito.<String>any());
    assertEquals("42", actualCreatePaymentStateResult.getStatus());
    assertNull(actualCreatePaymentStateResult.getCanRetry());
    assertNull(actualCreatePaymentStateResult.getCode());
    assertNull(actualCreatePaymentStateResult.getMessage());
    assertTrue(actualCreatePaymentStateResult.isFinished());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Status is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentState#PaymentState()}
   *   <li>{@link PaymentState#getCanRetry()}
   *   <li>{@link PaymentState#getCode()}
   *   <li>{@link PaymentState#getMessage()}
   *   <li>{@link PaymentState#getStatus()}
   *   <li>{@link PaymentState#isFinished()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentState.<init>()",
    "void PaymentState.<init>(String, boolean)",
    "void PaymentState.<init>(String, boolean, String, String)",
    "void PaymentState.<init>(String, boolean, String, String, Boolean)",
    "Boolean PaymentState.getCanRetry()",
    "String PaymentState.getCode()",
    "String PaymentState.getMessage()",
    "String PaymentState.getStatus()",
    "boolean PaymentState.isFinished()"
  })
  public void testGettersAndSetters_thenReturnStatusIsNull() {
    // Arrange and Act
    PaymentState actualPaymentState = new PaymentState();
    Boolean actualCanRetry = actualPaymentState.getCanRetry();
    String actualCode = actualPaymentState.getCode();
    String actualMessage = actualPaymentState.getMessage();
    String actualStatus = actualPaymentState.getStatus();

    // Assert
    assertNull(actualCanRetry);
    assertNull(actualCode);
    assertNull(actualMessage);
    assertNull(actualStatus);
    assertFalse(actualPaymentState.isFinished());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Not all who wander are lost}.
   *   <li>Then return CanRetry.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentState#PaymentState(String, boolean, String, String, Boolean)}
   *   <li>{@link PaymentState#getCanRetry()}
   *   <li>{@link PaymentState#getCode()}
   *   <li>{@link PaymentState#getMessage()}
   *   <li>{@link PaymentState#getStatus()}
   *   <li>{@link PaymentState#isFinished()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentState.<init>()",
    "void PaymentState.<init>(String, boolean)",
    "void PaymentState.<init>(String, boolean, String, String)",
    "void PaymentState.<init>(String, boolean, String, String, Boolean)",
    "Boolean PaymentState.getCanRetry()",
    "String PaymentState.getCode()",
    "String PaymentState.getMessage()",
    "String PaymentState.getStatus()",
    "boolean PaymentState.isFinished()"
  })
  public void testGettersAndSetters_whenNotAllWhoWanderAreLost_thenReturnCanRetry() {
    // Arrange and Act
    PaymentState actualPaymentState =
        new PaymentState("Status", true, "Not all who wander are lost", "Code", true);
    Boolean actualCanRetry = actualPaymentState.getCanRetry();
    String actualCode = actualPaymentState.getCode();
    String actualMessage = actualPaymentState.getMessage();
    String actualStatus = actualPaymentState.getStatus();

    // Assert
    assertEquals("Code", actualCode);
    assertEquals("Not all who wander are lost", actualMessage);
    assertEquals("Status", actualStatus);
    assertTrue(actualCanRetry);
    assertTrue(actualPaymentState.isFinished());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Not all who wander are lost}.
   *   <li>Then return {@code Code}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentState#PaymentState(String, boolean, String, String)}
   *   <li>{@link PaymentState#getCanRetry()}
   *   <li>{@link PaymentState#getCode()}
   *   <li>{@link PaymentState#getMessage()}
   *   <li>{@link PaymentState#getStatus()}
   *   <li>{@link PaymentState#isFinished()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentState.<init>()",
    "void PaymentState.<init>(String, boolean)",
    "void PaymentState.<init>(String, boolean, String, String)",
    "void PaymentState.<init>(String, boolean, String, String, Boolean)",
    "Boolean PaymentState.getCanRetry()",
    "String PaymentState.getCode()",
    "String PaymentState.getMessage()",
    "String PaymentState.getStatus()",
    "boolean PaymentState.isFinished()"
  })
  public void testGettersAndSetters_whenNotAllWhoWanderAreLost_thenReturnCode() {
    // Arrange and Act
    PaymentState actualPaymentState =
        new PaymentState("Status", true, "Not all who wander are lost", "Code");
    Boolean actualCanRetry = actualPaymentState.getCanRetry();
    String actualCode = actualPaymentState.getCode();
    String actualMessage = actualPaymentState.getMessage();
    String actualStatus = actualPaymentState.getStatus();

    // Assert
    assertEquals("Code", actualCode);
    assertEquals("Not all who wander are lost", actualMessage);
    assertEquals("Status", actualStatus);
    assertNull(actualCanRetry);
    assertTrue(actualPaymentState.isFinished());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Status}.
   *   <li>Then return Code is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentState#PaymentState(String, boolean)}
   *   <li>{@link PaymentState#getCanRetry()}
   *   <li>{@link PaymentState#getCode()}
   *   <li>{@link PaymentState#getMessage()}
   *   <li>{@link PaymentState#getStatus()}
   *   <li>{@link PaymentState#isFinished()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PaymentState.<init>()",
    "void PaymentState.<init>(String, boolean)",
    "void PaymentState.<init>(String, boolean, String, String)",
    "void PaymentState.<init>(String, boolean, String, String, Boolean)",
    "Boolean PaymentState.getCanRetry()",
    "String PaymentState.getCode()",
    "String PaymentState.getMessage()",
    "String PaymentState.getStatus()",
    "boolean PaymentState.isFinished()"
  })
  public void testGettersAndSetters_whenStatus_thenReturnCodeIsNull() {
    // Arrange and Act
    PaymentState actualPaymentState = new PaymentState("Status", true);
    Boolean actualCanRetry = actualPaymentState.getCanRetry();
    String actualCode = actualPaymentState.getCode();
    String actualMessage = actualPaymentState.getMessage();
    String actualStatus = actualPaymentState.getStatus();

    // Assert
    assertEquals("Status", actualStatus);
    assertNull(actualCanRetry);
    assertNull(actualCode);
    assertNull(actualMessage);
    assertTrue(actualPaymentState.isFinished());
  }

  /**
   * Test {@link PaymentState#toString()}.
   *
   * <p>Method under test: {@link PaymentState#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PaymentState.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "PaymentState{status='Status', finished='true', message=null, code=null}",
        new PaymentState("Status", true).toString());
  }

  /**
   * Test {@link PaymentState#equals(Object)}, and {@link PaymentState#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentState#equals(Object)}
   *   <li>{@link PaymentState#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PaymentState.equals(Object)", "int PaymentState.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    PaymentState paymentState = new PaymentState("Status", true);
    PaymentState paymentState2 = new PaymentState("Status", true);

    // Act and Assert
    assertEquals(paymentState, paymentState2);
    assertEquals(paymentState.hashCode(), paymentState2.hashCode());
  }

  /**
   * Test {@link PaymentState#equals(Object)}, and {@link PaymentState#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PaymentState#equals(Object)}
   *   <li>{@link PaymentState#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PaymentState.equals(Object)", "int PaymentState.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    PaymentState paymentState = new PaymentState("Status", true);

    // Act and Assert
    assertEquals(paymentState, paymentState);
    int expectedHashCodeResult = paymentState.hashCode();
    assertEquals(expectedHashCodeResult, paymentState.hashCode());
  }

  /**
   * Test {@link PaymentState#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PaymentState.equals(Object)", "int PaymentState.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    PaymentState paymentState = new PaymentState(null, true);

    // Act and Assert
    assertNotEquals(paymentState, new PaymentState("Status", true));
  }

  /**
   * Test {@link PaymentState#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PaymentState.equals(Object)", "int PaymentState.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    PaymentState paymentState = new PaymentState("Status", false);

    // Act and Assert
    assertNotEquals(paymentState, new PaymentState("Status", true));
  }

  /**
   * Test {@link PaymentState#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PaymentState.equals(Object)", "int PaymentState.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    PaymentState paymentState =
        new PaymentState("Status", true, "Not all who wander are lost", "Code");

    // Act and Assert
    assertNotEquals(paymentState, new PaymentState("Status", true));
  }

  /**
   * Test {@link PaymentState#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PaymentState.equals(Object)", "int PaymentState.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    PaymentState paymentState = new PaymentState("Status", true, null, "Code");

    // Act and Assert
    assertNotEquals(paymentState, new PaymentState("Status", true));
  }

  /**
   * Test {@link PaymentState#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PaymentState.equals(Object)", "int PaymentState.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new PaymentState("Status", true), null);
  }

  /**
   * Test {@link PaymentState#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link PaymentState#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PaymentState.equals(Object)", "int PaymentState.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new PaymentState("Status", true), "Different type to PaymentState");
  }
}
