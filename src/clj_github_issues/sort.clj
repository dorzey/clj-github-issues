(ns clj-github-issues.sort)

(defn- get-labels [issue]
  (get-in issue [:labels]))

(defn- match? [label issue-label]
  (= label issue-label))

(defn- has? [issue label]
  (let [labels (get-labels issue)]
    (some #(match? label (get-in % [:name])) labels)))

(defn- return-if-has [issue label]
  (if (has? issue label) issue))

(defn sort-issues-by [issues labels]
  (for [label labels]
    (filter identity (map #(return-if-has % label) issues))))
