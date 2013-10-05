(ns factoids-web.routes
  (:use compojure.core)
  (:require [compojure.route      :as route]
            [factoids-web.config            :refer [config]]
            [factoids-web.views             :refer [render-view]]
            [taoensso.carmine     :as car   :refer (wcar)]))

(defmacro wcar* [& body] `(car/wcar (config :carmine) ~@body))

(defn factoids []
  (wcar* (car/hgetall* (config :factoid-key))))

(defn factoids-index [request params]
  (render-view [:table
                [:thead
                 [:tr
                  [:th "Trigger"]
                  [:th "Factoid"]]]
                [:tbody
                 (map (fn [factoid] [:tr
                                     [:td.trigger (first factoid)]
                                     [:td.factoid (last factoid)]])
                      (sort-by first (factoids)))]]))

(defroutes app-routes
  (context "" {params :params :as request}
   (GET "/"         [] "Hello World")
   (GET "/factoids" [] (factoids-index request params))))
