package uk.gov.pay.api.exception.mapper;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.dropwizard.jersey.validation.JerseyViolationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.metadata.ConstraintDescriptor;
import java.util.HashSet;
import org.glassfish.jersey.server.model.Invocable;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ViolationExceptionMapperDiffblueTest {
  /**
   * Test {@link ViolationExceptionMapper#toResponse(JerseyViolationException)} with {@code
   * exception}.
   *
   * <ul>
   *   <li>Then throw {@link JerseyViolationException}.
   * </ul>
   *
   * <p>Method under test: {@link ViolationExceptionMapper#toResponse(JerseyViolationException)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "jakarta.ws.rs.core.Response ViolationExceptionMapper.toResponse(JerseyViolationException)"
  })
  public void testToResponseWithException_thenThrowJerseyViolationException() {
    // Arrange
    ViolationExceptionMapper violationExceptionMapper = new ViolationExceptionMapper();

    ConstraintViolation<Object> constraintViolation = mock(ConstraintViolation.class);
    JerseyViolationException jerseyViolationException =
        new JerseyViolationException(new HashSet<>(), mock(Invocable.class));
    org.mockito.Mockito.<ConstraintDescriptor<?>>when(constraintViolation.getConstraintDescriptor())
        .thenThrow(jerseyViolationException);
    when(constraintViolation.getPropertyPath()).thenReturn(PathImpl.createRootPath());
    when(constraintViolation.getMessage()).thenReturn("Not all who wander are lost");

    HashSet<ConstraintViolation<?>> constraintViolations = new HashSet<>();
    constraintViolations.add(constraintViolation);
    JerseyViolationException exception =
        new JerseyViolationException(constraintViolations, mock(Invocable.class));

    // Act and Assert
    assertThrows(
        JerseyViolationException.class, () -> violationExceptionMapper.toResponse(exception));
    verify(constraintViolation).getConstraintDescriptor();
    verify(constraintViolation, atLeast(1)).getMessage();
    verify(constraintViolation, atLeast(1)).getPropertyPath();
  }
}
