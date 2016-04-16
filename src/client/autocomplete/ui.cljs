(ns autocomplete.ui
  (:require [om.next :as om :refer-macros [defui]]
            [untangled.client.mutations :as mut]
            [om.dom :as dom]))

(defn result-list [results]
  (dom/ul #js {:key "result-list"}
          (map #(dom/li #js{:key %} %) results)))

(defn search-field [component user-request]
  (dom/input
    #js {:key   "search-field"
         :value user-request
         :onChange
                (fn [e]
                  (let [evt-val (.. e -target -value)
                        _ (println "Entered: " evt-val)]
                    (om/set-query! component
                                   {:params {:user-query evt-val}})))}))

(defui AutoCompleter
  static om/IQuery
  (query [_]
    '[:ui/results :ui/user-request])
  Object
  (render [this]
    (let [{:keys [ui/results ui/user-request]} (om/props this)
          _ (println "results:" results)]
      (dom/div nil
               (dom/h2 nil "AutoCompleter")
               (cond->
                 [(search-field this user-request)]
                 (not (empty? results)) (conj (result-list results)))
               (dom/br nil)
               (#_ (dom/button #js{:onClick (fn [_] (read-query))} "Read Query"))))))
(def auto-completer (om/factory AutoCompleter))
