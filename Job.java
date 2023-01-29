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
public class Job implements Comparable<Job> {
    //Instance Variables
    private String _name;
    private int _priority;
    private int _stepsRequired;

    //Accessor Methods
    public String GetName(){
        return _name;
    }
    public int GetPriority(){
        return _priority;
    }
    public int GetStepsRequired(){
        return _stepsRequired;
    }

    //Update Methods
    public void CompleteStep(){
        _stepsRequired--;
    }

    //Constructor
    public Job(int jobNumber, int priority, int stepsRequired){
        _name = "Job_" + jobNumber;
        _priority = priority;
        _stepsRequired = stepsRequired;
    }

    //Override Methods
    @Override
    public String toString(){
        return this.GetName() + " Priority: " + this.GetPriority() + " Steps Left: " + this.GetStepsRequired();
    }
    @Override
    public int compareTo(Job job){
        //Lower number priority is a higher priority ;
        if (this.GetPriority() > job.GetPriority()){
            return 1;
        } else if (this.GetPriority() == job.GetPriority()){
            return 0;
        } else {
            return -1;
        }
    }

}
