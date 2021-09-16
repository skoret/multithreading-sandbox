using System;
using System.Threading;
using System.Threading.Tasks;

namespace tasks
{
    class Program
    {
        static void Main(string[] args)
        {
            var t = Task<int>.Factory.StartNew( () => {
                Thread.Sleep(1000);
                return 5;
            } );
            Console.WriteLine("Just after starting task");
            Console.WriteLine("Result is: {0}", t.Result);
        }
    }
}