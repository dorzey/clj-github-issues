(ns clj-github-issues.handler
  (:use [compojure.core]
        [clj-github-issues.api :as api])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [ring.middleware.json :as middleware]))

(defroutes app-routes
  (GET "/pr" [] (response {:type "pr"}))
  (GET "/issue" [] (response {:type "issue"}))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-response)))
