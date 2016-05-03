(ns numeralia.endpoint.numerals-test
  (:require [numeralia.endpoint.numerals :refer [numerals-endpoint]]
            [ring.mock.request :as mock]
            [clojure.test :refer :all]))

(def handler
  (numerals-endpoint {}))

(deftest test-numerals-endpoint
  (testing "returns a successful representation when the number can be translated"
    (is (= {:status  200
            :headers {}
            :body    {"number"  1
                      "english" "one"}}
           (handler (mock/request :get "/numerals/1")))))
  (testing "returns a bad request response when the number format is not valid"
    (is (= 400
           (-> (handler (mock/request :get "/numerals/foobar"))
               :status))))
  (testing "returns a bad request response when the number is out of range"
    (is (= 400
           (-> (handler (mock/request :get "/numerals/-1"))
               :status)))))
