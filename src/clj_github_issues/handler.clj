(ns clj-github-issues.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "Hello World 2")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
