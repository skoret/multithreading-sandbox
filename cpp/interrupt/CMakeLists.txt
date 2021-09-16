cmake_minimum_required(VERSION 3.10)
project(interrupt)

set(CMAKE_CXX_STANDARD 17)

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)

add_executable(interrupt main.cpp)

find_package(Boost COMPONENTS thread REQUIRED)
include_directories(${Boost_INCLUDE_DIR})
target_link_libraries(interrupt LINK_PUBLIC ${Boost_LIBRARIES})

