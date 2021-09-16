using System;
using System.Threading;

namespace condition_variable
{
    class Program
    {
        private static object m = new object();
        static String data;
        static bool ready = false;
        static bool processed = false;

        static void worker_thread()
        {
            lock (m)
            {
                while (!ready)
                {
                    Monitor.Wait(m);
                }
                
                Console.WriteLine("Worker thread is processing data");
                data += " after processing";

                processed = true;
                Console.WriteLine("Worker thread signals data processing completed");
            }

            lock (m)
            {
                Monitor.Pulse(m);
            }
        }
        
        static void Main(string[] args)
        {
            Thread worker = new Thread(worker_thread);
            worker.Start();

            data = "Example data";
            {
                lock (m)
                {
                    ready = true;
                    Console.WriteLine("main() signals data ready for processing");
                }
            }
            
            lock (m)
            {
                Monitor.Pulse(m);
            }
            
            {
                lock (m)
                {
                    while (!processed)
                    {
                        Monitor.Wait(m);
                    }
                }
            }
            Console.WriteLine("Back in main(), data = {0}", data);
            worker.Join();
        }
    }
}