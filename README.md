GomiCal Kako
============

加古川市のごみ・資源物収集日程表を元にGoogleカレンダーに取り込めるCSV形式のデータを出力するツールです。

# 動作要件

* Clojure
* Clojure CLI

# 使い方

```
clj -X gomical-kako.core/run :workbook-path '"{path to workbook file}"' :sheet-no {sheet-no} 
```

# 利用例

```
clj -X gomical-kako.core/run :workbook-path '"/Users/foo/Downloads/gomiR06E.xlsx"' :sheet-no 3 
```
