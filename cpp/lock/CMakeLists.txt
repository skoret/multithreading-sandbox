cmake_minimum_required(VERSION 3.10)
project(lock)

set(CMAKE_CXX_STANDARD 17)

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)

add_executable(lock main.cpp)

target_link_libraries(lock PRIVATE Threads::Threads)

