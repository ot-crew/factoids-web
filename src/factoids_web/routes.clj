(ns factoids-web.routes
  (:use compojure.core)
  (:require [compojure.route :as route]
            [factoids-web.config :refer [config]]
            [factoids-web.css :as css]
            [factoids-web.views :refer [render-view]]
            [ring.util.response :as response]
            [taoensso.carmine :as car :refer (wcar)]))

(defmacro wcar* [& body] `(car/wcar (config :carmine) ~@body))

; From John Gruber's http://daringfireball.net/2010/07/improved_regex_for_matching_urls
; Added a capture group around the protocol so we can distinguish matches that contained it.
(def url-regex #"(?i)\b((?:([a-z][\w-]+:(?:/{1,3}|[a-z0-9%]))|www\d{0,3}[.]|[a-z0-9.\-]+[.][a-z]{2,4}/)(?:[^\s()<>]+|\(([^\s()<>]+|(\([^\s()<>]+\)))*\))+(?:\(([^\s()<>]+|(\([^\s()<>]+\)))*\)|[^\s`!()\[\]{};:'\".,<>?«»“”‘’]))")
 
(defn linkify-urls
  "Add A tags around parts of the string that look likely to be URLs."
  [text]
  (let [matcher (re-matcher url-regex text)]
    (if-not (.find matcher)
            text ; avoid building a StringBuffer (and presumably copying the string) if there were no matches
            (let [result (StringBuffer.)]
              (loop []
                (if (.group matcher 2)
                    (.appendReplacement matcher result "<a class=\"detected-link\" href=\"$1\">$1</a>")
                    ; If there was no protocol, we need to add one so the browser won't treat this as a relative link.
                    (.appendReplacement matcher result "<a class=\"detected-link\" href=\"http://$1\">$1</a>"))
                (if (.find matcher)
                    (recur)
                    (.toString (.appendTail matcher result))))))))

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
                                     [:td.factoid (linkify-urls (last factoid))]])
                      (sort-by first (factoids)))]]))

;; Routes
(defroutes app-routes
  (context "" {params :params :as request}
   (GET "/css/styles.css" [] {:status 200 :headers {"Content-Type" "text/css; charset=utf-8"} :body css/styles})
   (GET "/"               [] (response/redirect "/factoids"))
   (GET "/factoids"       [] (factoids-index request params))))
