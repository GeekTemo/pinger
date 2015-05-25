(ns pinger.core
  (:gen-class)
  (:import (java.net URL HttpURLConnection))
  (:require [pinger.scheduler :as scheduler]
            [pinger.config :as config]
            [clojure.tools.logging :as logger]))


(defn response-code [address]
  (let [conn ^HttpURLConnection (.openConnection (URL. address))
        code (.getResponseCode conn)]
    (when (< code 400)
      (-> conn .getInputStream .close))
    code
    ))


(defn available? [address]
  (= 200 (response-code address)))

(defn record-availability [address]
  (if (available? address)
    (logger/info (str address " is responding normally"))
    (logger/error (str address " is not available"))))


(defn check []
  (let [addresses '("http://www.google.com"
                     "http://www.so.com"
                     "http://www.google.com/badurl")]
    (doseq [address addresses]
      (record-availability address))))

(defn check []
  (doseq [address (config/urls (config/config))]
    (record-availability address)))

(def immediately 0)

(def every-time (* 60 1000))

(defn start [e]
  "REPL helper. Start pinger on executor e."
  (scheduler/periodically e check
                          :initial-delay immediately
                          :delay every-time))

(defn stop [e]
  "REPL helper. Stop executor e."
  (scheduler/shutdown-executor e))


(defn -main []
  (start (scheduler/scheduled-executor 1)))

(-main)
