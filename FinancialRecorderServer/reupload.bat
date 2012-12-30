@echo off

call mvn package -DskipTests

cd target

call vmc update

pause;