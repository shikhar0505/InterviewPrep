package org.shikhar.dropwizard.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hubspot.rosetta.annotations.RosettaNaming;
import jakarta.validation.constraints.Pattern;
import java.util.Optional;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableEmployee.class)
@RosettaNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public interface Employee {

  Optional<Integer> getId();

  @Length(min = 2, max = 45)
  String getFirstName();

  @Length(min = 2, max = 45)
  String getLastName();

  @Pattern(regexp = ".+@.+\\.[a-z]+")
  @Value.Redacted
  String getEmail();
}
