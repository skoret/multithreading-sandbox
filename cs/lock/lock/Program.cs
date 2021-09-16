using System;
using System.Threading;

namespace lockns
{
class Program
{
    const int N = 1000000;

    static int result = 0;

    static void accumulate_sum_unsafe() {
        for (int i = 0; i < N; i++) {
            result = result + 1;
        }
    }

    static object lockObject = new object();

    static void accumulate_sum_safe_lock() {
        for (int i = 0; i < N; i++) {
            lock (lockObject)
            {
                result = result + 1;    
            }
        }
    }

    static void accumulate_sum_safe_monitor() {
        for (int i = 0; i < N; i++) {
            Monitor.Enter(lockObject);
            result = result + 1;
            Monitor.Exit(lockObject);
        }
    }

    public delegate void Del();
    static void test(Del f, String s) {
        result = 0;
        Thread t = new Thread(new ThreadStart(f));
        Thread p = new Thread(new ThreadStart(f));
        t.Start();
        p.Start();
        t.Join();
        p.Join();
        Console.WriteLine("{0}: {1}", s, result);
    }

    static void Main(string[] args)
    {
        test(accumulate_sum_unsafe, "accumulate_sum_unsafe");
        test(accumulate_sum_safe_lock, "accumulate_sum_safe_mutex");
        test(accumulate_sum_safe_monitor, "accumulate_sum_safe_lock");
    }
}
}