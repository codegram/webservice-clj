(ns myapp.post-test
  (:require [clojure.test :refer :all]
            [myapp.post :refer :all]))

(init-db "test")

(deftest persist-test
  (let [id (create { :title "Foo" })
        retrieved (fetch id)]
    (testing "Inserts and retrieves a document."
      (is (= (retrieved :title) "Foo")))))
