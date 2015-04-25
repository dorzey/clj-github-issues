(ns clj-github-issues.group-test
  (:require [clojure.test :refer :all]
            [clj-github-issues.group :refer :all :as sort]))

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
                     {:labels [{:name "d"}]}])

(deftest group-issues-by-test
  (let [ret (sort/group-issues-by list-of-issues ["a" "b" "c" "d"])]
    (testing "the 'a' group has two"
      (is (= 2 (count (get ret "a")))))
    (testing "the 'b' group has two"
      (is (= 2 (count (get ret "b")))))
    (testing "the 'c' group has one"
      (is (= 1 (count (get ret "c")))))
    (testing "the 'd' group has one"
      (is (= 1 (count (get ret "d")))))))
      
