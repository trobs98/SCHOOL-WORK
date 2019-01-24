/******************************************************************************
* 
* Name: 	Tyler Roberts
*
* scheduler.c 
*
******************************************************************************/

#include "scheduler.h"

job_t *get_next_job(int mode, d_linked_list_t* jobs) {
	job_t *j;
	
	//treat like queue
	if (mode == FCFS){
		j=(job_t*) dequeue(jobs);
	}
	
	//treat like stack
	else if(mode == LIFO){
		j=(job_t*) pop(jobs);
	}

	//
	else if(mode == SJF){

		//smallest is the place holder for the job with the smallest required time
		job_t *smallest = (job_t*)dequeue(jobs),*current;
		int i;
		
		//loop through all the jobs comparing their required times looking for the smallest time 
		for(i=0; i < jobs->size; i++){
			//current job we are looking at to compare to the current smallest job 
			current = (job_t*) dequeue(jobs);
			
			//if the current jobs time is less than the current smallest jobs time, then make the current job
			//the smallest job and enqueue the previous smallest job
			if(current->required_time < smallest->required_time){
				enqueue(jobs, smallest);
				smallest = current;
			}

			//if the current jobs time is NOT less than the current smallest jobs time, then enqueue the current job
			else{
				enqueue(jobs, current);
			}
		}

		j = smallest;
	}

	//cannot access time quantum in scheduler, thus treat Round Robin like FCFS and deal with time quantum in simulate
	else if(mode == RR){
		j=(job_t*) dequeue(jobs);
	}

	return j;
}
