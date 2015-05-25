(ns pinger.scheduler
  (:import (java.util.concurrent ScheduledThreadPoolExecutor ScheduledFuture TimeUnit ThreadPoolExecutor)))

(defn scheduled-executor
  "Create a scheduler executor."
  ^ScheduledThreadPoolExecutor [threads]
  (ScheduledThreadPoolExecutor. threads))

(defn periodically
  "Schedules function f to run on executor every 'delay'
   millisecnonds after a delay of 'initial-delay'
   Returns a ScheduledFutures"
  ^ScheduledFuture [e f & {:keys [initial-delay delay]}]
  (.scheduleWithFixedDelay
    e f
    initial-delay delay
    TimeUnit/MILLISECONDS))

(defn shutdown-executor
  "Shutdown an executor."
  [^ThreadPoolExecutor e]
  (.shutdown e))