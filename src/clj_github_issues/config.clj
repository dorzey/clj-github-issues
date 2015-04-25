(ns clj-github-issues.config
   (:require [clojure.edn :as edn]))

(defn deep-merge
  [& values]
  (if (every? map? values)
    (apply merge-with deep-merge values)
    (last values)))

(defn load-and-merge
  [& filenames]
  (reduce deep-merge (map (comp edn/read-string slurp) filenames)))
