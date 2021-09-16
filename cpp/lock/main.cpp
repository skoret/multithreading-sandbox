#include <iostream>
#include <thread>

const int N = 1000000;

int result = 0;

void accumulate_sum_unsafe() {
    for (int i = 0; i < N; i++) {
        result = result + 1;
    }
}

std::mutex m;

void accumulate_sum_safe_mutex() {
    for (int i = 0; i < N; i++) {
        m.lock();
        result = result + 1;
        m.unlock();
    }
}

void accumulate_sum_safe_lock() {
    for (int i = 0; i < N; i++) {
        std::lock_guard<std::mutex> lock(m);
        result = result + 1;
    }
}

void test(std::function<void()> f, std::string s) {
    result = 0;
    std::thread t(f);
    std::thread p(f);
    t.join();
    p.join();
    std::cout << s << ": " << result << std::endl;
}

int main() {
    test(accumulate_sum_unsafe, "accumulate_sum_unsafe");
    test(accumulate_sum_safe_mutex, "accumulate_sum_safe_mutex");
    test(accumulate_sum_safe_lock, "accumulate_sum_safe_lock");
    return 0;
}
