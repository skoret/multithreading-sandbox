FROM ubuntu:20.04

RUN apt update && DEBIAN_FRONTEND=noninteractive apt install -y build-essential cmake
COPY / /root/cpp-sandbox
WORKDIR /root/cpp-sandbox
RUN cd simple_threading && mkdir build && cd build && cmake .. && make && ./simple_threading
