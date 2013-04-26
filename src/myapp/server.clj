(ns myapp.server
  (:use compojure.core)
  (:require [compojure.route :as route]
            [myapp.post :as post]
            [cheshire.core :as json])
  (:import org.bson.types.ObjectId))

(def to-json json/generate-string)

(defn create [params]
  (let [id (post/create params)]
    {:status 201
     :headers {"Content-Type" "application/json"}
     :body (to-json (assoc params :_id (str id)))}))

(defn fetch [id]
  (let [object-id (ObjectId. id)
        post (post/fetch object-id)]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (to-json (assoc post :_id (str id)))}))

(defroutes all-routes
  (POST "/posts" {params :params} (create params))
  (GET "/posts/:id" [id] (fetch id)))
