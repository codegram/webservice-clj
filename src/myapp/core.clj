(ns myapp.core
  (:require [myapp.post :as post]
            [myapp.server :as server]
            [compojure.handler :as handler])
  (:use org.httpkit.server))

(defn -main [& args]
  (post/init-db "development")
  (run-server (handler/site #'server/all-routes) {:port 8080}))
