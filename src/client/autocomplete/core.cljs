(ns autocomplete.core
  (:require [untangled.client.core :as uc]
            [autocomplete.ui :as ui]
            [autocomplete.routing :refer [configure-routing!]]
            [goog.events :as events]
            [secretary.core :as secretary :refer-macros [defroute]]
            [goog.history.EventType :as EventType]
            [om.next :as om]
            [untangled.client.logging :as log])
  (:import goog.History))

(defonce app (atom (uc/new-untangled-client
                     :initial-state {:todos/filter :none}
                     :started-callback (fn [app]
                                         (configure-routing! (:reconciler app))
                                         (let [h (History.)]
                                           (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
                                           (doto h (.setEnabled true)))))))


