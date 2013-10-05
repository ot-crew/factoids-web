(ns factoids-web.views
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [html5]]))

(defn layout [body]
  (html5 {:lang "en-CA"}
   [:head
    [:link {:href "/favicon.ico" :rel "shortcut icon"}]
    [:title "Factoids"]
    [:meta {:charset "utf-8"}]
    [:meta {:content "IE=edge,chrome=1" :http-equiv "X-UA-Compatible"}]
    [:meta {:content "width=device-width, initial-scale=1" :name "viewport"}]]
   [:body
    [:header
     [:h1 "Factoids"]]
    [:section.container body]]))

(defn render-view [data]
  (html
    (layout data)))
