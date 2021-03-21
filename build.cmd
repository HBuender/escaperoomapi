call mvn clean install
call docker build -t hbuender/kvescaperoomroom:latest .
call docker push hbuender/kvescaperoomroom:latest
