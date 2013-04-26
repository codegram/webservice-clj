(ns myapp.server-test
  (:use clojure.test
        myapp.server)
  (:require [cheshire.core :as json]
            [myapp.post :as post]))

(defn- decode [string]
  (json/parse-string string))

(deftest create-test
  (let [response (create { :title "Foo" })]
    (testing "Returns 201 as the status code"
      (is (= 201 (:status response))))

    (testing "Returns the ID in the response"
      (is (decode (response :body)) :_id))))

(deftest fetch-test
  (let [id (post/create { :title "Foo" })
        response (fetch (str id))]
    (testing "Returns the document"
      (is (=
           { "_id" (str id) "title" "Foo" }
           (decode (response :body)))))))
