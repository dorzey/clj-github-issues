(defproject clj-github-issues "0.1.0-SNAPSHOT"
  :description "Helper for GitHub issues"
  :url "https://github.com/dorzey/clj-github-issues"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"
            :year 2015
            :key "mit"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/algo.generic "0.1.2"]
                 [tentacles "0.3.0"]]
  :main ^:skip-aot clj-github-issues.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:resource-paths ["resources/dev"]}
             :prod {:resource-paths ["resources/prod"]}})
