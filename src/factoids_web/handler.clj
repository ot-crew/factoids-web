(ns factoids-web.handler
  (:use compojure.core)
  (:require [org.httpkit.server   :refer  [run-server]]
            [compojure.handler    :as     handler]
            [compojure.route      :as     route]
            [factoids-web.config  :refer  [config]]
            [factoids-web.routes  :refer  [app-routes]]))

(defroutes base-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def all-routes
  [app-routes base-routes])

(def app
  (handler/site (apply routes all-routes)))

(defn -main [& args]
  (run-server app {:port (config :port)}))
