package org.shikhar.dropwizard.api;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;

@Value.Immutable
public interface Employee {

  public int getId();

  @Length(min = 2, max = 45)
  public String getFirstName();

  @Length(min = 2, max = 45)
  public String getLastName();

  @Pattern(regexp = ".+@.+\\.[a-z]+")
  @Value.Redacted
  public String getEmail();
}
