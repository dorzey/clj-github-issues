(ns clj-github-issues.output-test
  (:require [clojure.test :refer :all]
            [clj-github-issues.output :refer :all :as output]))

(def issue-one {:labels [{:name "a"}] :html_url "one-url"})
(def issue-two {:labels [{:name "a"}, {:name "b"}] :html_url "two-url"})
(def issue-three {:labels [{:name "b"}, {:name "c"}] :html_url "three-url"})
(def issue-four {:labels [{:name "d"}]:html_url "four-url"})
(def group-of-issues {"a" [issue-one issue-two]
                      "b" [issue-two issue-three]
                      "c" [issue-three]
                      "d" [issue-four]})

(deftest table-pretty-issue
  (testing "full issue gets reduced to just html_url"
    (is (= "one-url"
           (#'output/table-pretty-issue issue-one)))))

(deftest table-pretty-issues
  (testing "issues get reduced to just html_urls"
    (is (= ["one-url" "two-url"]
           (doall
            (#'output/table-pretty-issues [issue-one issue-two]))))))

(deftest table-pretty-test
  (let [res  (#'output/table-pretty group-of-issues "")]
    (testing "output is ammenable tp being used by print table"
      (is (= [{"a" "one-url"} {"a" "two-url"} {"b" "two-url"} {"b" "three-url"} {"c" "three-url"} {"d" "four-url"}]
             res)))))
