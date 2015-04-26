(ns clj-github-issues.core
  (:gen-class)
  (:use [clj-github-issues.config :as config]
        [clj-github-issues.api :as api]
        [clj-github-issues.group :as group]
        [clj-github-issues.output :as output]))

(defn -main
  [& args]
  (let [public-config (clojure.java.io/resource "config-public.edn")
        private-config (clojure.java.io/resource "config-private.edn")
        loaded-config (config/load-and-merge public-config private-config)
        user (:user loaded-config)
        repos (:repo loaded-config)
        labels (:labels loaded-config)
        oauth-token (:oauth-token loaded-config)
        type {:Type (first args)}
        issues (flatten (map #(api/get-issues type user % oauth-token) repos))
        grouped-issues (group/group-issues-by issues labels)]
    (output/print-issues labels grouped-issues user)
    ))
