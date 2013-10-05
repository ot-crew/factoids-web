(defproject factoids-web "0.1.0-SNAPSHOT"
  :description "b-ot factoid browser!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure   "1.5.1"]
                 [com.taoensso/carmine  "2.2.3"]
                 [compojure             "1.1.5"]
                 [environ               "0.4.0"]
                 [garden                "1.0.2"]
                 [hiccup                "1.0.4"]
                 [http-kit              "2.1.10"]]

  :main factoids-web.handler

  :plugins [[lein-environ "0.4.0"]
            [lein-ring    "0.8.5"]]

  :ring {:handler factoids-web.handler/app}

  :profiles {:production {:ring {:open-browser? false
                                 :stacktraces?  false
                                 :auto-reload?  false}}

             :dev {:dependencies [[ring-mock "0.1.5"]]
                   :ring {:open-browser? false
                          :auto-refresh? true
                          :nrepl         {:start? true :port 7000}}}})
