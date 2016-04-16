(ns autocomplete.main
  (:require [autocomplete.root :as root]
            [autocomplete.core :as core]
            autocomplete.mutations
            [untangled.client.core :as uc]
            [om.dom :as dom]
            [om.next :as om]))

(reset! core/app (uc/mount @core/app root/Root "app"))
