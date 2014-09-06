(ns cljs-blogger.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<!]]
            [clojure.string :refer [join]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [markdown.core :refer [md->html]]
            [cljs-http.client :as http]
            [secretary.core :as secretary :include-macros true :refer [defroute]])
  (:import goog.History))

(enable-console-print!)

(defn load-md [title]
  (go (let [txt (<! (http/get (join ["/md/" title ".md"])))
            field (.getElementById js/document "app")]
        (set! (.-innerHTML field) (md->html (:body txt))))))

(secretary/set-config! :prefix "#")

(defroute "/" []
  (load-md "main"))

(defroute "/:title" [title]
  (load-md title))

(let [h (History.)]
  (goog.events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
  (doto h (.setEnabled true)))
