cmake_minimum_required(VERSION 3.10)
project(thread_pool)

set(CMAKE_CXX_STANDARD 17)

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)

add_executable(thread_pool main.cpp)

target_link_libraries(thread_pool PRIVATE Threads::Threads)

