package org.shikhar.dropwizard.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hubspot.rosetta.annotations.RosettaNaming;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableEmployeePage.class)
@RosettaNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public interface EmployeePage {
  List<Employee> getData();

  Optional<String> getNextCursor();

  boolean getHasMore();
}
