cmake_minimum_required(VERSION 3.10)
project(simple_threading)

set(CMAKE_CXX_STANDARD 17)

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)

add_executable(simple_threading main.cpp)

target_link_libraries(simple_threading PRIVATE Threads::Threads)

