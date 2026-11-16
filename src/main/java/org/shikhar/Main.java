package org.shikhar;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;
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
    super.initialize(bootstrap);
  }

  @Override
  public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
    // Initialize Dropwizard-managed JDBI
    Jdbi jdbi =
        new JdbiFactory().build(environment, appConfiguration.getDataSourceFactory(), "mysql");

    // Initialize Guice
    Injector injector = Guice.createInjector(new EmployeeModule(jdbi, environment.getValidator()));

    // Register Guice-created resources with Jersey
    environment.jersey().register(injector.getInstance(PingResource.class));
    environment.jersey().register(injector.getInstance(EmployeeResource.class));
  }
}
