@echo off
echo Starting Spring Boot Application...
echo.

echo Compiling project...
call mvn clean compile

echo.
echo Starting application...
call mvn spring-boot:run

pause
