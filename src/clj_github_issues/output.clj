(ns clj-github-issues.output
  (:use [clojure.pprint]
        [clojure.algo.generic.functor]))

(defn- table-pretty-issue [issue]
  (:html_url issue))

(defn- table-pretty-issues [issues]
  (map table-pretty-issue issues))

(defn- table-pretty [issue-groups]
  (fmap table-pretty-issues issue-groups))

(defn print-issues [issue-groups]
  (print-table [(table-pretty issue-groups)]))
