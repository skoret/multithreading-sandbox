#include "thread_pool.hpp"

int main() {
    thread_pool pool(10);
    size_t a[100];
    for (size_t i = 0; i < 100; i++) {
        pool.push_task([&a, i] { a[i] = i * i; });
    }
    pool.wait_for_tasks();
    std::cout << a[50];
}