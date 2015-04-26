(ns clj-github-issues.output
  (:refer-clojure :exclude [reverse, replace])
  (:use [clojure.pprint]
        [clojure.string :as string :only [replace]]
        [clojure.algo.generic.functor]))

(defn- table-pretty-issue [issue]
  (:html_url issue))

(defn- table-pretty-issues [issues]
  (map table-pretty-issue issues))

(defn- tidy-url [url user]
  (string/replace
   (string/replace url "https://github.com/" "")
   user ""))

(defn- flatten-value-lists [entry user]
  (vec
   (for [a-key (keys entry)]
     (for [value (get entry a-key)]
       {a-key (tidy-url value user)}))))

(defn- table-pretty [issue-groups user]
  (flatten
   (flatten-value-lists (fmap table-pretty-issues issue-groups) user)))

(defn print-issues [labels issue-groups user]
  (print-table labels (table-pretty issue-groups user)))
