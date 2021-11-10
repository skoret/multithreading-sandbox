// To compile: 
// clang -DSHARED_NUM=1 shared.c -o s1
// clang -DSHARED_NUM=2 shared.c -o s2

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <errno.h>
int main()
{
    int *array; 
    int shmid; 
    int new = 1;
    char pathname[] = "shared.c"; 
    key_t key; 
    key = ftok(pathname,0);
    if ((shmid = shmget(key, 3*sizeof(int),0666|IPC_CREAT|IPC_EXCL)) < 0) {
	shmid = shmget(key, 3*sizeof(int), 0);
    	new = 0;
    }
    array = (int *)shmat(shmid, NULL, 0);
    if(new){
        array[0] = 0;
        array[1] = 0;
    }
    
    array[SHARED_NUM - 1] += 1;
    
    printf("Program 1 was spawn %d times, program 2 - %d times\n",
        array[0], array[1]);
    shmdt(array);
    return 0;
}
