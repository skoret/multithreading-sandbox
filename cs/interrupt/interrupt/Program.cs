using System;
using System.Threading;

namespace interrupt
{
    class Program
    {
        
        static void wait(int seconds)
        {
            Thread.Sleep(seconds * 1000);
        }

        static void thread()
        {
            try
            {
                for (int i = 0; i < 5; ++i)
                {
                    wait(1);
                    Console.WriteLine("{0}", i);
                }
            }
            catch(ThreadInterruptedException e) {}
        }

        static void Main(string[] args)
        {
            Thread t = new Thread(thread);
            t.Start();
            wait(3);
            t.Interrupt();
            t.Join();
        }
    }
}