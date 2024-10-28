#!/bin/bash

# Pull the PostgreSQL Docker image
docker pull postgres

# Run the PostgreSQL container
docker run --name postgres-container \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres

# Wait for a few seconds to ensure the database server is up
sleep 10

# Create the database
docker exec -it postgres-container psql -U postgres -c "CREATE DATABASE brikol;"

echo "PostgreSQL container is running and database 'brikol' has been created."
