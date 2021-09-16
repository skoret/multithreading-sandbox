cmake_minimum_required(VERSION 3.10)
project(condition)

set(CMAKE_CXX_STANDARD 17)

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)

add_executable(condition main.cpp)

target_link_libraries(condition PRIVATE Threads::Threads)

