call mvn clean install
call docker build -t hbuender/escaperoom:latest .
call docker push hbuender/escaperoom:latest
