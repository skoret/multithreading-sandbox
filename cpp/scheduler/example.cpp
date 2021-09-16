#include <iostream>

#include "Scheduler.h"

void message(const std::string &s) {
  std::cout << s << std::endl;
}

int main() {
  // number of tasks that can run simultaneously
  // Note: not the number of tasks that can be added,
  //       but number of tasks that can be run in parallel
  unsigned int max_n_threads = 12;

  // Make a new scheduling object.
  // Note: s cannot be moved or copied
  Bosma::Scheduler s(max_n_threads);

  // every second call message("every second")
  s.every(std::chrono::seconds(1), message, "every second");

  std::this_thread::sleep_for(std::chrono::seconds(10));
}