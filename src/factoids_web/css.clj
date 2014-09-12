(ns factoids-web.css
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.core :refer [css]]
            [garden.color :as color :refer [hsl hsla rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.units :as u :refer [em px pt]]))

(def border-color :#EDEDED)

(def styles
  (css {:vendors ["moz" "ms" "webkit"]}

    ;;-----------------------------------------------------------------------------
    ;; Base
    ;;-----------------------------------------------------------------------------

    [:html :body
     {:height "100%"}]

    [:*
     {:box-sizing "border-box"}]

    [:body
     {:background-color :#FFF
      :color :#6C6D6F
      :font-family "'Open Sans', sans-serif"
      :font-size "14px"
      :font-weight 400
      :text-rendering "optimizeLegibility"
      :-webkit-font-smoothing "antialiased"}]

    [:h1 :h2 :h3 :h4 :h5 :h6
     {:color :#2A2D33
      :font-family "'Open Sans', sans-serif"
      :font-weight 300}]

    [:h1
     {:font-size (px 24)}]

    [:.container
      {:position "relative"
      :margin [[0 "auto"]]
      :max-width (px 980)}]


    ;;-----------------------------------------------------------------------------
    ;; Links
    ;;-----------------------------------------------------------------------------

    [:a
     {:color :#6AB2EE
      :text-decoration "none"}

     [:&:link
      {:color :#6AB2EE
       :text-decoration "none"}]

     [:&:visited
      {:color :#6AB2EE
       :text-decoration "none"}]

     [:&:hover
      {:color :#A5D1F5
       :text-decoration "none"}]

     [:&:active
      {:color :#6AB2EE
       :text-decoration "none"}]]


    ;;-----------------------------------------------------------------------------
    ;; Header
    ;;-----------------------------------------------------------------------------

    [:body>header
     {:position "relative"
      :width "100%"
      :height (px 60)
      :line-height (px 60)
      :background :#2F323A
      :z-index 1024}

      [:h1
       {:float "left"
        :margin [[0 (px 15)]]
        :padding 0
        :color :#959CAC
        :letter-spacing (px 2)
        :text-transform "uppercase"}]]


    ;;-----------------------------------------------------------------------------
    ;; Table
    ;;-----------------------------------------------------------------------------

    [:table
     {:display "block"
      :width "100%"
      :border-collapse "collapse"
      :border-spacing 0}]

    [:tr
     {}

     ["&:nth-child(even)"
      {:background-color :#F7F7F7}]]

    [:th
     {:padding [[(px 15) (px 15)]]
      :border-top [[(px 1) "solid" border-color]]
      :border-bottom [[(px 1) "solid" border-color]]
      :font-weight 600
      :text-align "left"}]

    [:td
     {:padding [[(px 8) (px 15)]]
      :-ms-wordjbreak "break-all"
      "word-break" "break-all"
      :word-break "break-word"
      :-webkit-hyphens "auto"
      :-moz-hyphens "auto"
      :hyphens "auto"}
     
     [:&.trigger
      {:min-width (px 250)
       :font-weight 600}]]


    ))
