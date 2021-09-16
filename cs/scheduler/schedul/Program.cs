using System;
using System.Threading;

namespace schedul
{
    class Program
    {
        static void Main(string[] args)
        {
            int num = 0; 
            
            TimerCallback tm = new TimerCallback((object Object) =>
            {
                Console.WriteLine("Hello timer!!!");
            });

            Timer timer = new Timer(tm, num, 0, 1000);
 
            Thread.Sleep(5000);        
        }
    }
}