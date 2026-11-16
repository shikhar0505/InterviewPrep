package org.shikhar.dropwizard;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import jakarta.validation.Validator;
import org.jdbi.v3.core.Jdbi;
import org.shikhar.dropwizard.db.EmployeeDAO;

public class EmployeeModule extends AbstractModule {
  private final Jdbi jdbi;
  private final Validator validator;

  public EmployeeModule(Jdbi jdbi, Validator validator) {
    this.jdbi = jdbi;
    this.validator = validator;
  }

  @Override
  protected void configure() {
    bind(Jdbi.class).toInstance(jdbi);
    bind(Validator.class).toInstance(validator);
  }

  @Provides
  @Singleton
  public EmployeeDAO provideEmployeeDAO(Jdbi jdbi) {
    return jdbi.onDemand(EmployeeDAO.class);
  }
}
