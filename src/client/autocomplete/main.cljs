(ns autocomplete.main
  (:require [autocomplete.ui :as ui]
            [autocomplete.core :as core]
            autocomplete.mutations
            [untangled.client.core :as uc]))

(reset! core/app (uc/mount @core/app ui/TodoList "app"))
