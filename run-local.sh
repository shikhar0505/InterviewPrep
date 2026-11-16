
#!/bin/bash

set -e

echo "Starting PostgreSQL..."
docker compose up -d

echo "Building project..."
mvn clean package -DskipTests

echo "Running database migrations..."
mvn exec:java \
  -Dexec.mainClass=org.shikhar.Main \
  -Dexec.args="db migrate config.yaml"

echo "Starting Dropwizard..."
mvn exec:java \
  -Dexec.mainClass=org.shikhar.Main \
  -Dexec.args="server config.yaml"