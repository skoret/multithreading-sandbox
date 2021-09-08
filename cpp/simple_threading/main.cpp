#include <iostream>
#include <chrono>
#include <string>
#include <vector>
#include <thread>

const int32_t max_number = 1e8;

int64_t sum(int32_t start, int32_t finish) {
    int64_t result = 0;
    for (auto i = start; i < finish; i++) {
        result += i;
    }
    return result;
}

void with_threads() {
    std::vector<std::thread> threads;
    const int division = 8;
    std::vector<int64_t> results(division + 1);
    const int32_t step = max_number / division;

    for (int32_t i = 0; i * step < max_number; i++) {
        std::thread t ([&results, i] {
            int32_t start = i * step;
            int32_t finish = std::min((i + 1) * step, max_number);
            results[i] = sum(start, finish);
        });
        threads.emplace_back(std::move(t));
    }
    for (auto &thread: threads) {
        thread.join();
    }
    int64_t result = 0;
    for (auto x: results) {
        result += x;
    }
    std::cout << "Sum is: " << result << std::endl;
}

void without_threads() {
    std::cout << "Sum is: " << sum(0, max_number) << std::endl;
}

template<typename T>
void with_clock(T function, std::string caption) {
    std::cout << caption << std::endl;
    auto t1 = std::chrono::high_resolution_clock::now();
    function();
    auto t2 = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> ms_double = t2 - t1;
    std::cout << "Milliseconds: " << ms_double.count() << std::endl;
}

int main() {
    with_clock(without_threads, "Without threads");
    with_clock(with_threads, "With threads");
    return 0;
}
