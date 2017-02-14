(ns xml-to-json.core-test
  (:require [clojure.test :refer :all]
            [xml-to-json.core :refer :all]
            [clojure.java.io :as io]))

(deftest exists-test
  (testing "Checks if file existes"
    (is (.exists (io/file "resources/sample-feed.xml")))))

(deftest read-file-to-string
  (testing "clojure reading capabilities"
    (def xml-string (slurp "resources/sample-feed.xml"))
    (is (= (compare xml-string "<>") 1))))

(deftest saves-xml-to-def
  (testing "Saves xml file to an string"
    (is (not (empty? (read-xml "resources/sample-feed.xml"))))))
