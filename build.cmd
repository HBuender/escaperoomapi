call mvn clean install
call docker build -t hbuender/connectionroom:latest .
call docker push hbuender/connectionroom:latest
