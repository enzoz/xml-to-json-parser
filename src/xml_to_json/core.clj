(ns xml-to-json.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello WOrld"))

(defn read-xml
  [path]
  (slurp path))
