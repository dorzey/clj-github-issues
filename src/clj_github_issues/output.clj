(ns clj-github-issues.output
  (:use [clojure.pprint]
        [clojure.algo.generic.functor]))

(defn- table-pretty-issue [issue]
  (:html_url issue))

(defn- table-pretty-issues [issues]
  (map table-pretty-issue issues))

(defn flatten-value-lists [entry]
  (into []
        (for [a-key (keys entry)]
          (for [value (get entry a-key)]
            {a-key value}))))

(defn- table-pretty [issue-groups]
  (flatten
   (flatten-value-lists (fmap table-pretty-issues issue-groups))))

(defn print-issues [labels issue-groups]
  (print-table labels (table-pretty issue-groups)))
