(ns xml-to-json.core
  (:require [clojure.java.io :as io]
            [clojure.data.xml :refer :all])
  (:gen-class))

(defn parser
  [path]
  (parse-str (slurp path)))

(def parsed-xml (parser "resources/sample-feed.xml"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (element :rss parsed-xml)))
