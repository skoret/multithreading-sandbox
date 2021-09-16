using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading;

namespace ConsoleApp1
{
    class Program
    {
        
        const Int32 max_number = (int)1e8;

        static Int64 sum(Int32 start, Int32 finish) {
            Int64 result = 0;
            for (Int32 i = start; i < finish; i++) {
                result += i;
            }
            return result;
        }

        static void with_threads()
        {
            var threads = new List<Thread>();
            const int division = 8;
            var results = new Int64[division + 1];
            const Int32 step = max_number / division;

            for (Int32 i = 0; i * step < max_number; i++)
            {
                int x = i;
                Thread thread = new Thread(() => {
                    Int32 start = x * step;
                    Int32 finish = Math.Min((x + 1) * step, max_number);
                    results[x] = sum(start, finish);
                });
                thread.Start();
                threads.Add(thread);
            }
            foreach (var thread in threads) {
                thread.Join();
            }
            Int64 result = 0;
            foreach (var x in results) {
                result += x;
            }
            Console.WriteLine(String.Format("Sum is: {0}", result));
        }

        static void without_threads() {
            Console.WriteLine(String.Format("Sum is: {0}", sum(0, max_number)));
        }
        
        public delegate void Del();
        static void with_clock(Del function, String caption) {
            Console.WriteLine(caption);
            Stopwatch stopwatch = Stopwatch.StartNew();
            function();
            stopwatch.Stop();
            Console.WriteLine(String.Format("Milliseconds: {0}", stopwatch.ElapsedMilliseconds));
        }

        static void Main(string[] args)
        {
            with_clock(without_threads, "Without threads");
            with_clock(with_threads, "With threads");
        }
    }
}