(ns clj-github-issues.api
  (:use [tentacles.issues :as issues]))

(defn- get-repo-issues [user repo oauth-token]
  (issues/issues user repo {:oauth-token oauth-token}))

(defn- get-repo-prs [user repo oauth-token]
  (filter :pull_request (get-repo-issues user repo oauth-token)))

(defmulti get-issues (fn[x user repo oauth-token] [(:Type x)]))
(defmethod get-issues ["issue"] [x user repo oauth-token]
  (get-repo-issues user repo oauth-token))
(defmethod get-issues ["pr"] [x user repo oauth-token]
  (get-repo-prs user repo oauth-token))
