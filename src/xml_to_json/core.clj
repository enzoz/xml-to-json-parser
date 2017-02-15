(ns xml-to-json.core
  (:require [clojure.java.io :as io]
            [clojure.data.xml :refer :all]
            [clojure.data.json :as json])
  (:gen-class))

(defn parser
  [path]
  (parse-str (slurp path)))

(def parsed-xml "resources/sample-feed.xml")

(defn convert-line-to-json
  [xml]
  (json/write-str (sorted-map (:tag xml) (:tag :rss (:attrs xml)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (element :rss parsed-xml)))
