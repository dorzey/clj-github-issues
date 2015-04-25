(ns clj-github-issues.core
  (:gen-class)
  (:use [clj-github-issues.config :as config]
        [clj-github-issues.api :as api]
        [clj-github-issues.sort :as sort]))

(defn -main
  [& args]
  (let [public-config (clojure.java.io/resource "config-public.edn")
        private-config (clojure.java.io/resource "config-private.edn")
        loaded-config (config/load-and-merge public-config private-config)
        user (:user loaded-config)
        repo (:repo loaded-config)
        labels (:labels loaded-config)
        oauth-token (:oauth-token loaded-config)
        issues (api/get-repo-issues user repo oauth-token)
        sorted-issues (sort/sort-issues-by issues labels)]
    (println sorted-issues)
    ))
