#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

int main(){
    pid_t pid;
    int a;
    a=89;
    printf("Before %d\n",a);
    pid=fork();
    printf("----------\n");
    if(pid==-1){
       printf("Error \n");
       return 1;
    }
    printf("After %d\n",a);
    printf("Current %d\n",getpid());
    
    if(!pid){
       printf("Parent %d\n",getppid());
    }else{
       printf("Child %d\n", pid);
       waitpid(pid, NULL, 0);
    }
    return 0;
}
