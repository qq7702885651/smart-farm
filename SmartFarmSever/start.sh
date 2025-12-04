#!/bin/bash
echo "Starting Spring Boot Application..."
echo

echo "Compiling project..."
mvn clean compile

echo
echo "Starting application..."
mvn spring-boot:run
