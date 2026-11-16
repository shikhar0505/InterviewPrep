package org.shikhar.dropwizard.auth;

import com.google.inject.Inject;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import java.util.Optional;

public class OAuthAuthenticator implements Authenticator<String, UserPrincipal> {
  @Inject
  public OAuthAuthenticator() {}

  @Override
  public Optional<UserPrincipal> authenticate(String token) throws AuthenticationException {
    // Validate your JWT / OAuth token signature, expiry, issuer, and audience here
    if (token != null && !token.isBlank()) {
      return Optional.of(new UserPrincipal("authenticated-client"));
    }
    return Optional.empty();
  }
}
