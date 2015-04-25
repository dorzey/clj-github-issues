(ns clj-github-issues.sort-test
  (:require [clojure.test :refer :all]
            [clj-github-issues.sort :refer :all :as sort]))

(deftest get-labels-test
  (testing "getting labels at top level"
    (is (= [:a :b :c]
           (#'sort/get-labels {:labels [:a :b :c]})))))

(deftest match?-test
  (testing "true when labels match"
    (is (true? (#'sort/match? "one" "one"))))
  (testing "false when labels don't match"
    (is (false? (#'sort/match? "one" "two")))))

(def simple-issue {:labels [{:name "a"}]})

(deftest has?-test
  (testing "true when issue has label"
    (is (true? (#'sort/has?  simple-issue "a"))))
  (testing "false when issue does not have label"
    (is (nil? (#'sort/has? simple-issue "b")))))

(deftest return-if-has-issue
  (testing "issue is return if it has label"
    (is (= simple-issue (#'sort/return-if-has simple-issue "a"))))
  (testing "issue not returned if it does not have label"
    (is (nil? (#'sort/return-if-has simple-issue "b")))))

(def list-of-issues [{:labels [{:name "a"}]}
                     {:labels [{:name "a"}, {:name "b"}]}
                     {:labels [{:name "b"}, {:name "c"}]}
                     {:labels [{:name "c"}]}])

(deftest sort-issues-by-test
  (testing "get three groups when give three labels"
    (is (= 3
           (count (sort/sort-issues-by list-of-issues ["a" "b" "c"])))))
  (testing "get one group when give one group"
    (is (= 1
           (count (sort/sort-issues-by list-of-issues ["a"]))))))
