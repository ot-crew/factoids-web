(ns factoids-web.handler
  (:use compojure.core)
  (:require [compojure.handler    :as     handler]
            [compojure.route      :as     route]
            [factoids-web.routes  :refer  [app-routes]]))

(defroutes base-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def all-routes
  [app-routes base-routes])

(def app
  (handler/site (apply routes all-routes)))
