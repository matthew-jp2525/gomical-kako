(ns gomical-kako.google-calendar-csv-formatter
  (:require [java-time.api :as jt]))

(def header-record "Subject,Start date,Start time")

(defn data-record-for [record]
  (let [subject (get record :category)
        start-date (jt/format "MM/dd/YYYY"
                              (jt/local-date (get record :year)
                                             (get record :month)
                                             (get record :day)))
        start-time "5:00 AM"]
    (format "%s,%s,%s" subject start-date start-time)))

(defn do-format [records]
  (clojure.string/join "\n"
                       [header-record
                        (clojure.string/join "\n"
                                             (map data-record-for records))]))
