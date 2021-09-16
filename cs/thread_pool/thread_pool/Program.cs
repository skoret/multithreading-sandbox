using System;
using System.Threading;

namespace thread_pool
{
    class Program
    {
        static void Main(string[] args)
        {
            var a = new int[100];
            for (int i = 0; i < 100; i++)
            {
                int x = i;
                ThreadPool.QueueUserWorkItem((Object stateInfo) => { a[x] = x * x; });
            }
            Console.WriteLine("{0}", a[50]);     
        }
    }
}