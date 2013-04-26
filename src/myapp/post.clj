(ns myapp.post
  (:require [monger.collection :as mc]
            [monger.core :as mg])
  (:import [org.bson.types ObjectId]))

(defn init-db [name]
  (mg/connect!)
  (mg/set-db! (mg/get-db name)))

(defn fetch [id]
  (mc/find-one-as-map "posts" { :_id id }))

(defn create [post]
  (let [id (ObjectId.)]
    (mc/insert "posts" (assoc post :_id id))
    id))
