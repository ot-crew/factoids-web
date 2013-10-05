(ns factoids-web.css
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.core :refer [css]]
            [garden.color :as color :refer [hsl hsla rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.units :as u :refer [em px pt]]))

(def styles
  (css
    ;;-----------------------------------------------------------------------------
    ;; Base
    ;;-----------------------------------------------------------------------------

    [:html :body
     {:height "100%"}]

    [:body
     {:text-rendering "optimizeLegibility"
      :-webkit-font-smoothing "antialiased"}]
    ))
