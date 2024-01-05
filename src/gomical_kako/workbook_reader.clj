(ns gomical-kako.workbook-reader
  (:require [dk.ative.docjure.spreadsheet :as ss]
            [java-time.api :as jt]))

(def rows [{:year 2024, :month 1, :row-number 16}
           {:year 2024, :month 2, :row-number 17}
           {:year 2024, :month 3, :row-number 18}
           {:year 2024, :month 4, :row-number 19}
           {:year 2024, :month 5, :row-number 20}
           {:year 2024, :month 6, :row-number 21}
           {:year 2024, :month 7, :row-number 22}
           {:year 2024, :month 8, :row-number 23}
           {:year 2024, :month 9, :row-number 24}
           {:year 2024, :month 10, :row-number 25}
           {:year 2024, :month 11, :row-number 26}
           {:year 2024, :month 12, :row-number 27}])

(def columns [{:category "燃やさないごみ", :column-numbers ["C"]}
              {:category "かん", :column-numbers ["D"]}
              {:category "びん", :column-numbers ["E"]}
              {:category "剪定枝・草", :column-numbers ["F" "G"]}
              {:category "ペットボトル・衣類", :column-numbers ["H"]}
              {:category "紙類", :column-numbers ["I" "J"]}
              {:category "蛍光灯・乾電池・ライター", :column-numbers ["K"]}])

(defn get-selectors []
  (for [row rows
        column columns
        column-number (get column :column-numbers)]
    (merge row (dissoc column :column-numbers) {:column-number column-number})))

(defn read-cell-value [selector sheet]
  (let [cell-position (format "%s%s" (get selector :column-number) (str (get selector :row-number)))
        cell (ss/select-cell cell-position sheet)]
    (ss/read-cell cell)))

(defmulti parse-cell class)

(defmethod parse-cell String [s]
  (Integer/parseInt s))

(defmethod parse-cell Double [d]
  (int d))

(defn do-read [workbook-path sheet-no]
  (let [workbook (ss/load-workbook workbook-path)
        sheet (ss/select-sheet (str sheet-no) workbook)]
    (for [selector (get-selectors)
          :let [cell-value (read-cell-value selector sheet)]
          :when (some? cell-value)]
      (merge selector {:day (parse-cell cell-value)}))))
