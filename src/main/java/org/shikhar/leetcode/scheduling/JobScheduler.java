package com.shikhar.interviewprep.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

// https://www.hellointerview.com/community/questions/job-scheduling-implementation/cm6k0flr7000nyis4gwnbys17
public class JobScheduler {

    public enum SchedulingAlgorithm {
        FCFS, PRIORITY
    }

    public static class Job  {
        public int jobId;
        public int startTime;
        public int endTime;
        public int priority;

        public Job(int jobId, int startTime, int endTime, int priority) {
            this.jobId = jobId;
            this.startTime = startTime;
            this.endTime = endTime;
            this.priority = priority;
        }

        public int getDuration() {
            return endTime - startTime;
        }
    }

    /**
     * FCFS Scheduler Implementation
     */
    public List<Map<String, Integer>> fcfsSchedule(List<Job> jobs) {
        List<Job> sortedJobs = new ArrayList<>(jobs);
        Collections.sort(sortedJobs, Comparator.comparingInt(j -> j.startTime));

        List<Map<String, Integer>> schedule = new ArrayList<>();
        int currentTime = 0;
        for (Job job : sortedJobs) {
            currentTime = Math.max(currentTime, job.startTime);

            int finishTime = currentTime + job.getDuration();
            schedule.add(Map.of(
                "jobId", job.jobId,
                "startTime", currentTime,
                "finishTime", finishTime
            ));

             currentTime = finishTime;
        }

        return schedule;
    }

    /**
     * Priority Scheduling Implementation
     * 
     * Maintains a ready queue (min-heap by priority) of jobs that have arrived.
     * 
     * At each step, pop the highest priority job from the queue, execute it, and update currentTime.
     */
    public static List<Map<String, Integer>> prioritySchedule(List<Job> jobs) {
        List<Job> sortedJobs = new ArrayList<>(jobs);
        sortedJobs.sort(Comparator.comparingInt(j -> j.startTime));

        int currentTime = 0;
        PriorityQueue<Job> readyQueue = new PriorityQueue<>(Comparator.comparingInt(j -> j.priority));
        int jobIndex = 0;

        List<Map<String, Integer>> schedule = new ArrayList<>();
    
        while (jobIndex < sortedJobs.size() || !readyQueue.isEmpty()) {
            while (jobIndex < sortedJobs.size() && sortedJobs.get(jobIndex).startTime <= currentTime) {
                readyQueue.offer(sortedJobs.get(jobIndex++));
            }
            
            if (!readyQueue.isEmpty()) {
                Job job = readyQueue.poll();
                int finishTime = currentTime + job.getDuration();
                schedule.add(
                    Map.of("jobId", job.jobId, "startTime", currentTime, "finishTime", finishTime)
                );
                currentTime = finishTime;
            } else if (jobIndex < sortedJobs.size()) {
                currentTime = sortedJobs.get(jobIndex).startTime;
            }
        }
        return schedule;
    }

    public String schedule(List<Job> jobs, SchedulingAlgorithm algorithm) {
        List<Map<String, Integer>> scheduleResult = switch (algorithm) {
            case FCFS -> fcfsSchedule(jobs);
            case PRIORITY -> prioritySchedule(jobs);
        };

        return scheduleResult.stream()
            .map(entry -> String.format("Job %d: %d-%d", 
                entry.get("jobId"), entry.get("startTime"), entry.get("finishTime"))
            )
            .collect(Collectors.joining("\n"));
    }

    public String scheduleWithStats(List<Job> jobs, SchedulingAlgorithm algorithm) {
        Map<Integer, Job> jobMap = jobs.stream()
            .collect(Collectors.toMap(j -> j.jobId, j -> j));
        
        List<Map<String, Integer>> schedule = switch (algorithm) {
            case FCFS -> fcfsSchedule(jobs);
            case PRIORITY -> prioritySchedule(jobs);
        };
        
        return schedule.stream()
            .map(entry -> {
                Job job = jobMap.get(entry.get("jobId"));
                int waiting = entry.get("startTime") - job.startTime;
                int turnaround = entry.get("finishTime") - job.startTime;
                
                return String.format("Job %d: %d-%d (Wait: %d, Turnaround: %d)", 
                    entry.get("jobId"), entry.get("startTime"), entry.get("finishTime"), waiting, turnaround);
            }).collect(Collectors.joining("\n"));
    }
    
    public static void main(String[] args) {
        // Example usage of the Job class
        Job job1 = new Job(1, 0, 5, 2);
        Job job2 = new Job(2, 1, 4, 3);
        Job job3 = new Job(3, 2, 6, 1);

        System.out.println("FCFS Schedule:\n" + 
            new JobScheduler().scheduleWithStats(List.of(job1, job2, job3), SchedulingAlgorithm.FCFS));

        System.out.println("Priority Schedule:\n" + 
            new JobScheduler().scheduleWithStats(List.of(job1, job2, job3), SchedulingAlgorithm.PRIORITY));
    }
}
