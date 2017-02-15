(ns xml-to-json.core
  (:require [clojure.java.io :as io]
            [clojure.data.xml :refer :all])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello WOrld"))

(defn parser
  [path]
  (parse-str (slurp path)))
