#include "../common/thread_pool.hpp"

#include <thread>

int main() {
    thread_pool pool(10);

    auto future = pool.submit([](int x) -> int {
            std::this_thread::sleep_for(std::chrono::seconds(1));
            return x + 1;
        },7);

    int result = future.get();
    std::cout << result;
}