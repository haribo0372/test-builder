#!/bin/bash
docker build -t alik8haribo/quiz-builder:latest .
docker push alik8haribo/quiz-builder:latest
# docker run -p 8080:8080 --name quiz-builder alik8haribo/quiz-builder:latest

