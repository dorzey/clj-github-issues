(ns clj-github-issues.config-test
  (:require [clojure.test :refer :all]
            [clj-github-issues.config :refer :all]))

(def public-config (clojure.java.io/resource "config-public.edn"))

(def private-config (clojure.java.io/resource "config-private.edn"))

(deftest load-and-merge-public-and-private-config
  (testing
    (is (= 4
           (count (load-and-merge public-config private-config))))))

(deftest deep-merge-test
  (testing "when maps contain disjoint keys"
    (is (= {:one 1 :two 2 :three 3 :four 4}
           (deep-merge {:one 1 :two 2} {:three 3 :four 4}))))
  (testing "when maps contain intersecting keys"
    (is (= {:one 1 :two 2 :three 3}
           (deep-merge {:one 1 :two 2} {:two 2 :three 3})))))
