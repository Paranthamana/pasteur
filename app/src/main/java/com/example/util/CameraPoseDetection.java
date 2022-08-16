package com.example.util;

import android.util.Log;

public class CameraPoseDetection {

    private static void printpoint(float[][] points) {
        for (int i = 0; i < points[0].length; i++) {
            Log.d("msg-1", points[0][i] + "," + points[1][i]);
        }
    }

    public static boolean checkpoint(float pose1, float... poses) {
        if (pose1 == 0) {
            return false;
        }
        float startpose1 = pose1 - Constants.Different;
        float endpose = pose1 + Constants.Different;
        for (int i = 0; i < poses.length; i++) {
            if (!(startpose1 <= poses[i] && endpose >= poses[i])) {
                return false;
            }
        }
        return true;
    }

    //pose 1 validation points with man
    public static boolean Pose1Detecttion(float[][] modelDataPoints) {
        if (!checkpoint(modelDataPoints[0][0], modelDataPoints[0][1]))
            return false;

        if (checkpoint(modelDataPoints[0][0], modelDataPoints[0][2])) {
            if (checkpoint(modelDataPoints[1][2], modelDataPoints[1][3],
                    modelDataPoints[1][4]) || checkpoint(modelDataPoints[1][5], modelDataPoints[1][6],
                    modelDataPoints[1][7])) {

                return true;
            }
        }
        return false;
    }

    //pose 2 validation points with man
    public static boolean Pose2Detecttion(float[][] modelDataPoints) {
        if (!checkpoint(modelDataPoints[0][0], modelDataPoints[0][1]))
            return false;
        if (Pose1Detecttion(modelDataPoints)) return false;
        if (checkpoint(modelDataPoints[0][0], modelDataPoints[0][2]) ||
                checkpoint(modelDataPoints[0][0], modelDataPoints[0][5])) {
            if (checkpoint(modelDataPoints[0][0], modelDataPoints[0][3], modelDataPoints[0][4]) ||
                    checkpoint(modelDataPoints[0][0], modelDataPoints[0][6], modelDataPoints[0][7])) {
                //--- pose 2 image
                return true;
            }
        }
        return false;
    }

    //pose 3 validation points with man
    public static boolean Pose4Detecttion(float[][] modelDataPoints) {
        if (!checkpoint(modelDataPoints[0][0], modelDataPoints[0][1]))
            return false;
        if (checkpoint(modelDataPoints[1][2], modelDataPoints[1][5])) {
            if (Math.abs(modelDataPoints[0][4] - modelDataPoints[0][7]) < 100) return false;

            //printpoint(modelDataPoints);
            if (checkpoint(modelDataPoints[1][4], modelDataPoints[1][7], modelDataPoints[1][8],
                    modelDataPoints[1][11])) {
                //pose 3 image
                return true;
            }

        }
        return false;
    }

    //pose 4 validation points with man
    public static boolean Pose3Detecttion(float[][] modelDataPoints) {
        if (!checkpoint(modelDataPoints[0][0], modelDataPoints[0][1]))
            return false;
        if (checkpoint(modelDataPoints[1][2], modelDataPoints[1][5])) {
            if (checkpoint(modelDataPoints[1][2], modelDataPoints[1][3],
                    modelDataPoints[1][4]) && checkpoint(modelDataPoints[1][5],
                    modelDataPoints[1][6], modelDataPoints[1][7])) {
                //pose 4 image

                return true;
            }
        }
        return false;
    }
}
