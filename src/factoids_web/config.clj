(ns factoids-web.config
  (:require [environ.core :refer  :all]))

(defn config
  ([key]
     (config key nil))
  ([key fallback]
     (get
       {:carmine {:pool :none
                  :spec {:uri (env :redis-url)}}
        :factoid-key "FACTOID"
        :port (env :port 9090)} key fallback)))
