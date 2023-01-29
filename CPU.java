import java.util.PriorityQueue;
import java.util.Random;

//Autumn Clark
//CS 2235
//Dr. Kirby
//CPU_HW6_Clark
/*
Note: The project description says you may assume that CPU jobs must be finished before moving to another job
    that has a higher priority. I am choosing to make this more complicated and am allowing the CPU to swap to working
    on another job, before the current job is finished, is one with a higher priority enters the queue.
    Dr. Kirby okay'd this.
 */
public class CPU {
    //Instance Variables
    private int _timeSlices; //Int to track the total number of time slices passed
    private int _jobNumber; //Int to track what job number the next job will be ; Starts at 1
    private PriorityQueue<Job> _jobs;

    //Accessor Methods
    public int GetJobNumber(){
        return _jobNumber;
    }

    //Update Methods
    public void IncrementJobNumber(){
        _jobNumber++;
    }

    //Constructor
    public CPU(){
        Initialize();
        StartJobs();
        EndReport();
    }

    //Methods
    public void Initialize(){
        _jobNumber = 1; //Start job numbers at 1
        _jobs = new PriorityQueue<>(); //Initialize _jobs
        AddJob(10);
    }

    public void StartJobs(){
        while (!_jobs.isEmpty()){
            CompleteStep();
            if (_timeSlices % 100 == 0 && _timeSlices != 0){
                //Add a new job to PQ every 100 time slices
                AddJob();
                System.out.println("Job added. Time slices passed: " + _timeSlices);
            }
        }
    }

    public void CompleteStep(){
        //Complete a step for the root, i.e. object with the highest priority
        System.out.println(_jobs.peek());
        _jobs.peek().CompleteStep();
        if (_jobs.peek().GetStepsRequired() == 0){
            //If the job has no more steps to be completed, remove it from PQ
            _jobs.remove();
            System.out.println("Job has been completed.");
        }
        _timeSlices++; //Increment time slices
    }

    //Overloaded method that adds a specified number of CPU jobs
    public void AddJob(int numOfJobs){
        Random rand = new Random(System.currentTimeMillis());
        //Add specified number of new jobs to _jobs
        for (int i = 0; i < numOfJobs; i++){
            //Random bounds are 0 (inclusive) - bound (exclusive) ; Add one to each number to make bounds inclusively 1 - bound
            _jobs.add(new Job(GetJobNumber(), rand.nextInt(20) + 1, rand.nextInt(100) + 1));
            IncrementJobNumber(); //Increment job number
        }
    }

    //Overloaded method that adds one new CPU job
    public void AddJob(){
        Random rand = new Random(System.currentTimeMillis());
        //Random bounds are 0 (inclusive) - bound (exclusive) ; Add one to each number to make bounds inclusively 1 - bound
        _jobs.add(new Job(GetJobNumber(), rand.nextInt(20) + 1, rand.nextInt(100) + 1));
        IncrementJobNumber(); //Increment job number
    }

    public void EndReport(){
        System.out.println("All jobs completed.\nTotal number of time slices passed: " + _timeSlices
        + "\nJobs completed: " + (GetJobNumber() - 1));

    }

    public static void main(String[] args){
        CPU simulation = new CPU();
    }
}
