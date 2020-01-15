//package com.sws.base.Enum;
//
//enum LogLevel {
//    debug(4), YELLOW, RED;
//
//    LogLevel(int i) {
//
//    }
//}
//
//public class TrafficLight {
//    LogLevel color = LogLevel.RED;
//    public int getLevel(int i) {
//        switch (i) {
//            case 1:
//                return LogLevel.debug;
//            case 2:
//                color = LogLevel.RED;
//                break;
//            case 3:
//                color = LogLevel.YELLOW;
//                break;
//            case 4:
//                color = LogLevel.YELLOW;
//                break;
//        }
//    }
//}