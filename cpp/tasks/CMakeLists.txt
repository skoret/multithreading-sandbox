cmake_minimum_required(VERSION 3.10)
project(tasks)

set(CMAKE_CXX_STANDARD 17)

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)

add_executable(tasks main.cpp)

target_link_libraries(tasks PRIVATE Threads::Threads)

