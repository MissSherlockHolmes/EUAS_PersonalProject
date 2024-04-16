FROM ubuntu:latest
LABEL authors="missherlock"

ENTRYPOINT ["top", "-b"]