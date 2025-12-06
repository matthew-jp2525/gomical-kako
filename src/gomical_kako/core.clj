(ns gomical-kako.core
  (:require
    [gomical-kako.google-calendar-csv-formatter :as ft]
    [gomical-kako.workbook-reader :as wr]))


(defn run
  [opts]
  (let [records (wr/do-read (get opts :workbook-path) (get opts :sheet-no))
        contents (ft/do-format records)]
    (println contents)))
