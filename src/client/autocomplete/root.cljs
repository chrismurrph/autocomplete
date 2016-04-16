(ns autocomplete.root
  (:require [om.dom :as dom]
            [om.next :as om :refer-macros [defui]]
            [autocomplete.core :as core]
            [om.next :as om]
            [default-db-format.core :as db-format]
            [autocomplete.ui :as ui]))

(defui ^:once Root
       static om/IQuery
       (query [_]
              [{:root-join (om/get-query ui/AutoCompleter)}])
       Object
       (render [this]
               (let [app-props (om/props this)
                     {:keys [root-join]} app-props
                     my-reconciler (:reconciler @core/app)]
                 (dom/div nil
                          (if my-reconciler
                            (db-format/show-hud (db-format/check my-reconciler))
                            (println "reconciler not available in Root component"))
                          (ui/auto-completer root-join)
                          (dom/br nil)))))
