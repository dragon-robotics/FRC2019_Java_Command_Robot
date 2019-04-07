/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.libraries;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class will be used to calculate the rolling averages.
 */
public class MovingAverage {
    private int array_size;
    private Queue<Double> moving_avg_queue;

    public MovingAverage(int size){
        array_size = size;
        moving_avg_queue = new LinkedList<>();
    }

    public void addToMovingAverage(double value){
        if(moving_avg_queue.size() < array_size){
            moving_avg_queue.add(value);
        }
        else{
            moving_avg_queue.remove();
            moving_avg_queue.add(value);
        }
    }

    public double getMovingAverage(){
        double moving_sum = 0;
        for(double value : moving_avg_queue){
            moving_sum += value;
        }
        double moving_avg = moving_sum / moving_avg_queue.size();
        return moving_avg;
    }
}
