(ns xml-to-json.core-test
  (:require [clojure.test :refer :all]
            [xml-to-json.core :refer :all]
            [clojure.data.xml :refer :all]
            [clojure.java.io :as io]))

(def test-xml (parse-str (slurp "resources/sample-feed.xml")))

(deftest exists-test
  (testing "Checks if file existes"
    (is (.exists (io/file "resources/sample-feed.xml")))))

(deftest read-file-to-string
  (testing "clojure reading capabilities"
    (def xml-string (slurp "resources/sample-feed.xml"))
    (is (= (compare xml-string "<>") 1))))

(deftest transforms-string-to-xml
  (testing "parses xml to clj format"
    (is (clojure.lang.APersistentMap/mapEquals (parser "resources/sample-feed.xml")
    test-xml ))))

(deftest test-if-tag-is-mapped
  (testing "Tests if parser is reading correctly the first tag"
    (is (= (:tag (parser "resources/sample-feed.xml")) :rss))
))

(deftest test-tag-content
  (testing "Tests if parser is reading correctly the first tag's content"
    (is (= (:tag :rss (:attrs (parser "resources/sample-feed.xml"))) {:version "2.0"}))
))

(deftest tests-clj-mapping-of-xml
  (testing "Maps the xml as a clj obj"
    (is (= (:tag (:tag :rss (:content  parsed-xml)) :channel)))
  ))
