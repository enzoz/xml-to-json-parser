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
  (json/write-str
    (sorted-map (:tag xml) (:tag :rss (:attrs xml)))
  ))

(defn mod-key
  [keyword]
  (str "\"" (name keyword) "\" : "))

(defn different-keys? [content]
      (when content
        (let [diffkeys (count (filter identity (distinct (map :tag content))))
              n (count content)]
          (= diffkeys n))))

(defn xml->json [element]
  (cond
    (nil? element) nil
    (string? element) (str "\"" element "\"")
    (sequential? element) (if (> (count element) 1)
                           (if (different-keys? element)
                             (reduce into {} (map (partial xml->json ) element))
                             (map xml->json element))
                           (xml->json  (first element)))
    (and (map? element) (empty? element)) {}
    (map? element) (if (:attrs element)
                    {(mod-key (:tag element)) (xml->json (:content element))
                    (keyword (mod-key (:tag element))) (:attrs element)}
                    {(mod-key (:tag element)) (xml->json  (:content element))})
    :else nil))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (xml->json (parser "resources/sample-feed.xml"))))
