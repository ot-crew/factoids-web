(ns factoids-web.routes
  (:use compojure.core)
  (:require [compojure.route :as route]
            [factoids-web.config :refer [config]]
            [factoids-web.css :as css]
            [factoids-web.views :refer [render-view]]
            [ring.util.response :as response]
            [taoensso.carmine :as car :refer (wcar)]))

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
                 (map (fn [factoid] [:tr {:id (str "factoid-" (clojure.string/replace (first factoid) #"[^\w\d_:-]" ""))}
                                     [:td.trigger (first factoid)]
                                     [:td.factoid (last factoid)]])
                      (sort-by first (factoids)))]]))

;; Routes
(defroutes app-routes
  (context "" {params :params :as request}
   (GET "/css/styles.css" [] {:status 200 :headers {"Content-Type" "text/css; charset=utf-8"} :body css/styles})
   (GET "/"               [] (response/redirect "/factoids"))
   (GET "/factoids"       [] (factoids-index request params))))
