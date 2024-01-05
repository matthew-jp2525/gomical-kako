(ns gomical-kako.core
  (:require [gomical-kako.workbook-reader :as wr]
            [gomical-kako.google-calendar-csv-formatter :as ft]))

(defn run [opts]
  (let [records (wr/do-read (get opts :workbook-path) (get opts :sheet-no))
        contents (ft/do-format records)]
    (println contents)))
