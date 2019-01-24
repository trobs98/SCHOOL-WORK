/******************************************************************************
*   
*   Name: Tyler Roberts
*   simulate.c
*
******************************************************************************/

#include "simulate.h"

//Global Variables
int memory, max_memory;
FILE *fp;
linked_stack_t *jobs;
pthread_t threadArray[NUMBER_OF_THREADS];

void simulate(int memory_value, linked_stack_t *stack){

    max_memory = memory_value; //stores the total amount of memory you are allowed 
    memory = max_memory;//memory that will be changed based on the jobs that are being used
    jobs = stack;//initialize the stack global variable 
    fp = fopen(SYSTEM_OUTPUT, WRITE); //opens the output file so that you can write to it with the print functions
    

    //loops through the jobs in the stack creating threads for them and putting them in an array
    while(jobs->size > 0){
        int i, arrayCount = 0;

        //make sure that when creating the threads, that there are still jobs on the stack, memory left for the jobs and that the array is not full
        while(arrayCount < NUMBER_OF_THREADS && jobs->size > 0 && memory >= 0){
            if(pthread_create(&threadArray[arrayCount], NULL, checkJobMemory, pop(jobs)) != 0){ //pops job from stack
                printf("Error creating thread.\n");
                exit(1);
            }
            arrayCount++;
        }
        //join the threads currently in the array, and then have them run 
        for(i=0; i<arrayCount; i++){
            if(pthread_join(threadArray[i], NULL) != 0){
                printf("Error joining thread. \n");
                exit(2);
            };
            i++;  
        }
    }
}

//checks to make sure the job's memory is compatible with the memory left, or run the job
void *checkJobMemory(void *arg){ 

    job_t *newjob = (job_t*) arg;//cast the void pointer job popped from the top of the stack to a job struct pointer 

    //make sure the jobs memory requirement is less than or equal to the max memory
    if(newjob->required_memory > max_memory){
        print_exceed_memory(fp, newjob->number);
    }

    //make sure theres enough memory left for the job to run, if not push it back onto the stack for the next round of jobs
    else if((memory - (newjob->required_memory)) < 0){
        print_insufficient_memory(fp, newjob->number);
        push(jobs, ((void *) newjob)); 
    }

    //if theres enough memory left for the job, then run the job
    else{
        runJob(newjob);
    }
} 

//runs the job if theres enough memory for it
void runJob(job_t *job){

    //starts the job, updates the total memory, and then allocates it 
    print_starting(fp, job->number);
    memory = (memory - job->required_memory);
    print_allocate_memory(fp, memory, job->required_memory);

    //simulate the job running for its required time
    sleep(job->required_time);

    //finishes the job, updates the total memory and the deallocates it
    print_completed(fp, job->number);
    memory = (memory + job->required_memory);
    print_deallocate_memory(fp, memory, job->required_memory);
}
