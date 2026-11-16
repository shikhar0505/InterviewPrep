package org.shikhar;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hubspot.rosetta.Rosetta;
import com.hubspot.rosetta.jdbi3.RosettaObjectMapper;
import com.hubspot.rosetta.jdbi3.RosettaRowMapperFactory;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import org.jdbi.v3.core.Jdbi;
import org.shikhar.dropwizard.EmployeeModule;
import org.shikhar.dropwizard.resources.EmployeeResource;
import org.shikhar.dropwizard.resources.PingResource;

public class Main extends Application<AppConfiguration> {
  public static void main(String[] args) throws Exception {
    new Main().run(args);
  }

  @Override
  public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());

    bootstrap.addBundle(
        new MigrationsBundle<AppConfiguration>() {
          @Override
          public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDataSourceFactory();
          }
        });

    super.initialize(bootstrap);
  }

  @Override
  public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
    // Initialize Dropwizard-managed JDBI
    Jdbi jdbi =
        new JdbiFactory().build(environment, appConfiguration.getDataSourceFactory(), "postgresql");

      Rosetta.getMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
      jdbi.registerRowMapper(new RosettaRowMapperFactory());

      ObjectMapper rosettaMapper = Rosetta.getMapper()
              .copy()
              .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
              .enable(MapperFeature.ALLOW_EXPLICIT_PROPERTY_RENAMING);
      jdbi.getConfig(RosettaObjectMapper.class).setObjectMapper(rosettaMapper);
      jdbi.registerRowMapper(new RosettaRowMapperFactory());

    // Initialize Guice
    Injector injector = Guice.createInjector(new EmployeeModule(jdbi, environment.getValidator()));

    // Register Guice-created resources with Jersey
    environment.jersey().register(injector.getInstance(PingResource.class));
    environment.jersey().register(injector.getInstance(EmployeeResource.class));
  }
}
